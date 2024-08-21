package com.board.global.exception.exceptions;

import org.springframework.http.HttpStatus;

public enum CustomErrorCode {

    ALREADY_EXIST_MEMBER_ID(HttpStatus.BAD_REQUEST, "A001", "유저 아이디 이미 존재합니다."),
    ALREADY_EXIST_MEMBER_NICKNAME(HttpStatus.BAD_REQUEST, "A002", "유저 닉네임 이미 존재합니다."),
    NOT_EXIST_MEMBER_LOGIN_ID(HttpStatus.BAD_REQUEST,"A003", "아이디가 일치하지 않습니다."),
    NOT_EXIST_MEMBER_PASSWORD(HttpStatus.BAD_REQUEST,"A004", "비밀번호가 일치하지 않습니다."),
    NOT_EXIST_MEMBER(HttpStatus.BAD_REQUEST, "A005", "유저가 존재하지 않습니다."),
    NOT_EXIST_COOKIE(HttpStatus.NO_CONTENT, "C001", "해당 쿠키가 존재하지 않습니다."),
    NOT_EXIST_SESSION(HttpStatus.BAD_REQUEST, "S001", "해당 세션이 존재하지 않습니다.");


    private final HttpStatus httpStatus;
    private final String customCode;
    private final String message;

    CustomErrorCode(HttpStatus httpStatus, String customCode, String message) {
        this.httpStatus = httpStatus;
        this.customCode = customCode;
        this.message = message;
    }

    public HttpStatus getHttpStatus(){
        return httpStatus;
    }

    public String getCustomCode(){
        return customCode;
    }
    public String getMessage() {
        return message;
    }
}
