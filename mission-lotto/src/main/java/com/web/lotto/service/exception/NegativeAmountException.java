package com.web.lotto.service.exception;

public class NegativeAmountException extends RuntimeException{

    public NegativeAmountException() {
        super("음수 금액을 넣을 수 없습니다!!");
    }

}
