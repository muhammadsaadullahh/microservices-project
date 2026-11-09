package com.project.user.service.services;

import com.project.user.service.entities.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity createUser(UserEntity user);
    UserEntity updateUser(UserEntity user);
    UserEntity deleteUser(String id);
    UserEntity getUserById(String id);
    List<UserEntity> getAllUsers();
}
