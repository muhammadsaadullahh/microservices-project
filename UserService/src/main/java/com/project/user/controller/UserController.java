package com.project.user.controller;

import com.project.user.entities.UserEntity;
import com.project.user.exceptions.ResourceNotFoundException;
import com.project.user.payload.ApiResponse;
import com.project.user.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserEntity>> getUserById(@PathVariable String userId) throws ResourceNotFoundException {
        UserEntity user = userService.getUserById(userId);
        return ResponseEntity.ok(
                ApiResponse
                        .<UserEntity>builder()
                        .success(true)
                        .message("User fetched successfully")
                        .entity(user)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserEntity>>> getAllUsers() throws ResourceNotFoundException {
        List<UserEntity> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(
                ApiResponse
                        .<List<UserEntity>>builder()
                        .success(true)
                        .message("All Users fetched successfully")
                        .entity(allUsers)
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserEntity>> createUser(@RequestBody UserEntity user) {
        final boolean usernameExists = userService.existsByUsername(user.getUsername());
        if(usernameExists) {
            throw new ResourceNotFoundException("User already exists with username: " + user.getUsername());
        }
        UserEntity createUser = userService.createUser(user);
        return ResponseEntity.ok(
                ApiResponse
                        .<UserEntity>builder()
                        .success(true)
                        .message("User created successfully")
                        .entity(createUser)
                        .build()
        );
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserEntity>> deleteUser(@PathVariable String userId) throws  ResourceNotFoundException {
        final boolean userExists = userService.existsByUserId(userId);
        if(!userExists) {
            throw new ResourceNotFoundException("User not found with ID: " + userId);
        }
        UserEntity deleteUser = userService.deleteUser(userId);
        return ResponseEntity.ok(
                ApiResponse
                        .<UserEntity>builder()
                        .success(true)
                        .message("User with ID " + userId + " deleted successfully")
                        .entity(deleteUser)
                        .build()
        );
    }
}
