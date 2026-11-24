package com.claimacademy.taskmanager.auth;

import com.claimacademy.taskmanager.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    /** POST /api/auth/login  { "username": "demo", "password": "password123" } */
    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        // 1. Check username + password (BCrypt). Wrong → BadCredentialsException → 401
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        // 2. Correct → hand out a signed token
        JwtService.IssuedToken issued = jwtService.generateToken(request.username());
        return new AuthResponse(issued.token(), request.username(), issued.expiresAt());
    }
}
