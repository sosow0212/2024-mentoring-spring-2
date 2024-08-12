package com.lotto.dto.response;


import com.lotto.domain.Lotto;
import com.lotto.domain.LottoJudge;
import com.lotto.domain.LottoRank;
import com.lotto.domain.LottoResult;

import java.util.List;

public record UserResultResponse(
        List<Integer> lottoNumber,
        boolean result
) {
    public static List<UserResultResponse> fromLottos(List<Lotto> lottos, List<Integer> winNumbers) {
        return lottos.stream()
                .map(lotto -> new UserResultResponse(
                        lotto.getLottoNumbers(),
                        isWinningLotto(lotto.getLottoNumbers(), winNumbers)
                ))
                .toList();
    }

    public static UserResultResponse fromLottoJudge(LottoJudge judge) {
        return new UserResultResponse(
                judge.getLottoNumbers(),
                judge.isWinningLotto()
        );
    }

    private static boolean isWinningLotto(List<Integer> lottoNumbers, List<Integer> winNumbers) {
        LottoResult result = new LottoResult(lottoNumbers, winNumbers);
        return result.getLottoRank() != LottoRank.NONE;
    }
}
