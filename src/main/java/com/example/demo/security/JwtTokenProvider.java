package com.example.demo.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String secret = "mysecretkey123456";
    private final long validity = 86400000;

    public JwtTokenProvider() {}

    public JwtTokenProvider(String secret, long validity) {
        this.validity = validity;
    }

    public String generateToken(Long userId, String email, String role) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .claim("email", email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validity))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getUserIdFromToken(String token) {
        return Long.parseLong(
                Jwts.parser().setSigningKey(secret)
                        .parseClaimsJws(token)
                        .getBody().getSubject()
        );
    }
}
