// =====================================================================
//  STEP 2 — The same program, using the official Anthropic Java SDK
// =====================================================================
//  The SDK (added in pom.xml) builds the JSON, sends the request and
//  reads the answer for us. Compare this file with Step 1!
//
//  Run: the green ▶ arrow in IntelliJ (set ANTHROPIC_API_KEY in
//  Run > Edit Configurations > Environment variables), or:
//      mvn compile exec:java -Dexec.mainClass=demo.HelloClaudeSdk
// =====================================================================
package demo;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.messages.Message;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.TextBlock;

public class HelloClaudeSdk {

    public static void main(String[] args) {

        // 1. Create a client. fromEnv() reads ANTHROPIC_API_KEY for us.
        //    (If the variable is missing you'll get a clear error here.)
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();

        // 2. Describe the request with a builder instead of writing JSON.
        //    Same three things as Step 1: model, max tokens, a user message.
        //    Bonus: a system prompt — instructions for HOW Claude should answer.
        MessageCreateParams params = MessageCreateParams.builder()
                .model("claude-haiku-5-5")
                .maxTokens(300)
                .system("You are a friendly Java teacher. Use simple words.")
                .addUserMessage("Explain what a Java class is in two short sentences.")
                .build();

        // 3. Send it. This one line replaces the HttpClient code of Step 1.
        Message message = client.messages().create(params);

        // 4. The answer is a LIST of content blocks. Keep only the text blocks
        //    (some models also send "thinking" blocks) and print them.
        System.out.println("--- Claude's answer ---");
        message.content().stream()
                .flatMap(block -> block.text().stream())
                .map(TextBlock::text)
                .forEach(System.out::println);

        // 5. Extra information that comes with every answer.
        System.out.println("\n--- Details ---");
        System.out.println("Stop reason:   " + message.stopReason().orElse(null)); // end_turn = finished normally
        System.out.println("Input tokens:  " + message.usage().inputTokens());      // what we sent
        System.out.println("Output tokens: " + message.usage().outputTokens());     // what Claude wrote (you pay for both)
    }
}
