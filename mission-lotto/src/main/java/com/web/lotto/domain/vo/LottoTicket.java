package com.web.lotto.domain.vo;

import com.web.lotto.infrastructure.RandomGeneratorLotto;

import java.util.List;

public record LottoTicket(List<Integer> numbers, RandomGeneratorLotto randomGeneratorLotto) {

}
