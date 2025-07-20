package org.example.dao;

import org.example.entity.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao {
    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User save(User user) throws SQLException {
        String sql = "INSERT INTO users (username) VALUES (?) RETURNING id";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setId(rs.getLong("id"));
                return user;
            } else {
                throw new SQLException("Error during saving user with username %s"
                        .formatted(user.getUsername()));
            }
        }
    }

    public Optional<User> findById(Long id) throws SQLException {
        String sql = "SELECT id, username FROM users WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getLong("id"), rs.getString("username"));
                return Optional.of(user);
            }
            return Optional.empty();
        }
    }

    public List<User> findAll() throws SQLException {
        String sql = "SELECT id, username FROM users";
        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                users.add(new User(rs.getLong("id"), rs.getString("username")));
            }
        }
        return users;
    }

    public void delete(User user) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, user.getId());
            ps.executeUpdate();
        }
    }

    public User update(User user) throws SQLException {
        String sql = "UPDATE users SET username = ? WHERE id = ? RETURNING id, username";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setLong(2, user.getId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getLong("id"), rs.getString("username"));
                } else {
                    throw new SQLException("User with id = %d not found for update".formatted(user.getId()));
                }
            }
        }
    }

}

