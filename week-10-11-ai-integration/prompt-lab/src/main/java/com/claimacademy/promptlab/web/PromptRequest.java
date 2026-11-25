package com.claimacademy.promptlab.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PromptRequest(
        @NotBlank(message = "input is required")
        @Size(max = 4000, message = "input must be at most 4000 characters")
        String input
) {
}
