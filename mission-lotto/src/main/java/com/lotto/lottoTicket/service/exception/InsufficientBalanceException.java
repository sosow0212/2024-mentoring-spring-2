package com.lotto.lottoTicket.service.exception;

public class InsufficientBalanceException extends IllegalStateException{

    public InsufficientBalanceException() {
        super("잔고가 부족합니다.");
    }

}