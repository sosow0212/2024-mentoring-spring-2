package com.lotto.domain;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class LottoRandomNumber implements RandomNumber {

    private static final int LOTTO_LIMIT_NUMBER = 45;
    private static final int LOTTO_SIZE = 6;

    @Override
    public List<Integer> createRandomNumber() {
        List<Integer> numbers = new ArrayList<>();
        for (int number = 0; number < LOTTO_LIMIT_NUMBER; number++) {
            numbers.add(number + 1);
        }
        Collections.shuffle(numbers);
        List<Integer> lottoNumbers = numbers.subList(0, LOTTO_SIZE);
        Collections.sort(lottoNumbers);
        return lottoNumbers;
    }
}
