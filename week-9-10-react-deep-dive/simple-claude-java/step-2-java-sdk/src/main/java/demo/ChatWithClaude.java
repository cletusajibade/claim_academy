// =====================================================================
//  STEP 3 — A tiny chat in the terminal (type "bye" to quit)
// =====================================================================
//  Lesson: Claude does NOT remember earlier messages by itself.
//  To have a conversation, WE keep the history and send all of it
//  every time.
//
//  Run: the green ▶ arrow in IntelliJ, or:
//      mvn compile exec:java -Dexec.mainClass=demo.ChatWithClaude
// =====================================================================
package demo;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.messages.Message;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.TextBlock;

import java.util.Scanner;
import java.util.stream.Collectors;

public class ChatWithClaude {

    public static void main(String[] args) {
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();
        Scanner keyboard = new Scanner(System.in);

        // The builder holds the WHOLE conversation. We keep adding to it.
        MessageCreateParams.Builder conversation = MessageCreateParams.builder()
                .model("claude-haiku-5-5")
                .maxTokens(300)
                .system("You are a friendly Java tutor. Keep answers under 3 sentences.");

        System.out.println("Chat with Claude! Type 'bye' to quit.");

        while (true) {
            // 1. Read what the user types
            System.out.print("\nYou: ");
            String userText = keyboard.nextLine().trim();
            if (userText.equalsIgnoreCase("bye")) {
                break;
            }
            if (userText.isEmpty()) {
                continue;                                   // don't send empty messages
            }

            // 2. Add the user's message to the history and send EVERYTHING
            conversation.addUserMessage(userText);
            Message message = client.messages().create(conversation.build());

            // 3. Get the answer text
            String answer = message.content().stream()
                    .flatMap(block -> block.text().stream())
                    .map(TextBlock::text)
                    .collect(Collectors.joining());
            System.out.println("Claude: " + answer);

            // 4. Add Claude's answer to the history too, so the next
            //    question can refer to it ("can you give an example of that?")
            conversation.addAssistantMessage(answer);

            // Watch this number grow: the whole history is sent every time
            System.out.println("   (input tokens this turn: " + message.usage().inputTokens() + ")");
        }
        System.out.println("Goodbye!");
    }
}
