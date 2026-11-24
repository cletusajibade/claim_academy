package com.claimacademy.taskmanager.task;

import java.time.Instant;
import java.time.LocalDate;

/** What the API returns — never expose the JPA entity directly. */
public record TaskResponse(
        Long id,
        String title,
        String description,
        LocalDate dueDate,
        boolean completed,
        Instant createdAt
) {
    public static TaskResponse from(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.isCompleted(),
                task.getCreatedAt());
    }
}
