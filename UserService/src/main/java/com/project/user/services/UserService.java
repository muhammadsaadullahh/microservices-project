package com.project.user.services;

import com.project.user.entities.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity createUser(UserEntity user);
    UserEntity updateUser(UserEntity user);
    UserEntity deleteUser(String id);
    UserEntity getUserById(String id);
    List<UserEntity> getAllUsers();
    boolean existsByUsername(String username);
    boolean existsByUserId(String userId);
}
