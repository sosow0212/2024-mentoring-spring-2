package com.web.lotto.domain.vo.exception;

public class BoundaryLottoNumberValueException extends RuntimeException{

    public BoundaryLottoNumberValueException() {
        super("로또 숫자는 1부터 45 사이입니다. ");
    }

}
