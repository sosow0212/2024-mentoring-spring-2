package com.web.user.controller.dto;

public record CreateUserRequest(Long userId, String userName, int balance, int lottoCount) {

}