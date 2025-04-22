package com.store.cont4bl3.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    private final String SECRET_KEY = "D@c9Jk#7wY!qZx8R3tGfLm2VbNpAsE4u";
    public Claims extractClaims(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7); // quitar "Bearer "
            System.out.println(">>> Token recibido: " + token);
            System.out.println(">>> Secret usada: " + SECRET_KEY);
        }
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
