package com.project.user.services.impl;

import com.project.user.entities.UserEntity;
import com.project.user.exceptions.ResourceNotFoundException;
import com.project.user.repositories.UserRepository;
import com.project.user.services.UserService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity createUser(UserEntity user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public UserEntity updateUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public UserEntity deleteUser(String id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
        return user;
    }

    @Override
    public UserEntity getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean existsByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            return false;
        }
        return userRepository.existsByUsernameIgnoreCase(username);
    }

    @Override
    public boolean existsByUserId(String userId) {
        if (StringUtils.isBlank(userId)) {
            return false;
        }
        return userRepository.existsByUserIdIgnoreCase(userId);
    }
}
