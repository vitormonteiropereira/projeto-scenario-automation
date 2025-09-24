package com.scenarioautomation.demo.auth;

import com.scenarioautomation.demo.auth.dto.AuthRequest;
import com.scenarioautomation.demo.auth.dto.AuthResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        if (!authService.isValid(request.username(), request.password())) {
            return ResponseEntity.status(401).build();
        }
        String token = jwtUtil.generateToken(request.username());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
