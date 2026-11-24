package com.claimacademy.taskmanager.exception;

import java.time.Instant;
import java.util.Map;

/** Consistent error shape the React app can rely on. */
public record ApiError(
        int status,
        String message,
        Map<String, String> fieldErrors,
        Instant timestamp
) {
    public static ApiError of(int status, String message) {
        return new ApiError(status, message, Map.of(), Instant.now());
    }

    public static ApiError of(int status, String message, Map<String, String> fieldErrors) {
        return new ApiError(status, message, fieldErrors, Instant.now());
    }
}
