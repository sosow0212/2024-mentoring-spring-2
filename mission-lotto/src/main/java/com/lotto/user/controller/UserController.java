package com.lotto.user.controller;

import com.lotto.user.domain.entity.User;

import com.lotto.user.service.UserService;
import com.lotto.user.controller.dto.CreateUserResponse;

import com.lotto.user.service.dto.CreateUserRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        CreateUserResponse response = userService.registerUser(request.userName(), request.balance());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CreateUserResponse> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        CreateUserResponse response = new CreateUserResponse(
                user.getUserId(),
                user.getUserName(),
                user.getBalance(),
                user.getLottoCount(),
                user.getWinning()
        );
        return ResponseEntity.ok(response);
    }


    @GetMapping("/allUsers")
    public ResponseEntity<List<CreateUserResponse>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<CreateUserResponse> response = users
                .stream()
                .map(user -> new CreateUserResponse(
                        user.getUserId(),
                        user.getUserName(),
                        user.getBalance(),
                        user.getLottoCount(),
                        user.getWinning()
                ))
                .toList();
        return ResponseEntity.ok(response);
    }

}