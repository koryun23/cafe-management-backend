package com.cafe.dto.response;

import com.cafe.entity.user.UserRoleType;

import java.util.List;
import java.util.Objects;

public class AuthenticationResponseDto {

    private String token;
    private long expiresIn;
    private String username;
    private String password;
    private String firstName;
    private String secondName;
    private List<UserRoleType> role;
    private String type = "Bearer ";
    private String message;

    public AuthenticationResponseDto() {
    }

    public AuthenticationResponseDto(String message) {
        this.message = message;
    }

    public AuthenticationResponseDto(String token,
                                     long expiresIn,
                                     String username,
                                     String password,
                                     String firstName,
                                     String secondName,
                                     List<UserRoleType> role) {
        this.token = token;
        this.expiresIn = expiresIn;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<UserRoleType> getRole() {
        return role;
    }

    public void setRole(List<UserRoleType> role) {
        this.role = role;
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
        AuthenticationResponseDto that = (AuthenticationResponseDto) o;
        return Objects.equals(token, that.token) &&
                expiresIn == that.expiresIn &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(secondName, that.secondName) &&
                Objects.equals(type, that.type) &&
                Objects.equals(message, that.message) &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, expiresIn, username, password, firstName, secondName, type, message, role);
    }

    @Override
    public String toString() {
        return "AuthenticationResponseDto{" +
                "token='" + token + '\'' +
                "expiresIn='" + expiresIn + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", type='" + type + '\'' +
                ", message='" + message + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
