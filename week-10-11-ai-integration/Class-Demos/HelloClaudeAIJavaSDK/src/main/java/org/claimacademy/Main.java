package org.claimacademy;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.messages.Message;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.TextBlock;
import io.github.cdimascio.dotenv.Dotenv;

public class Main {
    static void main() {
        // 1. Create a client
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        AnthropicClient client = AnthropicOkHttpClient
                .builder()
                .apiKey(dotenv.get("ANTHROPIC_API_KEY"))
                .build();

        // 2. Describe the request with a builder instead of a JSON
        String question = "Explain what a java class is in two short sentences.";
        MessageCreateParams params = MessageCreateParams
                .builder()
                .model("claude-haiku-5-5")
                .maxTokens(300)
                .system("You are a friendly Java teacher. Use simple words.")
                .addUserMessage(question)
                .build();

        // 3. Send the request.
        Message message = client.messages().create(params);

        // 4. The answer is a list of content block.
        IO.println("Asking Claude: "+question);
        IO.println("---- Claude's Answer ----");
        message.content().stream()
                .flatMap(block->block.text().stream())
                .map(TextBlock::text)
                .forEach(IO::println);
    }
}
