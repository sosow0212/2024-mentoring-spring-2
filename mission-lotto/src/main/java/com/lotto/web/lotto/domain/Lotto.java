package com.lotto.web.lotto.domain;

import com.lotto.web.lotto.domain.vo.Number;

import java.util.ArrayList;
import java.util.List;

public class Lotto {

    private static final int LOTTO_NUMBER_BOUNDARY = 6;
    private static final int INITIAL_NUMBER = 0;

    private final List<Number> lotto;

    public Lotto(CreateRandomNumber createRandomNumber) {
        this.lotto = createLottoNumber(createRandomNumber);
    }

    public List<Integer> getLotto() {
        List<Integer> lottoNumber = new ArrayList<>();
        for (Number number : lotto) {
            lottoNumber.add(number.getNumber());
        }
        return lottoNumber;
    }

    private List<Number> createLottoNumber(CreateRandomNumber createRandomNumber) {
        List<Number> numbers = new ArrayList<>();
        for (int i = INITIAL_NUMBER; i < LOTTO_NUMBER_BOUNDARY; i++) {
            Number number = selectNumber(createRandomNumber, numbers, Number.form(createRandomNumber.generateRandomNumber()));
            numbers.add(number);
        }
        return numbers;
    }

    private Number selectNumber(CreateRandomNumber createRandomNumber, List<Number> numbers, Number number) {
        List<Integer> lottoNumber = makeLottoNumbers(numbers);
        while (isSameNumberInLotto(lottoNumber, number)) {
            number = Number.form(createRandomNumber.generateRandomNumber());
        }
        return number;
    }

    private List<Integer> makeLottoNumbers(List<Number> numbers) {
        List<Integer> lottoNumber = new ArrayList<>();
        for (Number number : numbers) {
            lottoNumber.add(number.getNumber());
        }
        return lottoNumber;
    }

    private boolean isSameNumberInLotto(List<Integer> lottoNumber, Number number) {
        return lottoNumber.contains(number.getNumber());
    }
}
