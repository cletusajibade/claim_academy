package com.claimacademy.helloclaude.ai;

/** What we keep from Claude's response. */
public record AiAnswer(
        String text,          // the reply
        String model,         // which model answered
        String stopReason,    // why it stopped: end_turn, max_tokens, stop_sequence, refusal …
        long inputTokens,     // tokens we sent (you pay for these)
        long outputTokens     // tokens Claude wrote (you pay more for these)
) {
}
