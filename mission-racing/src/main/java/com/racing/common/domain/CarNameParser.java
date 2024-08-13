package com.racing.common.domain;

import com.racing.common.domain.vo.Name;

import java.util.ArrayList;
import java.util.List;

public class CarNameParser {

    private static final String SPLIT_STRING_DELIMITER = ",";

    public final List<Name> carNames;

    public CarNameParser(String carNames) {
        this.carNames = parseCarName(carNames);
    }

    public List<Name> getCarNames() {
        return carNames;
    }

    private List<Name> parseCarName(String carNames) {
        String[] parsedCarNames = carNames.split(SPLIT_STRING_DELIMITER);
        List<Name> Names = new ArrayList<>();
        for (String carName : parsedCarNames) {
            Names.add(Name.from(carName));
        }
        return Names;
    }
}
