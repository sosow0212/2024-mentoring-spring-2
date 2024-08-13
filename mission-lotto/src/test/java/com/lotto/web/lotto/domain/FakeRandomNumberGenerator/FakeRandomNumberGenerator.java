package com.lotto.web.lotto.domain.FakeRandomNumberGenerator;

import com.lotto.web.lotto.domain.CreateRandomNumber;

public class FakeRandomNumberGenerator implements CreateRandomNumber {
    @Override
    public int generateRandomNumber() {
        return 4;
    }
}
