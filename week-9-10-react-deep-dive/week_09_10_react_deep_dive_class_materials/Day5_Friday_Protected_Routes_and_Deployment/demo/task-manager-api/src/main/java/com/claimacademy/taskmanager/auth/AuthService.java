package com.claimacademy.taskmanager.auth;

import com.claimacademy.taskmanager.exception.ConflictException;
import com.claimacademy.taskmanager.security.JwtService;
import com.claimacademy.taskmanager.user.AppUser;
import com.claimacademy.taskmanager.user.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        String username = request.username().trim();
        if (userRepository.existsByUsername(username)) {
            throw new ConflictException("Username '" + username + "' is already taken");
        }
        userRepository.save(new AppUser(username, passwordEncoder.encode(request.password())));
        return issueToken(username);
    }

    public AuthResponse login(LoginRequest request) {
        // Throws BadCredentialsException if the username/password pair is wrong
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        return issueToken(request.username());
    }

    private AuthResponse issueToken(String username) {
        JwtService.IssuedToken issued = jwtService.generateToken(username);
        return new AuthResponse(issued.token(), username, issued.expiresAt());
    }
}
