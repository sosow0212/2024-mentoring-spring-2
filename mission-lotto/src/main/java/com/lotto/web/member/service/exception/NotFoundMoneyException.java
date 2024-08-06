package com.lotto.web.member.service.exception;

import com.lotto.web.global.exception.exceptions.CustomErrorCode;
import com.lotto.web.global.exception.exceptions.CustomException;

public class NotFoundMoneyException extends CustomException {
    public NotFoundMoneyException() {
        super(CustomErrorCode.EXCEPTION_MONEY);
    }
}
