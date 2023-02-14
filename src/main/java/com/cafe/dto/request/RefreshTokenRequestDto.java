package com.cafe.dto.request;

import java.util.Objects;

public class RefreshTokenRequestDto {
    private String username;

    public RefreshTokenRequestDto() {
    }

    public RefreshTokenRequestDto(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RefreshTokenRequestDto that = (RefreshTokenRequestDto) o;
        return Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return "RefreshTokenRequestDto{" +
                "username='" + username + '\'' +
                '}';
    }
}
