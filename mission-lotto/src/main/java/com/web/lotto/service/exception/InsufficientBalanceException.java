package com.web.lotto.service.exception;

public class InsufficientBalanceException extends RuntimeException{

    public InsufficientBalanceException() {
        super("잔고가 부족합니다.");
    }

}