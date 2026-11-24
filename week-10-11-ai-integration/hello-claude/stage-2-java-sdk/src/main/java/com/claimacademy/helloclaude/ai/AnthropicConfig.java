package com.claimacademy.helloclaude.ai;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * ONE AnthropicClient for the whole app (it holds connection and thread pools).
 * Spring creates it once; anything can ask for it in its constructor.
 */
@Configuration
public class AnthropicConfig {

    @Bean
    public AnthropicClient anthropicClient(@Value("${app.ai.api-key}") String apiKey) {
        return AnthropicOkHttpClient.builder()
                // If the key is missing we still start the app; ClaudeService explains the problem on the first call
                .apiKey(apiKey.isBlank() ? "not-set" : apiKey)
                .maxRetries(2)                        // retries 429 / 5xx / network errors with backoff
                .timeout(Duration.ofSeconds(30))      // don't let a web request hang for minutes
                .build();
    }
}
