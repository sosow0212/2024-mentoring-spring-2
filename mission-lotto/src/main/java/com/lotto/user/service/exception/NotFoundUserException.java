package com.lotto.user.service.exception;

public class NotFoundUserException extends IllegalArgumentException {

    public NotFoundUserException() {
        super("유저를 찾을 수 없습니다.");
    }

}
