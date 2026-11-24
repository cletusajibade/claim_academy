package com.claimacademy.taskmanager.task;

import jakarta.validation.constraints.NotNull;

/** Body for PATCH /api/tasks/{id} — partial update of the completed flag. */
public record TaskPatchRequest(
        @NotNull(message = "completed is required")
        Boolean completed
) {
}
