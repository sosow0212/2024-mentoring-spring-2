package com.board.login.service.exception;

import com.board.global.exception.exceptions.CustomErrorCode;
import com.board.global.exception.exceptions.CustomException;

public class TokenTimeException extends CustomException {

    public TokenTimeException() {
        super(CustomErrorCode.EXPIRE_TOKEN);
    }
}
