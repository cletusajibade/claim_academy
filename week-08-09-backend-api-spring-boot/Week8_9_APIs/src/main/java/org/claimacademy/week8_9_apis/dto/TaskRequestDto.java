package org.claimacademy.week8_9_apis.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record TaskRequestDto(
        @NotBlank(message = "must not be blank")
        @Size(max = 100, message = "must be at most 100 characters")
        String title,

        @Size(max = 1000, message = "must be at most 1000 characters")
        String description,

        boolean complete,
        LocalDate dueDate
) { }
