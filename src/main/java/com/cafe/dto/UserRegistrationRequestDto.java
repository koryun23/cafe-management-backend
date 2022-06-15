package com.cafe.dto;

import com.cafe.entity.user.UserRole;
import com.cafe.entity.user.UserRoleType;

import java.util.List;
import java.util.Objects;

public class UserRegistrationRequestDto {

    private String username;
    private String password;
    private String firstName;
    private String secondName;
    private List<UserRoleType> roleList;

    public UserRegistrationRequestDto(String username, String password, String firstName, String secondName, List<UserRoleType> roleList) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.roleList = roleList;
    }

    public UserRegistrationRequestDto() {
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

    public List<UserRoleType> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<UserRoleType> roleList) {
        this.roleList = roleList;
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
        UserRegistrationRequestDto that = (UserRegistrationRequestDto) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(secondName, that.secondName) &&
                Objects.equals(roleList, that.roleList) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, firstName, secondName, roleList, password);
    }

    @Override
    public String toString() {
        return "UserRegistrationRequestDto{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", roleList=" + roleList +
                '}';
    }
}
