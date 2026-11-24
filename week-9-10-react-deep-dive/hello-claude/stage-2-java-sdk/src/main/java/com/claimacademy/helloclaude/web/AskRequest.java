package com.claimacademy.helloclaude.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AskRequest(
        @NotBlank(message = "question is required")
        @Size(max = 2000, message = "question must be at most 2000 characters")
        String question
) {
}
