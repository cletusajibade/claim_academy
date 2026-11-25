package com.claimacademy.promptlab.prompts;

/** Claude sometimes wraps JSON in ```json fences or adds a sentence. Take the {...} part only. */
public final class JsonText {

    private JsonText() {
    }

    public static String extractObject(String text) {
        if (text == null) {
            return "";
        }
        int start = text.indexOf('{');
        int end = text.lastIndexOf('}');
        if (start < 0 || end < start) {
            return text;            // no object at all: let Jackson report the error
        }
        return text.substring(start, end + 1);
    }
}
