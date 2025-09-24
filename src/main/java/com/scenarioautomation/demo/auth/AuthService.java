package com.scenarioautomation.demo.auth;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    // Usuários hardcoded (user -> password)
    private final Map<String, String> users = new HashMap<>();

    public AuthService() {
        users.put("admin", "admin123");
        users.put("user", "user123");
    }

    public boolean isValid(String username, String password) {
        String expected = users.get(username);
        return expected != null && expected.equals(password);
    }
}
