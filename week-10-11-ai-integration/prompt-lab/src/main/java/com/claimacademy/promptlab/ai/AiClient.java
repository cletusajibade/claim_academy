package com.claimacademy.promptlab.ai;

/**
 * Our own small interface in front of the Claude SDK.
 * - The rest of the app doesn't depend on the SDK's classes.
 * - Tests can pass a fake AiClient (a lambda!) instead of calling the real API.
 */
@FunctionalInterface
public interface AiClient {

    AiAnswer complete(AiRequest request);
}
