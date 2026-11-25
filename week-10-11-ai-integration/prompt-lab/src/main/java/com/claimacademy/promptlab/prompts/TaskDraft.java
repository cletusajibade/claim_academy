package com.claimacademy.promptlab.prompts;

/** The JSON shape we asked Claude for. Jackson turns Claude's text into this record. */
public record TaskDraft(String title, String priority, String dueDate) {
}
