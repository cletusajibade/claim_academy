package com.claimacademy.helloclaude.web;

import com.anthropic.errors.AnthropicIoException;
import com.anthropic.errors.AnthropicServiceException;
import com.anthropic.errors.RateLimitException;
import com.anthropic.errors.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/** The SDK throws a different exception for each kind of failure. Turn each into a clear HTTP answer. */
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> badRequest(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return ResponseEntity.badRequest().body(Map.of("error", message));
    }

    /** 401 from Anthropic: wrong or missing API key. Our server's fault, not the user's. */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Map<String, String>> badKey(UnauthorizedException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "The server's AI API key is invalid."));
    }

    /** 429 even after the SDK's automatic retries: too many requests. */
    @ExceptionHandler(RateLimitException.class)
    public ResponseEntity<Map<String, String>> rateLimited(RateLimitException ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Map.of("error", "The AI service is busy. Please try again in a moment."));
    }

    /** Any other error status from Anthropic (400, 500, 529 overloaded…). */
    @ExceptionHandler(AnthropicServiceException.class)
    public ResponseEntity<Map<String, String>> aiError(AnthropicServiceException ex) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(Map.of("error", "The AI service returned " + ex.statusCode()));
    }

    /** Network problem or timeout: we never got an answer. */
    @ExceptionHandler(AnthropicIoException.class)
    public ResponseEntity<Map<String, String>> network(AnthropicIoException ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Map.of("error", "Could not reach the AI service."));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, String>> notConfigured(IllegalStateException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", ex.getMessage()));
    }
}
