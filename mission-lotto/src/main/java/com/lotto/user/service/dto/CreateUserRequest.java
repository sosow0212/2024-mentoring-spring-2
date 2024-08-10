package com.lotto.user.service.dto;

public record CreateUserRequest(Long userId, String userName, int balance, int lottoCount) {

}