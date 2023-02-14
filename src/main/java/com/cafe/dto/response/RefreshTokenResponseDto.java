package com.cafe.dto.response;

import java.util.Objects;

public class RefreshTokenResponseDto {
    private String refreshToken;
    private long expiresIn;
    public RefreshTokenResponseDto(String refreshToken, long expiresIn) {
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RefreshTokenResponseDto that = (RefreshTokenResponseDto) o;
        return Objects.equals(refreshToken, that.refreshToken) &&
                expiresIn == that.expiresIn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(refreshToken, expiresIn);
    }

    @Override
    public String toString() {
        return "RefreshTokenResponseDto{" +
                "refreshToken='" + refreshToken + '\'' +
                "expiresIn='" + expiresIn + '\'' +
                '}';
    }
}
