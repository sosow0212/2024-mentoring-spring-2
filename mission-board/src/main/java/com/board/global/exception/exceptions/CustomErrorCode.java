package com.board.global.exception.exceptions;

public enum CustomErrorCode {

    ALREADY_EXIST_MEMBER_ID(400, "유저 아이디 이미 존재합니다."),
    ALREADY_EXIST_MEMBER_NICKNAME(400, "유저 닉네임 이미 존재합니다."),
    NOT_EXIST_MEMBER_LOGIN_ID(400, "아이디가 일치하지 않습니다."),
    NOT_EXIST_MEMBER_PASSWORD(400, "비밀번호가 일치하지 않습니다."),
    NOT_EXIST_COOKIE(404, "해당 쿠키가 존재하지 않습니다.");

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
