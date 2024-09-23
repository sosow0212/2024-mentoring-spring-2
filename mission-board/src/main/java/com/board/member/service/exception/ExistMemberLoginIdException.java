package com.board.member.service.exception;

import com.board.global.exception.exceptions.CustomErrorCode;
import com.board.global.exception.exceptions.CustomException;

public class ExistMemberLoginIdException extends CustomException {

    public ExistMemberLoginIdException() {
        super(CustomErrorCode.ALREADY_EXIST_MEMBER_ID);
    }
}
