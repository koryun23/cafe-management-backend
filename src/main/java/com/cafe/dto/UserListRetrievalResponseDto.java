package com.cafe.dto;

import com.cafe.entity.user.User;

import java.util.List;
import java.util.Objects;

public class UserListRetrievalResponseDto {

    private List<UserRegistrationResponseDto> userList;

    public UserListRetrievalResponseDto(List<UserRegistrationResponseDto> userList) {
        this.userList = userList;
    }

    public List<UserRegistrationResponseDto> getUserList() {
        return userList;
    }

    public void setUserList(List<UserRegistrationResponseDto> userList) {
        this.userList = userList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserListRetrievalResponseDto that = (UserListRetrievalResponseDto) o;
        return Objects.equals(userList, that.userList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userList);
    }

    @Override
    public String toString() {
        return "UserListRetrievalResponseDto{" +
                "userDtoList=" + userList +
                '}';
    }
}
