package com.claimacademy.taskmanager.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/** Body for POST /api/tasks and PUT /api/tasks/{id}. */
public record TaskRequest(
        @NotBlank(message = "Title is required")
        @Size(max = 100, message = "Title must be at most 100 characters")
        String title,

        @Size(max = 500, message = "Description must be at most 500 characters")
        String description,

        LocalDate dueDate
) {
}
