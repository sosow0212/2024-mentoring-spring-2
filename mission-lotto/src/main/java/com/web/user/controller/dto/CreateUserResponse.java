package com.web.user.controller.dto;

public record CreateUserResponse(Long userId, String userName, int balance, int lottoCount) {

}