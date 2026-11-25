# prompt-lab — Week 10, Day 2 (Prompt Engineering)

One Spring Boot app, one endpoint per prompting technique. Open `requests.http` and run them top to bottom in class.

| Endpoint | Technique | Where the prompt lives |
|---|---|---|
| `POST /api/prompts/zero-shot` | No system prompt (the "before") | — |
| `POST /api/prompts/review` | System prompt: role, audience, rules, format | `Prompts.CODE_REVIEWER_SYSTEM` |
| `POST /api/prompts/commit-message` | Few-shot: examples as earlier user/assistant turns | `Prompts.COMMIT_MESSAGE_*` |
| `POST /api/prompts/generate-method` | Chain of thought with `<plan>` / `<code>` tags | `Prompts.JAVA_CODER_SYSTEM` |
| `POST /api/prompts/extract-task` | JSON output + validation in Java | `Prompts.TASK_EXTRACTOR_SYSTEM` |
| `POST /api/prompts/interview-questions` | Pair lab starter (weak v1 on purpose) | `Prompts.INTERVIEW_QUESTIONS_V1` |

## Run

1. Set `ANTHROPIC_API_KEY` in your IntelliJ run configuration (Run → Edit Configurations → Environment variables). Never put it in code or in `application.properties`.
2. Run `PromptLabApplication`.
3. Run the requests in `requests.http`.

## Tests (no API key needed)

```
mvn test
```

`AiClient` is a one-method interface, so the tests pass a lambda that plays Claude. That's how you test AI features: fake the model and test your own code around it (parsing, validation, error paths).

## Design points to call out

- **Prompts are code.** They live in `Prompts.java` as text blocks, are reviewed in PRs and versioned in Git.
- **The SDK is behind an interface** (`AiClient` → `AnthropicAiClient`). Controllers and services never import `com.anthropic.*`.
- **Never trust the output.** `extractTask` strips stray text and fences, parses with Jackson, checks the title and priority, and throws `AiOutputException` (→ 502) if anything is off.
- **Temperature is gone.** On API version `2026-10-21`, `temperature`, `top_p` and `top_k` return a 400 error. Steer the output with the prompt, examples and validation instead.
