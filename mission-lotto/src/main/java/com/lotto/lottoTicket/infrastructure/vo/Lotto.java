package com.lotto.lottoTicket.infrastructure.vo;

import lombok.Getter;

import java.util.List;

@Getter
public class Lotto {

    private final RandomNumberGenerator randomNumberGenerator;
    private final List<Integer> lotto;

    public Lotto(final RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
        this.lotto = createLotto(randomNumberGenerator);
    }

    public List<Integer> createLotto(final RandomNumberGenerator randomNumberGenerator) {
        List<Integer> lotto= randomNumberGenerator.createLotto();
        return lotto;
    }

}