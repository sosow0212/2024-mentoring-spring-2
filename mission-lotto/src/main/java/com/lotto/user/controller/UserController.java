package com.lotto.user.controller;

import com.lotto.user.domain.entity.User;

import com.lotto.user.service.UserService;
import com.lotto.user.service.dto.CreateUserRequest;

import com.lotto.user.controller.dto.CreateUserResponse;

import org.springframework.http.HttpStatus;
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
    public ResponseEntity<CreateUserRequest> registerUser(@RequestBody CreateUserResponse request) {
        CreateUserRequest response = userService.registerUser(request.userName(), request.balance());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CreateUserRequest> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        CreateUserRequest response = new CreateUserRequest(
                user.getUserId(),
                user.getUserName(),
                user.getBalance(),
                user.getLottoCount()
        );
        return ResponseEntity.ok(response);
    }


    @GetMapping("/allUsers")
    public ResponseEntity<List<CreateUserRequest>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<CreateUserRequest> response = users
                .stream()
                .map(user -> new CreateUserRequest(
                        user.getUserId(),
                        user.getUserName(),
                        user.getBalance(),
                        user.getLottoCount()
                ))
                .toList();
        return ResponseEntity.ok(response);
    }

}