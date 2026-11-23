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
        if (userId == null || userId.isBlank()) {
            throw new ResourceNotFoundException("User ID must not be blank");
        }
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
        validateUser(user);
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
        if (userId == null || userId.isBlank()) {
            throw new ResourceNotFoundException("User ID must not be blank");
        }
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

    private void validateUser(UserEntity user) {
        if (user == null) {
            throw new ResourceNotFoundException("User payload must not be null");
        }
        if (user.getFirstName() == null || user.getFirstName().isBlank()) {
            throw new ResourceNotFoundException("First name must not be blank");
        }
        if (user.getLastName() == null || user.getLastName().isBlank()) {
            throw new ResourceNotFoundException("Last name must not be blank");
        }
        if (user.getUsername() == null || user.getUsername().isBlank()) {
            throw new ResourceNotFoundException("Username must not be blank");
        }
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new ResourceNotFoundException("Email must not be blank");
        }
        if (!user.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new ResourceNotFoundException("Email format is invalid");
        }
    }
}
