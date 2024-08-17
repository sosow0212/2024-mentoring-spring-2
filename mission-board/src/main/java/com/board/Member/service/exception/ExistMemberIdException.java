package com.board.Member.service.exception;

import com.board.global.exception.exceptions.CustomErrorCode;
import com.board.global.exception.exceptions.CustomException;

public class ExistMemberIdException extends CustomException {

    public ExistMemberIdException() {
        super(CustomErrorCode.ALREADY_EXIST_MEMBER_ID);
    }
}
