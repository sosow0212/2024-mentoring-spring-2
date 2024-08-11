package com.lotto.user.service.exception;

public class NegativeAmountException extends IllegalArgumentException{

    public NegativeAmountException() {
        super("음수 금액을 넣을 수 없습니다!!");
    }

}