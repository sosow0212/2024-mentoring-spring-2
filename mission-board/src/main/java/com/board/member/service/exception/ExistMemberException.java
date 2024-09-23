package com.board.member.service.exception;

import com.board.global.exception.exceptions.CustomErrorCode;
import com.board.global.exception.exceptions.CustomException;

public class ExistMemberException extends CustomException {

    public ExistMemberException() {
        super(CustomErrorCode.NOT_EXIST_MEMBER);
    }
}
