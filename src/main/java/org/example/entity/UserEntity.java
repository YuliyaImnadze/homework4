package org.example.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    public UserEntity() {}

    public UserEntity(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public UserEntity(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof UserEntity)) {
            return false;
        }

        final var other = (UserEntity) o;

        return id != null && id.equals(other.getId());
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", username='" + username + "'}";
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

