package com.cafe.entity.user;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

//TODO:ADD PASSWORD
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ID_SEQUENCE")
    @SequenceGenerator(name = "USER_ID_SEQUENCE")
    private Long id;

    @Column(name = "first_name", nullable = false, length = 40)
    private String firstName;

    @Column(name = "second_name", nullable = false, length = 40)
    private String secondName;

    @Column(name = "username", nullable = false, length = 30)
    private String username;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<UserRole> userRoleList;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column
    private String password;

    public User() {
    }

    public User(String firstName, String secondName, String username, String password, LocalDateTime createdAt) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<UserRole> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<UserRole> userRoleList) {
        this.userRoleList = userRoleList;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(secondName, user.secondName) &&
                Objects.equals(username, user.username) &&
                Objects.equals(userRoleList, user.userRoleList) &&
                Objects.equals(createdAt, user.createdAt) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, secondName, username, userRoleList, createdAt, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", username='" + username + '\'' +
                ", userRoleList=" + userRoleList +
                ", createdAt=" + createdAt +
                '}';
    }
}
