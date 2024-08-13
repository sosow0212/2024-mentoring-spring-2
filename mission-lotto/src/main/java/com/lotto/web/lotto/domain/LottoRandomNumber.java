package com.lotto.web.lotto.domain;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class LottoRandomNumber implements CreateRandomNumber {

    private static final Random createRandomNumber = new Random();
    private static final int MINIMUM = 1;
    private static final int MAXIMUM = 45;

    @Override
    public int generateRandomNumber() {
        return createRandomNumber.nextInt((MAXIMUM - MINIMUM) + MINIMUM) + MINIMUM;
    }
}
