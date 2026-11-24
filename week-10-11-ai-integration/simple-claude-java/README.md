# simple-claude-java — your first Claude API program (no Spring Boot)

| Step | What | How to run |
|---|---|---|
| `step-1-plain-java/HelloClaude.java` | One file, only the JDK. Builds the JSON and sends the HTTP request by hand. | `java HelloClaude.java` |
| `step-2-java-sdk/.../HelloClaudeSdk.java` | The same thing with the official Anthropic Java SDK. | IntelliJ ▶, or `mvn compile exec:java -Dexec.mainClass=demo.HelloClaudeSdk` |
| `step-2-java-sdk/.../ChatWithClaude.java` | A terminal chat that keeps the conversation history. | IntelliJ ▶, or `mvn compile exec:java -Dexec.mainClass=demo.ChatWithClaude` |

## Before you run anything: set your API key

```bash
# macOS / Linux
export ANTHROPIC_API_KEY=sk-ant-...

# Windows PowerShell
$env:ANTHROPIC_API_KEY="sk-ant-..."
```

In IntelliJ: Run → Edit Configurations → Environment variables → `ANTHROPIC_API_KEY=sk-ant-...`

Never put the key in your code or commit it to Git.

Requires Java 17+. API version `2026-10-21`, SDK `com.anthropic:anthropic-java:3.12.0`.

## Option: use a .env file (dotenv-java)

Java doesn't read `.env` files by itself, and the SDK's `fromEnv()` only sees real environment variables.

1. Copy `step-2-java-sdk/.env.example` to `.env` (same folder as `pom.xml`) and put your key in it. `.env` is already in `.gitignore`.
2. Add to `pom.xml`:
   ```xml
   <dependency>
       <groupId>io.github.cdimascio</groupId>
       <artifactId>dotenv-java</artifactId>
       <version>3.2.0</version>
   </dependency>
   ```
3. Replace `AnthropicOkHttpClient.fromEnv()` with:
   ```java
   Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();   // import io.github.cdimascio.dotenv.Dotenv;
   AnthropicClient client = AnthropicOkHttpClient.builder()
           .apiKey(dotenv.get("ANTHROPIC_API_KEY"))
           .build();
   ```
Real environment variables still work and take priority over the file.
