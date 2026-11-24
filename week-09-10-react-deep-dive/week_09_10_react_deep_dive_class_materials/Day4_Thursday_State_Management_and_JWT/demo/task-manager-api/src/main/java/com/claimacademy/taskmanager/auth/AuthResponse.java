package com.claimacademy.taskmanager.auth;

import java.time.Instant;

/** Returned by /api/auth/login and /api/auth/register. */
public record AuthResponse(String token, String username, Instant expiresAt) {
}
