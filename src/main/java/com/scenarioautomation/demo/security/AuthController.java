package com.scenarioautomation.demo.security;



import com.scenarioautomation.demo.security.dto.LoginRequest;
import com.scenarioautomation.demo.security.dto.TokenResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;
    private final JwtUtil jwt;

    public AuthController(JwtUtil jwt, AuthService authService) {
        this.jwt = jwt;
        this.authService = authService;
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest body) {


        if (this.authService.isValid(body.getUsername(), body.getPassword())) {
            String token = jwt.generate(body.getUsername());
            return new TokenResponse(token, "Bearer");
        }
        // simples e direto
        throw new RuntimeException("Usuário ou senha inválidos");
    }
}

