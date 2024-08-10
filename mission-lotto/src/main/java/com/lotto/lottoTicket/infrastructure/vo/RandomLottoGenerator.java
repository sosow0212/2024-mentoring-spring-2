package com.lotto.lottoTicket.infrastructure.vo;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
public class RandomLottoGenerator implements LottoGenerator {

    private static final int LOTTO_SIZE = 6;
    private static final int LOTTO_BOUNDARY_NUMBER = 45;
    private final Random random = new Random();

    @Override
    public List<Integer> createLotto() {
        List<Integer> numbers = new ArrayList<>();
        for (int number = 1; number <= LOTTO_BOUNDARY_NUMBER; number++) {
            numbers.add(number);
        }
        return cleanLotto(numbers);
    }

    private List<Integer> cleanLotto(final List<Integer> numbers) {
        Collections.shuffle(numbers, random);
        List<Integer> lottoNumbers = numbers.subList(0, LOTTO_SIZE);
        Collections.sort(lottoNumbers);
        return lottoNumbers;
    }
}