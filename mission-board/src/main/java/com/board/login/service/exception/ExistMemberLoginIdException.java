package com.board.login.service.exception;

import com.board.global.exception.exceptions.CustomErrorCode;
import com.board.global.exception.exceptions.CustomException;

public class ExistMemberLoginIdException extends CustomException {
    public ExistMemberLoginIdException() {
        super(CustomErrorCode.NOT_EXIST_MEMBER_LOGIN_ID);
    }
}
