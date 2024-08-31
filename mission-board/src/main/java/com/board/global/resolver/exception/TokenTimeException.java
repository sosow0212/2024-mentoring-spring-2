package com.board.global.resolver.exception;

import com.board.global.exception.exceptions.CustomErrorCode;
import com.board.global.exception.exceptions.CustomException;

public class TokenTimeException extends CustomException {

    public TokenTimeException() {
        super(CustomErrorCode.EXPIRE_TOKEN);
    }
}
