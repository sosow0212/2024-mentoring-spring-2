package com.board.login.service.exception;

import com.board.global.exception.exceptions.CustomErrorCode;
import com.board.global.exception.exceptions.CustomException;

public class ExistTokenException extends CustomException {

    public ExistTokenException() {
        super(CustomErrorCode.NOT_EXIST_TOKEN);
    }
}
