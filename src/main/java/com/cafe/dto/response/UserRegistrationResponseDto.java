package com.cafe.dto.response;

import com.cafe.entity.user.UserRoleType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class UserRegistrationResponseDto {

    private String username;
    private String password;
    private String firstName;
    private String secondName;
    private List<UserRoleType> roleList;
    private LocalDateTime registeredAt;

    private List<String> errors;
    public UserRegistrationResponseDto(String username,
                                       String password,
                                       String firstName,
                                       String secondName,
                                       List<UserRoleType> roleList,
                                       LocalDateTime registeredAt) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.roleList = roleList;
        this.registeredAt = registeredAt;
    }

    public UserRegistrationResponseDto(List<String> errors) {
        this.errors = errors;
    }

    public UserRegistrationResponseDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRoleType> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<UserRoleType> roleList) {
        this.roleList = roleList;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRegistrationResponseDto that = (UserRegistrationResponseDto) o;
        return Objects.equals(username, that.username) && Objects.equals(firstName, that.firstName) && Objects.equals(secondName, that.secondName) && Objects.equals(registeredAt, that.registeredAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, firstName, secondName, registeredAt);
    }

    @Override
    public String toString() {
        return "UserRegistrationResponseDto{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", password=" + password +
                ", registeredAt=" + registeredAt +
                ", errors=" + errors +
                '}';
    }
}
