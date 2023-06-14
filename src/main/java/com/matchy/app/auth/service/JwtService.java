package com.matchy.app.auth.service;

public interface JwtService {

    String generateToken(String email);

    boolean validateToken(String token);

    boolean isExpired(String token);

    String getSub(String token);
}
