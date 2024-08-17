package com.web.user.controller;

import com.web.user.controller.dto.CreateUserResponse;
import com.web.user.service.UserService;
import com.web.user.controller.dto.CreateUserRequest;

import com.web.user.domain.entity.User;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> registerUser(@RequestBody CreateUserRequest request) {
        User user = userService.registerUser(request.userName(), request.balance());
        CreateUserResponse response = toCreateUserResponse(user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CreateUserResponse> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        CreateUserResponse response = toCreateUserResponse(user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<CreateUserResponse>> getAllUsers() {
        List<User> users = userService
                .getAllUsers();
        List<CreateUserResponse> response = users.stream()
                .map(user -> new CreateUserResponse(
                        user.getUserId(),
                        user.getUserName(),
                        user.getBalance(),
                        user.getLottoCount()))
                .toList();
        return ResponseEntity.ok(response);
    }

    private static CreateUserResponse toCreateUserResponse(final User user) {
        return new CreateUserResponse(
                user.getUserId(),
                user.getUserName(),
                user.getBalance(),
                user.getLottoCount());
    }
}
