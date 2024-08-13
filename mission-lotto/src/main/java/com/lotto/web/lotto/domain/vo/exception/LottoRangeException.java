package com.lotto.web.lotto.domain.vo.exception;

import com.lotto.web.global.exception.exceptions.CustomErrorCode;
import com.lotto.web.global.exception.exceptions.CustomException;

public class LottoRangeException extends CustomException {
    public LottoRangeException() {
        super(CustomErrorCode.NOT_LOTTO_RANGE_EXCEPTION);
    }
}
