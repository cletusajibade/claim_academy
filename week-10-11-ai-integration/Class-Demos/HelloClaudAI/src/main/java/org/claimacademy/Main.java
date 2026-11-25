package org.claimacademy;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    static void main() throws Exception {
        // Step 1. Read the API Key
        String apiKey = System.getenv("ANTHROPIC_API_KEY");
        if(apiKey ==null || apiKey.isBlank()){
            IO.println("Please set the ANTHROPIC_API_KEY environment variable first");
            return;
        }

        // Step 2. Ask AI a Question
        // Scanner input
        // String question  = "Explain what a java class is in two short sentences.";
        String question  = "How can I learn java programming";

        // Step 3. Build the request body (JSON)
        // Anthropic expects JSON.
        String requestBody = """
                {
                    "model":"claude-haiku-5-5",
                    "max_tokens":1000,
                    "messages":[
                        {"role": "user", "content":"%s"}
                    ]
                }
                """.formatted(question);

        // Step 4. Build the POST http request
        String baseUrl = "https://api.anthropic.com";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl+"/v1/messages"))
                .header("Authorization","Bearer "+apiKey)
                .header("anthropic-version","2026-10-21")
                .header("content-type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        //Step 5. Send the request and wait for the answer
        IO.println("Asking Claude: "+question);
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Step 6. Check the status code
        if(response.statusCode() != 200){
            IO.println("Something went wrong. HTTP Status:"+response.statusCode());
            IO.println(response.body());
            return;
        }

        // Step 7. All went well, print the body of the response
        //IO.println("\n--- Raw JSON response ---");
        // IO.println(response.body());
        IO.println(extractText(response.body()));
    }

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
