package com.lotto.exception;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException() {
        super("금액이 부족합니다.");
    }
}
