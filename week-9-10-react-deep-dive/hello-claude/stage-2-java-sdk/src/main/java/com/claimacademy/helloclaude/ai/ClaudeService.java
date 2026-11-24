package com.claimacademy.helloclaude.ai;

import com.anthropic.client.AnthropicClient;
import com.anthropic.models.messages.Message;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.TextBlock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * STAGE 2: the same call as stage 1, written with the Anthropic Java SDK.
 * The SDK builds the JSON, adds the headers, retries failures and gives us typed objects.
 */
@Service
public class ClaudeService {

    private final AnthropicClient client;
    private final String apiKey;
    private final String model;
    private final long maxTokens;

    public ClaudeService(AnthropicClient client,
                         @Value("${app.ai.api-key}") String apiKey,
                         @Value("${app.ai.model}") String model,
                         @Value("${app.ai.max-tokens}") long maxTokens) {
        this.client = client;
        this.apiKey = apiKey;
        this.model = model;
        this.maxTokens = maxTokens;
    }

    public AiAnswer ask(String systemPrompt, String userMessage) {
        if (apiKey.isBlank()) {
            throw new IllegalStateException("ANTHROPIC_API_KEY is not set. Add it to your run configuration.");
        }

        // 1. Build the request: the same fields as the JSON in stage 1
        MessageCreateParams params = MessageCreateParams.builder()
                .model(model)
                .maxTokens(maxTokens)
                .system(systemPrompt)
                .addUserMessage(userMessage)
                .build();

        // 2. Send it
        Message message = client.messages().create(params);

        // 3. Keep only the text blocks (skip any "thinking" blocks)
        String text = message.content().stream()
                .flatMap(block -> block.text().stream())
                .map(TextBlock::text)
                .collect(Collectors.joining());

        return new AiAnswer(
                text,
                message.model().toString(),
                message.stopReason().map(Object::toString).orElse("unknown"),
                message.usage().inputTokens(),
                message.usage().outputTokens());
    }
}
