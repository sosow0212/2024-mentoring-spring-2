package com.web.lotto.domain.vo;

import com.web.lotto.infrastructure.RandomGeneratorLotto;

import java.util.List;

public class LottoTicket {

    private final List<Integer> numbers;
    private final RandomGeneratorLotto randomGeneratorLotto;

    public LottoTicket(List<Integer> numbers, final RandomGeneratorLotto randomGeneratorLotto) {
        this.randomGeneratorLotto = randomGeneratorLotto;
        this.numbers = this.randomGeneratorLotto.generateRandomNumber();
    }

}