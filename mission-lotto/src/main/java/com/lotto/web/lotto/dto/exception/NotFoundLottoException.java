package com.lotto.web.lotto.dto.exception;

import com.lotto.web.global.exception.exceptions.CustomErrorCode;
import com.lotto.web.global.exception.exceptions.CustomException;

public class NotFoundLottoException extends CustomException {
    public NotFoundLottoException() {
        super(CustomErrorCode.LOTTO_NOT_FOUND_EXCEPTION);
    }
}
