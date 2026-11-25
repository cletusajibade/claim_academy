package com.claimacademy.promptlab.prompts;

import com.claimacademy.promptlab.ai.AiAnswer;
import com.claimacademy.promptlab.ai.AiClient;
import com.claimacademy.promptlab.ai.AiRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PromptService {

    private static final Pattern CODE_TAG = Pattern.compile("<code>(.*?)</code>", Pattern.DOTALL);
    private static final Set<String> PRIORITIES = Set.of("LOW", "MEDIUM", "HIGH");

    private final AiClient ai;
    private final ObjectMapper objectMapper;

    public PromptService(AiClient ai, ObjectMapper objectMapper) {
        this.ai = ai;
        this.objectMapper = objectMapper;
    }

    /** 0. Zero-shot, no system prompt: just the question. Compare with reviewCode(). */
    public AiAnswer zeroShot(String input) {
        return ai.complete(AiRequest.of(null, input));
    }

    /** 1. Same input, but with a role, rules and an output format in the system prompt. */
    public AiAnswer reviewCode(String code) {
        return ai.complete(AiRequest.of(Prompts.CODE_REVIEWER_SYSTEM, "Review this code:\n\n" + code));
    }

    /** 2. Few-shot: example input/output pairs become earlier turns of the conversation. */
    public AiAnswer commitMessage(String description) {
        AiRequest.Builder request = AiRequest.builder(Prompts.COMMIT_MESSAGE_SYSTEM).maxTokens(200);
        for (String[] example : Prompts.COMMIT_MESSAGE_EXAMPLES) {
            request.user(example[0]).assistant(example[1]);
        }
        request.user(description);          // the real question comes last
        return ai.complete(request.build());
    }

    /** 3. Chain of thought: Claude plans in <plan>, answers in <code>; we keep only the code. */
    public GeneratedCode generateMethod(String spec) {
        AiAnswer answer = ai.complete(AiRequest.of(Prompts.JAVA_CODER_SYSTEM, spec));
        Matcher matcher = CODE_TAG.matcher(answer.text());
        if (!matcher.find()) {
            throw new AiOutputException("Claude did not return a <code> section", answer.text());
        }
        return new GeneratedCode(matcher.group(1).strip(), answer.text());
    }

    /** 4. Structured output: ask for JSON, then VALIDATE it before using it. */
    public TaskDraft extractTask(String sentence) {
        String system = Prompts.TASK_EXTRACTOR_SYSTEM.formatted(LocalDate.now());
        AiAnswer answer = ai.complete(AiRequest.of(system, sentence));
        String json = JsonText.extractObject(answer.text());

        TaskDraft draft;
        try {
            draft = objectMapper.readValue(json, TaskDraft.class);
        } catch (JsonProcessingException e) {
            throw new AiOutputException("Claude's reply was not valid JSON", answer.text());
        }
        if (draft.title() == null || draft.title().isBlank()) {
            throw new AiOutputException("The task has no title", answer.text());
        }
        if (draft.priority() == null || !PRIORITIES.contains(draft.priority().toUpperCase())) {
            throw new AiOutputException("Unknown priority: " + draft.priority(), answer.text());
        }
        return new TaskDraft(draft.title().strip(), draft.priority().toUpperCase(), draft.dueDate());
    }

    /** 5. Pair-programming lab starter: iterate on Prompts.INTERVIEW_QUESTIONS_V1. */
    public AiAnswer interviewQuestions(String role) {
        return ai.complete(AiRequest.of(null, Prompts.INTERVIEW_QUESTIONS_V1.formatted(role)));
    }

    public record GeneratedCode(String code, String fullAnswer) {
    }
}
