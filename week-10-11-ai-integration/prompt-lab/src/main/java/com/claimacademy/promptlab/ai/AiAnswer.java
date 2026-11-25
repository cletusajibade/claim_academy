package com.claimacademy.promptlab.ai;

public record AiAnswer(String text, String stopReason, long inputTokens, long outputTokens) {
}
