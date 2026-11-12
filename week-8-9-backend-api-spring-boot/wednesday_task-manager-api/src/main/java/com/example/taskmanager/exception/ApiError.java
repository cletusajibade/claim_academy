package com.example.taskmanager.exception;

import java.time.Instant;

/**
 * Every error response has this one predictable shape.
 */
public record ApiError(
        int status,
        String error,
        String message,
        Instant timestamp
) {
}
