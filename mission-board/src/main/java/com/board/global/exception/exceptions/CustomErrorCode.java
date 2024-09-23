package com.board.global.exception.exceptions;

import org.springframework.http.HttpStatus;

public enum CustomErrorCode {

    ALREADY_EXIST_MEMBER_ID(HttpStatus.BAD_REQUEST, "A001", "유저 아이디 이미 존재합니다."),
    ALREADY_EXIST_MEMBER_NICKNAME(HttpStatus.BAD_REQUEST, "A002", "유저 닉네임 이미 존재합니다."),
    NOT_EXIST_MEMBER_LOGIN_ID(HttpStatus.BAD_REQUEST, "A003", "아이디가 일치하지 않습니다."),
    NOT_EXIST_MEMBER_PASSWORD(HttpStatus.BAD_REQUEST, "A004", "비밀번호가 일치하지 않습니다."),
    NOT_EXIST_MEMBER(HttpStatus.BAD_REQUEST, "A005", "유저가 존재하지 않습니다."),
    NOT_EXIST_ARTICLE(HttpStatus.BAD_REQUEST, "B001", "게시된 글이 존재하지 않습니다."),
    DO_NOT_HAVE_RIGHT_ARTICLE(HttpStatus.BAD_REQUEST, "B002", "게시글에 대한 권한이 존재하지 않습니다."),
    NOT_EXIST_COMMENT(HttpStatus.BAD_REQUEST, "C001", "댓글이 존재하지 않습니다."),
    DO_NOT_HAVE_RIGHT_COMMENT(HttpStatus.BAD_REQUEST, "C002", "댓글에 대한 권한이 존재하지 않습니다."),
    NOT_EXIST_COOKIE(HttpStatus.NO_CONTENT, "L001", "해당 쿠키가 존재하지 않습니다."),
    NOT_EXIST_SESSION(HttpStatus.BAD_REQUEST, "L002", "해당 세션 유저가 존재하지 않습니다."),
    NOT_EXIST_TOKEN(HttpStatus.BAD_REQUEST, "T001", "해당 토큰이 존재하지 않습니다."),
    CAN_NOT_VERIFY_TOKEN(HttpStatus.BAD_REQUEST, "T002", "해당 토큰을 검증하지 못했습니다."),
    EXPIRE_TOKEN(HttpStatus.BAD_REQUEST, "T003", "해당 토큰을 검증하지 못했습니다.");


    private final HttpStatus httpStatus;
    private final String customCode;
    private final String message;

    CustomErrorCode(HttpStatus httpStatus, String customCode, String message) {
        this.httpStatus = httpStatus;
        this.customCode = customCode;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getCustomCode() {
        return customCode;
    }

    public String getMessage() {
        return message;
    }
}
