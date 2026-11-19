package org.claimacademy.wednesdaytaskmanagerapi.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.time.Duration;
import java.time.Instant;

@Service
public class JwtService {
    private static final Logger log = LoggerFactory.getLogger(JwtService.class);
    private final SecretKey key;
    private final long accessMinutes;
    private final long refreshDays;

    public JwtService(@Value("${app.jwt.secret:}") String secret,
                      @Value("${app.jwt.access-minutes:15}") long accessMinutes,
                      @Value("${app.jwt.refresh-days:7}") long refreshDays){
        if(secret.isBlank()){
            log.warn("app.jwt.secret is not set. Using a random key: tokens stop working when the app restarts.");
            this.key = Jwts.SIG.HS256.key().build();
        }
        else {
            this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        }
        this.accessMinutes = accessMinutes;
        this.refreshDays = refreshDays;
    }

    public String generateAccessToken(String username){
        return buildToken(username, "access", Duration.ofMinutes(accessMinutes));
    }

    private String buildToken(String username, String type, Duration duration) {
        Instant now = Instant.now();

        return Jwts.builder()
                .subject(username)
                .claim("type", type)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(duration)))
                .signWith(key)
                .compact();
    }

    public String generateRefreshToken(String username){
        return  buildToken(username, "refresh", Duration.ofDays(refreshDays));
    }

    public Claims parse(String token){
        return  Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isValid(String token, String expectedType){
        try {
            return expectedType.equals(parse(token).get("type", String.class));
        }
        catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }
}
