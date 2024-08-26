package com.board.member.domain.exception;

import com.board.global.exception.exceptions.CustomErrorCode;
import com.board.global.exception.exceptions.CustomException;

public class ExistMemberPasswordException extends CustomException {

    public ExistMemberPasswordException() {
        super(CustomErrorCode.NOT_EXIST_MEMBER_PASSWORD);
    }
}
