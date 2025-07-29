package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity<Long> {

    @Column(name = "username", unique = true)
    private String username;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<ProductEntity> products;

    @Override
    public String toString() {
        return "User{id=" + this.getId() + ", username='" + username + "'}";
    }
}

