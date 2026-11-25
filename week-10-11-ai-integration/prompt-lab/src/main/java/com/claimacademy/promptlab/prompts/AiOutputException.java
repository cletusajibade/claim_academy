package com.claimacademy.promptlab.prompts;

/** Claude answered, but not in the shape we asked for. Never pass such output on to users. */
public class AiOutputException extends RuntimeException {

    private final String rawOutput;

    public AiOutputException(String message, String rawOutput) {
        super(message);
        this.rawOutput = rawOutput;
    }

    public String getRawOutput() {
        return rawOutput;
    }
}
