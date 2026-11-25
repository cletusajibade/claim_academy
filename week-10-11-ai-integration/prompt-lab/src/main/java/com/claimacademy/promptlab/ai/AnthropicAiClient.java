package com.claimacademy.promptlab.ai;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.messages.Message;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.TextBlock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.stream.Collectors;

/** The real AiClient: turns an AiRequest into a Claude Messages API call with the Java SDK. */
@Component
public class AnthropicAiClient implements AiClient {

    private final AnthropicClient client;
    private final String apiKey;
    private final String model;

    public AnthropicAiClient(@Value("${app.ai.api-key}") String apiKey,
                             @Value("${app.ai.model}") String model) {
        this.apiKey = apiKey;
        this.model = model;
        this.client = AnthropicOkHttpClient.builder()
                .apiKey(apiKey.isBlank() ? "not-set" : apiKey)
                .maxRetries(2)
                .timeout(Duration.ofSeconds(30))
                .build();
    }

    @Override
    public AiAnswer complete(AiRequest request) {
        if (apiKey.isBlank()) {
            throw new IllegalStateException("ANTHROPIC_API_KEY is not set. Add it to your run configuration.");
        }

        MessageCreateParams.Builder params = MessageCreateParams.builder()
                .model(model)
                .maxTokens(request.maxTokens());

        // The system prompt is optional: only send it when we have one
        if (request.system() != null && !request.system().isBlank()) {
            params.system(request.system());
        }

        // The messages array: our turns, in order
        for (AiRequest.Turn turn : request.turns()) {
            if (turn.role() == AiRequest.Role.USER) {
                params.addUserMessage(turn.text());
            } else {
                params.addAssistantMessage(turn.text());
            }
        }

        Message message = client.messages().create(params.build());

        String text = message.content().stream()
                .flatMap(block -> block.text().stream())
                .map(TextBlock::text)
                .collect(Collectors.joining());

        return new AiAnswer(
                text,
                message.stopReason().map(Object::toString).orElse("unknown"),
                message.usage().inputTokens(),
                message.usage().outputTokens());
    }
}
