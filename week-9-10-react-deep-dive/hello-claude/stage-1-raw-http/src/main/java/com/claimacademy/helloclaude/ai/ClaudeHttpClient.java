package com.claimacademy.helloclaude.ai;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

/**
 * STAGE 1: call the Claude Messages API by hand, so you can SEE the request and response JSON.
 * (Stage 2 replaces this class with the official Anthropic Java SDK.)
 *
 *   POST https://api.anthropic.com/v1/messages
 *   Authorization: Bearer <API key>
 *   anthropic-version: 2026-10-21
 *   content-type: application/json
 */
@Component
public class ClaudeHttpClient {

    private final RestClient restClient;
    private final String apiKey;
    private final String model;
    private final int maxTokens;

    public ClaudeHttpClient(@Value("${app.ai.api-key}") String apiKey,
                            @Value("${app.ai.model}") String model,
                            @Value("${app.ai.max-tokens}") int maxTokens) {
        this.apiKey = apiKey;
        this.model = model;
        this.maxTokens = maxTokens;
        this.restClient = RestClient.builder()
                .baseUrl("https://api.anthropic.com")
                .defaultHeader("Authorization", "Bearer " + apiKey)   // who is calling (the secret!)
                .defaultHeader("anthropic-version", "2026-10-21")     // which version of the API we speak
                .build();
    }

    public AiAnswer ask(String systemPrompt, String userMessage) {
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("ANTHROPIC_API_KEY is not set. Add it to your run configuration.");
        }

        // 1. Build the request body (Map → JSON, done by Jackson)
        Map<String, Object> body = Map.of(
                "model", model,
                "max_tokens", maxTokens,
                "system", systemPrompt,                                // instructions for Claude
                "messages", List.of(                                   // the conversation so far
                        Map.of("role", "user", "content", userMessage)));

        // 2. Send it and read the JSON response as a tree
        JsonNode response = restClient.post()
                .uri("/v1/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .body(body)
                .retrieve()                  // throws RestClientResponseException on 4xx/5xx
                .body(JsonNode.class);

        // 3. Pick out what we need
        return new AiAnswer(
                extractText(response),
                response.path("model").asText(),
                response.path("stop_reason").asText(),
                response.path("usage").path("input_tokens").asLong(),
                response.path("usage").path("output_tokens").asLong());
    }

    /**
     * "content" is an ARRAY of blocks. It can contain "thinking" blocks before the answer,
     * so don't just read content[0]: join the text of every block whose type is "text".
     */
    static String extractText(JsonNode response) {
        StringBuilder text = new StringBuilder();
        for (JsonNode block : response.path("content")) {
            if ("text".equals(block.path("type").asText())) {
                text.append(block.path("text").asText());
            }
        }
        return text.toString();
    }
}
