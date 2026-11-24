# Day 1 Demo: hello-claude (Spring Boot → Claude API)

Two snapshots of the same tiny app: `POST /api/ai/ask { "question": "..." }` → Claude's answer + token usage.

| Stage | Folder | How it calls Claude | Why |
|---|---|---|---|
| 1 | `stage-1-raw-http` | Spring `RestClient` + a `Map` body → raw JSON | See the real request (headers, `model`, `max_tokens`, `system`, `messages`) and response (`content` blocks, `stop_reason`, `usage`) |
| 2 | `stage-2-java-sdk` | Anthropic Java SDK `com.anthropic:anthropic-java:3.12.0` | Typed builders, automatic retries, timeouts, one exception class per error |

## Before you run
1. Get an API key from https://platform.claude.com (Settings → API keys).
2. Put it in an **environment variable**, never in code or `application.properties`:
   - IntelliJ: Run → Edit Configurations → *Environment variables* → `ANTHROPIC_API_KEY=sk-ant-...`
   - Terminal (macOS/Linux): `export ANTHROPIC_API_KEY=sk-ant-...`
3. `mvn spring-boot:run`, then send the requests in `requests.http` (IntelliJ) or Postman.

The tests (`mvn test`) need **no** API key: they use a saved JSON response (stage 1) and a Mockito mock (stage 2).

## Things to point out
- API version `2026-10-21` (current since Oct 2026): `temperature`, `top_p` and `top_k` were **removed**; sending them is a 400 error. Many online tutorials still show them.
- The SDK (v3) sends the key as `Authorization: Bearer <key>`. Older examples use `x-api-key`; the API accepts both.
- `content` is an array. It may contain `thinking` blocks before the `text` block, so always select blocks by type.
- `max_tokens` is a hard ceiling. If Claude hits it, `stop_reason` is `max_tokens` and the answer is cut off.
