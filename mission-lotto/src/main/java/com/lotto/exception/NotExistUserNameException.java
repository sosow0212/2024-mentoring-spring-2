package com.lotto.exception;

public class NotExistUserNameException extends RuntimeException {
    public NotExistUserNameException() {
        super("등록되지 않은 사용자 이름입니다.");
    }
}
