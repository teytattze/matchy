package com.matchy.app.auth.service.impl;

import com.matchy.app.auth.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {
    static final String ISSUER = "matchy";

    static final long TTL = 3600000;
    private final Key signingKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @Override
    public String generateToken(String email) {
        return Jwts
            .builder()
            .setSubject(email)
            .setIssuer(ISSUER)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + TTL))
            .signWith(signingKey)
            .compact();
    }

    @Override
    public boolean validateToken(String token) {
        return getSub(token) != null && !isExpired(token);
    }

    @Override
    public boolean isExpired(String token) {
        return getClaims(token)
            .getExpiration()
            .after(new Date(System.currentTimeMillis()));
    }

    @Override
    public String getSub(String token) {
        return getClaims(token).getSubject();
    }

    private Claims getClaims(String token) {
        return Jwts
            .parserBuilder()
            .setSigningKey(signingKey)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
}
