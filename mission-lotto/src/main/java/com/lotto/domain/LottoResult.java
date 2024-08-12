package com.lotto.domain;

import java.util.List;

public class LottoResult {

    private final LottoRank rank;

    public LottoResult(List<Integer> lottoNumbers, List<Integer> winNumbers) {
        this.rank = calculateRank(lottoNumbers, winNumbers);
    }

    private LottoRank calculateRank(List<Integer> lottoNumbers, List<Integer> winNumbers) {
        int matchCount = 0;
        for (Integer number : lottoNumbers) {
            matchCount = getMatchCount(winNumbers, matchCount, number);
        }
        return LottoRank.getRank(matchCount);
    }

    private int getMatchCount(List<Integer> winNumbers, int matchCount, Integer number) {
        if (winNumbers.contains(number)) {
            matchCount++;
        }
        return matchCount;
    }

    public LottoRank getLottoRank() {
        return rank;
    }
}
