package com.lotto.user.controller.dto;

public record CreateUserResponse(Long userId, String userName, int balance, int lottoCount) {

}