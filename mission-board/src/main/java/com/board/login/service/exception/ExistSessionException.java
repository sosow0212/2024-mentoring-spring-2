package com.board.login.service.exception;

import com.board.global.exception.exceptions.CustomErrorCode;
import com.board.global.exception.exceptions.CustomException;

public class ExistSessionException extends CustomException {
    public ExistSessionException() {
        super(CustomErrorCode.NOT_EXIST_SESSION);
    }
}
