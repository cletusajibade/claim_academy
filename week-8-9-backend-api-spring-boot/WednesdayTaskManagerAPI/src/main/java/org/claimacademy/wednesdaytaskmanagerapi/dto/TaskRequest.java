package org.claimacademy.wednesdaytaskmanagerapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/**
 * DTO: what a client is ALLOWED to send. There is no id field, so a client cannot choose its own id.
 * A record is an immutable data class: accessors are title(), description(), ... (no "get").
 */
public record TaskRequest(
        @NotBlank(message = "must not be blank")
        @Size(max = 100, message = "must be at most 100 characters")
        String title,

        @Size(max = 1000, message = "must be at most 1000 characters")
        String description,

        boolean complete,
        LocalDate dueDate
) {
}
