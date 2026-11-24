package com.claimacademy.helloclaude.web;

import com.claimacademy.helloclaude.ai.AiAnswer;
import com.claimacademy.helloclaude.ai.ClaudeHttpClient;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
public class AskController {

    // A system prompt sets Claude's role and rules for the whole conversation
    private static final String SYSTEM_PROMPT = """
            You are a friendly tutor for Java bootcamp students.
            Answer in at most 3 short sentences. If you are not sure, say so.
            """;

    private final ClaudeHttpClient claude;

    public AskController(ClaudeHttpClient claude) {
        this.claude = claude;
    }

    /** POST /api/ai/ask  { "question": "What is a JVM?" } */
    @PostMapping("/ask")
    public AiAnswer ask(@Valid @RequestBody AskRequest request) {
        return claude.ask(SYSTEM_PROMPT, request.question());
    }
}
