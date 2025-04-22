package com.store.cont4bl3.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    private final String SECRET_KEY = "ProyectoCont4bl3ACL"; // usa la misma que en auth-service

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        Claims claims = extractClaims(token);

        // Primero intenta leer el `sub`, como debe ser
        String username = claims.getSubject();

        // Si no está, intenta con el claim explícito `usuario`
        if (username == null && claims.get("usuario") != null) {
            username = claims.get("usuario", String.class);
        }

        return username;
    }
}
