package com.lotto.lottoTicket.infrastructure.vo;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LottoTicket {

    private final RandomLottoGenerator randomLottoGenerator;

    public LottoTicket(final RandomLottoGenerator randomLottoGenerator) {
        this.randomLottoGenerator = randomLottoGenerator;
    }

    public List<Integer> createLotto(final RandomLottoGenerator randomLottoGenerator) {
        return randomLottoGenerator.createLotto();
    }
}