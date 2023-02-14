package com.cafe.controller;

import com.cafe.dto.request.RefreshTokenRequestDto;
import com.cafe.dto.response.RefreshTokenResponseDto;
import com.cafe.service.core.jwt.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "refresh-token", consumes = "application/json", produces = "application/json")
public class RefreshTokenController {

    private JwtService jwtService;

    @Value("${jwt.token.expiration}")
    private long expiresIn;

    public RefreshTokenController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<RefreshTokenResponseDto> refreshToken(@RequestBody RefreshTokenRequestDto dto) {
        System.out.println(dto.getUsername());
        String refreshToken = jwtService.getRefreshToken(dto.getUsername());
        return ResponseEntity.status(HttpStatus.OK).body(new RefreshTokenResponseDto(refreshToken, expiresIn));
    }
}
