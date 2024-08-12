package com.lotto.exception;

public class NotExistLottoException extends RuntimeException {
    public NotExistLottoException() {
        super("해당 로또가 존재하지 않습니다.");
    }
}
