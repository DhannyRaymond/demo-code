package com.example.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    private static final Key secretKey = Keys.hmacShaKeyFor("UkBoYXNpYVBAa2VCNG5nZXROaWhsYWxhLg==".getBytes());

    public static String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .claim("email", email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12)) // 12 hours
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60)) // 1 minute
                .signWith(secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException ex) {
            throw new JwtException("Token tidak tidak valid atau kadaluwarsa");
        }
    }

    public static String getEmailFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("email", String.class);
    }

    public static String getTokenFromRequest(HttpServletRequest request) throws IOException {
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isNotEmpty(authorization)) {
            String[] authSplit = authorization.split(" ");
            return authSplit[1];
        } else {
            return null;
        }
    }

    public static String getEmailFromRequest(HttpServletRequest request) throws IOException {
        String token = getTokenFromRequest(request);
        return StringUtils.isNotEmpty(token) ? getEmailFromToken(token) : null;
    }
}
