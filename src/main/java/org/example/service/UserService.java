package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.request.UserDtoRequest;
import org.example.dto.response.UserDtoResponse;
import org.example.entity.UserEntity;
import org.example.exception.UserNotFoundException;
import org.example.mapper.UserMapper;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDtoResponse createUser(UserDtoRequest request) {
        UserEntity user = userRepository.save(UserEntity.builder()
                .username(request.getName())
                .build());
        return userMapper.toUserDtoResponse(user);
    }

    public UserDtoResponse getById(Long id) {
        UserEntity user = findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return userMapper.toUserDtoResponse(user);
    }

    public List<UserDtoResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserDtoResponse)
                .toList();
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public UserDtoResponse updateUser(UserEntity user) {
        findById(user.getId());
        return userMapper.toUserDtoResponse(userRepository.save(user));
    }

    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }
}

