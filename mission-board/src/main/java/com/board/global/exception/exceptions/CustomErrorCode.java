package com.board.global.exception.exceptions;

public enum CustomErrorCode {

    ALREADY_EXIST_MEMBER_ID(400, "유저 아이디 이미 존재합니다."),
    ALREADY_EXIST_MEMBER_NICKNAME(400, "유저 닉네임 이미 존재합니다.");

    private final int status;
    private final String message;

    CustomErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
