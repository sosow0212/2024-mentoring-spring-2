package com.lotto.web.member.service.exception;

import com.lotto.web.global.exception.exceptions.CustomErrorCode;
import com.lotto.web.global.exception.exceptions.CustomException;

public class NotFoundMemberException extends CustomException {
    public NotFoundMemberException() {
        super(CustomErrorCode.USER_NOT_FOUND_EXCEPTION);
    }
}
