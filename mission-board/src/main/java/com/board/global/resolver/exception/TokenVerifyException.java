package com.board.global.resolver.exception;

import com.board.global.exception.exceptions.CustomErrorCode;
import com.board.global.exception.exceptions.CustomException;

public class TokenVerifyException extends CustomException {

    public TokenVerifyException() {
        super(CustomErrorCode.CAN_NOT_VERIFY_TOKEN);
    }
}
