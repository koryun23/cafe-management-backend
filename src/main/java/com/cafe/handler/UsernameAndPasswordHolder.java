package com.cafe.handler;

import java.util.Objects;

public class UsernameAndPasswordHolder {

    private String username;
    private String password;

    public UsernameAndPasswordHolder(String username, String password) {
        this.username = username;
        this.password = password;
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
        UsernameAndPasswordHolder that = (UsernameAndPasswordHolder) o;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return "UsernameAndPasswordHolder{" +
                "username='" + username + '\'' +
                ", password'***" + '\'' +
                '}';
    }
}
