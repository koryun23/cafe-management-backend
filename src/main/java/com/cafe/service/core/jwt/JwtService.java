package com.cafe.service.core.jwt;

public interface JwtService {

    String createToken(String username);

    String getUsername(String token);
}
