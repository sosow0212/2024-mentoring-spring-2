package com.web.lotto.infrastructure;

import com.web.lotto.domain.vo.GeneratorLotto;
import com.web.lotto.domain.vo.exception.BoundaryLottoNumberValueException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomGeneratorLotto implements GeneratorLotto {

    private static final int MAX = 45;
    private static final int LOTTO_SIZE = 6;
    private static final int COUNT_NUMBER = 1;

    private static final Random random = new Random();

    @Override
    public List<Integer> generateRandomNumber() {
        List<Integer> numbers = new ArrayList<>();
        while (numbers.size() < LOTTO_SIZE) {
            checkDuplicate(numbers);
        }
        return numbers;
    }

    private static void checkDuplicate(List<Integer> numbers) {
        int randomNumber = random.nextInt(MAX) + COUNT_NUMBER;
        if (!numbers.contains(randomNumber)) {
            validationLottoValueException(randomNumber);
            numbers.add(randomNumber);
        }
    }

    private static void validationLottoValueException(final int randomNumber) {
        if (randomNumber > 45 || randomNumber < 1) {
            throw new BoundaryLottoNumberValueException();
        }
    }
}