package com.web.lotto.domain.exception;

public class NotSetWinningNumbers extends IllegalStateException{
    public NotSetWinningNumbers() {
        super("당첨번호 설정이 되어있지 않다.");
    }
}

