package com.board.member.service.exception;

import com.board.global.exception.exceptions.CustomErrorCode;
import com.board.global.exception.exceptions.CustomException;

public class ExistMemberNickNameException extends CustomException {

    public ExistMemberNickNameException() {
        super(CustomErrorCode.ALREADY_EXIST_MEMBER_NICKNAME);
    }
}
