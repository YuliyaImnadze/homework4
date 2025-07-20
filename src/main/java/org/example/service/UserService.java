package org.example.service;

import org.example.dao.UserDao;
import org.example.entity.User;
import org.example.exception.UserNotFoundException;
import org.example.exception.UserServiceException;
import org.springframework.util.Assert;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User createUser(String username) {
        try {
            return userDao.save(new User(username));
        } catch (SQLException e) {
            throw new UserServiceException("Failed to create user with username %s".formatted(username), e);
        }
    }

    public User findById(Long id) {
        Assert.notNull(id, "The given id must not be null");
        try {
            return userDao.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        } catch (SQLException e) {
            throw new UserServiceException("Failed to get user with id = %d".formatted(id), e);
        }
    }

    public List<User> getAllUsers() {
        try {
            return userDao.findAll();
        } catch (SQLException e) {
            throw new UserServiceException("Failed to get users", e);
        }
    }

    public void deleteUserById(Long id) {
        Assert.notNull(id, "The given id must not be null");
        try {
            userDao.delete(this.findById(id));
        } catch (SQLException e) {
            throw new UserServiceException("Failed to delete user with id = %d".formatted(id), e);
        }
    }

    public User updateUser(User user) {
        this.findById(user.getId());
        try {
            return userDao.update(user);
        } catch (SQLException e) {
            throw new UserServiceException("Failed to update user with id = %d".formatted(user.getId()), e);
        }
    }
}

