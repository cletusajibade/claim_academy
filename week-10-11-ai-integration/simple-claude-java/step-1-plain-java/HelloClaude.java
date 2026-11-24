// =====================================================================
//  STEP 1 — Talk to Claude with plain Java (no libraries, no Spring)
// =====================================================================
//  Run it (Java 17 or newer) from this folder:
//
//      export ANTHROPIC_API_KEY=sk-ant-...      (macOS / Linux)
//      $env:ANTHROPIC_API_KEY="sk-ant-..."      (Windows PowerShell)
//      java HelloClaude.java
//
//  "java HelloClaude.java" compiles and runs a single file in one go.
//  No Maven, no project setup needed.
// =====================================================================

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HelloClaude {

    public static void main(String[] args) throws Exception {

        // ---------------------------------------------------------------
        // 1. Read the API key from an ENVIRONMENT VARIABLE.
        //    Never type your real key into the code: code ends up on GitHub.
        // ---------------------------------------------------------------
        String apiKey = System.getenv("ANTHROPIC_API_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            System.out.println("Please set the ANTHROPIC_API_KEY environment variable first.");
            return;
        }

        // ---------------------------------------------------------------
        // 2. Our question. Try changing it!
        // ---------------------------------------------------------------
        String question = "Explain what a Java class is in two short sentences.";

        // ---------------------------------------------------------------
        // 3. Build the request body. The Claude API speaks JSON.
        //    - model:      which Claude model answers
        //    - max_tokens: the longest answer we allow (required)
        //    - messages:   the conversation; here just one "user" message
        //    The """ ... """ is a Java text block: a multi-line String.
        //    %s is replaced by the question (see .formatted below).
        // ---------------------------------------------------------------
        String requestBody = """
                {
                  "model": "claude-haiku-5-5",
                  "max_tokens": 300,
                  "messages": [
                    { "role": "user", "content": "%s" }
                  ]
                }
                """.formatted(question);

        // ---------------------------------------------------------------
        // 4. Build the HTTP request: POST to the Messages endpoint.
        //    Three headers are required:
        //    - Authorization:      "Bearer " + your key  (who you are)
        //    - anthropic-version:  which version of the API you expect
        //    - content-type:       we are sending JSON
        // ---------------------------------------------------------------
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.anthropic.com/v1/messages"))
                .header("Authorization", "Bearer " + apiKey)
                .header("anthropic-version", "2026-10-21")
                .header("content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        // ---------------------------------------------------------------
        // 5. Send it and wait for the answer (this takes a few seconds).
        // ---------------------------------------------------------------
        System.out.println("Asking Claude: " + question);
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // ---------------------------------------------------------------
        // 6. Check the status code. 200 means OK.
        //    401 = bad key, 400 = something wrong in our JSON,
        //    429 = too many requests, 5xx = problem on Anthropic's side.
        // ---------------------------------------------------------------
        if (response.statusCode() != 200) {
            System.out.println("Something went wrong. HTTP status: " + response.statusCode());
            System.out.println(response.body());
            return;
        }

        // ---------------------------------------------------------------
        // 7. Print the raw JSON so we can SEE what comes back.
        //    Look for: "content" (the answer), "stop_reason", "usage" (tokens).
        // ---------------------------------------------------------------
        System.out.println("\n--- Raw JSON response ---");
        System.out.println(response.body());

        // ---------------------------------------------------------------
        // 8. Pull out just the answer text.
        //    We do it by hand here; in Step 2 the SDK does it for us.
        // ---------------------------------------------------------------
        System.out.println("\n--- Claude's answer ---");
        System.out.println(extractText(response.body()));
    }

    /**
     * Finds the first "text": "..." value in the JSON and returns it.
     * This is a teaching shortcut: real programs use a JSON library
     * (like Jackson) or the Anthropic SDK instead of searching strings.
     */
    static String extractText(String json) {
        String marker = "\"text\":";
        int start = json.indexOf(marker);
        if (start < 0) {
            return "(no text found)";
        }
        start = json.indexOf('"', start + marker.length()) + 1; // skip to the opening quote

        StringBuilder text = new StringBuilder();
        for (int i = start; i < json.length(); i++) {
            char c = json.charAt(i);
            if (c == '"') {
                break;                                  // closing quote = end of the text
            }
            if (c == '\\') {                            // escaped characters like \n or \"
                char next = json.charAt(++i);
                switch (next) {
                    case 'n' -> text.append('\n');
                    case 't' -> text.append('\t');
                    case 'u' -> {                       // unicode escapes like \\u00e9
                        text.append((char) Integer.parseInt(json.substring(i + 1, i + 5), 16));
                        i += 4;
                    }
                    default -> text.append(next);       // \" and \\
                }
            } else {
                text.append(c);
            }
        }
        return text.toString();
    }
}
