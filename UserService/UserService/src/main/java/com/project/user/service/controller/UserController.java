package com.project.user.service.controller;

import com.project.user.service.entities.UserEntity;
import com.project.user.service.payload.ApiResponse;
import com.project.user.service.services.UserService;
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
    public ResponseEntity<ApiResponse<UserEntity>> getUserById(@PathVariable String userId) {
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
    public ResponseEntity<ApiResponse<List<UserEntity>>> getAllUsers() {
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

}
