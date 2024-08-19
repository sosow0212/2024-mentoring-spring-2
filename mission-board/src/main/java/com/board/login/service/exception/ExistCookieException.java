package com.board.login.service.exception;

import com.board.global.exception.exceptions.CustomErrorCode;
import com.board.global.exception.exceptions.CustomException;

public class ExistCookieException extends CustomException {
    public ExistCookieException() {
        super(CustomErrorCode.NOT_EXIST_COOKIE);
    }
}
