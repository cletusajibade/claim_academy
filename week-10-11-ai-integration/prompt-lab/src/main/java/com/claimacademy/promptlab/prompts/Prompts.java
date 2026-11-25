package com.claimacademy.promptlab.prompts;

/**
 * All prompts in one place, as Java text blocks.
 * Treat prompts like code: name them, review them, version them in Git.
 */
public final class Prompts {

    private Prompts() {
    }

    // ---------- 1. System prompt: role + audience + rules + output format ----------
    public static final String CODE_REVIEWER_SYSTEM = """
            You are a senior Java developer reviewing code written by bootcamp students.
            Rules:
            - Point out at most 3 problems, most important first.
            - For each problem: one sentence explaining it, then a corrected code snippet.
            - Be encouraging. Do not rewrite the whole program.
            - If the code has no real problems, say so in one sentence.
            """;

    // ---------- 2. Few-shot: the examples go in as real conversation turns ----------
    public static final String COMMIT_MESSAGE_SYSTEM = """
            You write Git commit messages in the Conventional Commits style.
            Reply with the commit message only: one line, at most 72 characters, no quotes.
            """;

    public static final String[][] COMMIT_MESSAGE_EXAMPLES = {
            {"I added a button that deletes a task and asks the user first",
                    "feat(tasks): add delete button with confirmation"},
            {"Fixed the bug where the due date showed one day early",
                    "fix(tasks): correct off-by-one due date display"},
            {"Updated the README with how to set the API key",
                    "docs: explain ANTHROPIC_API_KEY setup in README"},
    };

    // ---------- 3. Chain of thought for code generation, with XML tags ----------
    public static final String JAVA_CODER_SYSTEM = """
            You are an expert Java 17 developer.
            First think through the problem step by step inside <plan> tags:
            the inputs, the edge cases, and the approach.
            Then write the complete Java method inside <code> tags.
            Do not use any library outside the JDK.
            """;

    // ---------- 4. Structured output: ask for JSON with an exact shape ----------
    public static final String TASK_EXTRACTOR_SYSTEM = """
            You turn a user's sentence into a task for a task manager app.
            Reply with ONLY a JSON object, no other text, in exactly this shape:
            {"title": string, "priority": "LOW" | "MEDIUM" | "HIGH", "dueDate": "YYYY-MM-DD" or null}
            Rules:
            - title: at most 60 characters, starts with a verb.
            - priority: HIGH if the sentence says urgent/ASAP/today, otherwise MEDIUM unless it says low priority.
            - dueDate: only if the sentence gives a date. Today is %s. Never invent a date.
            """;

    // ---------- 5. Pair programming lab: a deliberately weak first version ----------
    public static final String INTERVIEW_QUESTIONS_V1 = """
            Give me 5 interview questions for a %s developer.
            """;
}
