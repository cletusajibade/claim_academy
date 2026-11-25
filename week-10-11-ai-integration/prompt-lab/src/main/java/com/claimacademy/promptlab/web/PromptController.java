package com.claimacademy.promptlab.web;

import com.claimacademy.promptlab.ai.AiAnswer;
import com.claimacademy.promptlab.prompts.PromptService;
import com.claimacademy.promptlab.prompts.TaskDraft;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** One endpoint per prompting technique. Try them in requests.http. */
@RestController
@RequestMapping("/api/prompts")
public class PromptController {

    private final PromptService prompts;

    public PromptController(PromptService prompts) {
        this.prompts = prompts;
    }

    @PostMapping("/zero-shot")
    public AiAnswer zeroShot(@Valid @RequestBody PromptRequest request) {
        return prompts.zeroShot(request.input());
    }

    @PostMapping("/review")
    public AiAnswer review(@Valid @RequestBody PromptRequest request) {
        return prompts.reviewCode(request.input());
    }

    @PostMapping("/commit-message")
    public AiAnswer commitMessage(@Valid @RequestBody PromptRequest request) {
        return prompts.commitMessage(request.input());
    }

    @PostMapping("/generate-method")
    public PromptService.GeneratedCode generateMethod(@Valid @RequestBody PromptRequest request) {
        return prompts.generateMethod(request.input());
    }

    @PostMapping("/extract-task")
    public TaskDraft extractTask(@Valid @RequestBody PromptRequest request) {
        return prompts.extractTask(request.input());
    }

    @PostMapping("/interview-questions")
    public AiAnswer interviewQuestions(@Valid @RequestBody PromptRequest request) {
        return prompts.interviewQuestions(request.input());
    }
}
