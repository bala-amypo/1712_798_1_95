package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    public boolean validateToken(String token) {
        return true;
    }

    public String getEmailFromToken(String token) {
        return "user@example.com";
    }
}
