package com.cafe.service.core.user;

import java.util.Objects;

public class UserCreationParams {
    private String firstName;
    private String secondName;
    private String username;
    private String password;

    public UserCreationParams(String firstName, String secondName, String username, String password) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.username = username;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCreationParams that = (UserCreationParams) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(secondName, that.secondName) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName, username, password);
    }

    @Override
    public String toString() {
        return "UserCreationParams{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
