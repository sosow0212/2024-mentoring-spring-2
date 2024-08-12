package com.lotto.domain;

import com.lotto.entity.UserLotto;

import java.util.List;

public class LottoJudge {

    private final UserLotto userLotto;
    private final List<Integer> winNumbers;

    public LottoJudge(UserLotto userLotto, List<Integer> winNumbers) {
        this.userLotto = userLotto;
        this.winNumbers = winNumbers;
    }

    public boolean isWinningLotto() {
        LottoResult result = new LottoResult(userLotto.getLottoNumbers(), winNumbers);
        LottoRank lottoRank = result.getLottoRank();
        return lottoRank.getRankResult(lottoRank);
    }

    public List<Integer> getLottoNumbers() {
        return userLotto.getLottoNumbers();
    }
}
