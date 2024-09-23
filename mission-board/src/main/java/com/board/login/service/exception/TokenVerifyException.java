package com.board.login.service.exception;

import com.board.global.exception.exceptions.CustomErrorCode;
import com.board.global.exception.exceptions.CustomException;

public class TokenVerifyException extends CustomException {

    public TokenVerifyException() {
        super(CustomErrorCode.CAN_NOT_VERIFY_TOKEN);
    }
}
