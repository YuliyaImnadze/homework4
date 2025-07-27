package org.example.service;

import org.example.entity.UserEntity;
import org.example.exception.UserNotFoundException;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity createUser(String username) {
        return userRepository.save(new UserEntity(username));
    }

    public UserEntity findById(Long id) {
       return userRepository.findById(id)
               .orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public UserEntity updateUser(UserEntity user) {
        findById(user.getId());
        return userRepository.save(user);
    }
}

