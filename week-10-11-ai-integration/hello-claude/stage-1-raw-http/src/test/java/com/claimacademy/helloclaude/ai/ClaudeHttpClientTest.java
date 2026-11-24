package com.claimacademy.helloclaude.ai;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/** No API key needed: we test our JSON handling with a saved example response. */
class ClaudeHttpClientTest {

    @Test
    void extractsTextAndSkipsThinkingBlocks() throws Exception {
        JsonNode response = new ObjectMapper().readTree("""
                {
                  "id": "msg_01",
                  "type": "message",
                  "role": "assistant",
                  "model": "claude-haiku-5-5",
                  "content": [
                    { "type": "thinking", "thinking": "The student asks about the JVM..." },
                    { "type": "text", "text": "The JVM runs Java bytecode." }
                  ],
                  "stop_reason": "end_turn",
                  "usage": { "input_tokens": 42, "output_tokens": 9 }
                }
                """);

        assertThat(ClaudeHttpClient.extractText(response)).isEqualTo("The JVM runs Java bytecode.");
    }
}
