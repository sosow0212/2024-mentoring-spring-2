package com.lotto.web.lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoNumberParser {

    private static final String SPLIT_STRING_DELIMITER = ",";
    private static final String DELETE_STRING_DELIMITER = "[\\[\\]]";

    private final List<Integer> lottoNumber;

    public LottoNumberParser(String lottoNumber) {
        this.lottoNumber = makeLottoNumber(lottoNumber);
    }

    public List<Integer> getLottoNumber() {
        return lottoNumber;
    }

    private List<Integer> makeLottoNumber(String lottoNumber) {
        List<Integer> parsedLottoNumber = new ArrayList<>();
        for (String number : parseLottoNumber(cleanLottoNumber(lottoNumber))) {
            int realLottoNumberElement = Integer.parseInt(number.trim());
            parsedLottoNumber.add(realLottoNumberElement);
        }
        return parsedLottoNumber;
    }

    private String cleanLottoNumber(String lottoNumber) {
        return lottoNumber.replaceAll(DELETE_STRING_DELIMITER, "");
    }

    private List<String> parseLottoNumber(String lottoNumber) {
        String[] parsedLottoNumber = lottoNumber.split(SPLIT_STRING_DELIMITER);
        return List.of(parsedLottoNumber);
    }
}
