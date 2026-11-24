package com.claimacademy.helloclaude.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientResponseException;

import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    /** Our own request was invalid (e.g. blank question) → 400 */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> badRequest(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return ResponseEntity.badRequest().body(Map.of("error", message));
    }

    /** Claude's API answered with an error (401 bad key, 429 rate limit, 529 overloaded…) → 502 */
    @ExceptionHandler(RestClientResponseException.class)
    public ResponseEntity<Map<String, String>> claudeError(RestClientResponseException ex) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(Map.of(
                "error", "The AI service returned " + ex.getStatusCode().value(),
                "details", ex.getResponseBodyAsString()));   // fine for class; hide this in production
    }

    /** Missing API key → 500 with a clear message */
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, String>> notConfigured(IllegalStateException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", ex.getMessage()));
    }
}
