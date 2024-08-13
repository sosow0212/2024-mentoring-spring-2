package com.lotto.web.lotto.domain.vo;

import com.lotto.web.lotto.domain.vo.exception.LottoRangeException;

public class Number {

    private static final int MAXIMUM_LOTTO_NUMBER = 45;
    private static final int MINIMUM_LOTTO_NUMBER = 1;

    private final int number;

    public Number(int number) {
        this.number = number;
    }

    public static Number form(int number) {
        validateNumberRange(number);
        return new Number(number);
    }

    public int getNumber() {
        return number;
    }

    private static void validateNumberRange(int number) {
        if (number > MAXIMUM_LOTTO_NUMBER || number < MINIMUM_LOTTO_NUMBER) {
            throw new LottoRangeException();
        }
    }
}
