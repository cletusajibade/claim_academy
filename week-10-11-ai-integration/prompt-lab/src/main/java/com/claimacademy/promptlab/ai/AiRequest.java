package com.claimacademy.promptlab.ai;

import java.util.ArrayList;
import java.util.List;

/**
 * Everything we send to Claude, independent of any SDK.
 * turns = the conversation: user, assistant, user, assistant, … ending with a user turn.
 */
public record AiRequest(String system, List<Turn> turns, long maxTokens) {

    public record Turn(Role role, String text) {
    }

    public enum Role { USER, ASSISTANT }

    /** Shortcut for the common case: a system prompt and one user message. */
    public static AiRequest of(String system, String userMessage) {
        return new AiRequest(system, List.of(new Turn(Role.USER, userMessage)), 1024);
    }

    /** Builder-style helper used by the few-shot example. */
    public static Builder builder(String system) {
        return new Builder(system);
    }

    public static final class Builder {
        private final String system;
        private final List<Turn> turns = new ArrayList<>();
        private long maxTokens = 1024;

        private Builder(String system) {
            this.system = system;
        }

        public Builder user(String text) {
            turns.add(new Turn(Role.USER, text));
            return this;
        }

        public Builder assistant(String text) {
            turns.add(new Turn(Role.ASSISTANT, text));
            return this;
        }

        public Builder maxTokens(long maxTokens) {
            this.maxTokens = maxTokens;
            return this;
        }

        public AiRequest build() {
            return new AiRequest(system, List.copyOf(turns), maxTokens);
        }
    }
}
