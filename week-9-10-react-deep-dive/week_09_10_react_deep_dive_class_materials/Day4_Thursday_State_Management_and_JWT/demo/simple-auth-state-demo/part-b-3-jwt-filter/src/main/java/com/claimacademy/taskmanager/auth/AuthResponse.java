package com.claimacademy.taskmanager.auth;

import java.time.Instant;

public record AuthResponse(String token, String username, Instant expiresAt) {
}
