package org.claimacademy.wednesdaytaskmanagerapi.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.claimacademy.wednesdaytaskmanagerapi.exception.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/demo")
@Tag(name = "JWT Demo", description = "Teaching demo only. Real login and route protection come later in the course.")
public class JwtDemoController {
    public record TokenRequest(@NotBlank(message = "must not be blank") String username) {
    }

    public record RefreshRequest(@NotBlank(message = "must not be blank") String refreshToken) {
    }

    private final JwtService jwtService;

    public JwtDemoController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/token")
    @Operation(summary = "Get an access token and a refresh token for any username (demo: no password)")
    public Map<String, Object> issueTokens(@Valid @RequestBody TokenRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("accessToken", jwtService.generateAccessToken(request.username()));
        body.put("refreshToken", jwtService.generateRefreshToken(request.username()));
        body.put("tokenType", "Bearer");

        return body;
    }
    @GetMapping("/verify")
    @Operation(summary = "Send Authorization: Bearer <accessToken> to see its claims, or a 401")
    public ResponseEntity<Object> verify(@RequestHeader(value = "Authorization", required = false) String authorization){
        if(authorization==null || !authorization.startsWith("Bearer ")){
            return unauthorized("Missing header. Send:  Authorization: Bearer <token>");
        }

        String token = authorization.substring("Bearer ".length()).trim();
        if(!jwtService.isValid(token,"access")){
            return unauthorized("Token is invalid, expired, or not an access token");
        }

        Claims claims = jwtService.parse(token);
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("subject", claims.getSubject());
        body.put("type", claims.get("type", String.class));
        body.put("issuedAt", claims.getIssuedAt().toInstant());
        body.put("expiresAt", claims.getExpiration().toInstant());
        return ResponseEntity.ok(body);

    }

    @PostMapping("/refresh")
    @Operation(summary = "Trade a valid refresh token for a new access token")
    public ResponseEntity<Object> refresh(@Valid @RequestBody RefreshRequest request) {
        if (!jwtService.isValid(request.refreshToken(), "refresh")) {
            return unauthorized("Refresh token is invalid, expired, or not a refresh token");
        }
        try {
            String username = jwtService.parse(request.refreshToken()).getSubject();
            return ResponseEntity.ok(Map.of("accessToken", jwtService.generateAccessToken(username)));
        } catch (JwtException e) {
            return unauthorized("Refresh token is invalid");
        }
    }


    private ResponseEntity<Object> unauthorized(String message) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status)
                .body(new ApiError(status.value(), status.getReasonPhrase(), message, Instant.now()));
    }

}
