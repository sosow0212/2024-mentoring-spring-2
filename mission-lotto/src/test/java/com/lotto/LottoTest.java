package com.lotto;

import com.lotto.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

public class LottoTest {

    private RandomNumber randomNumber = new LottoRandomNumber();

    @Test
    void 로또_구매_개수_테스트() {
        int numberOfTickets = 5;
        Lottos lottos = new Lottos(new ArrayList<>());
        lottos = lottos.buyLotto(numberOfTickets, randomNumber);

        Assertions.assertEquals(numberOfTickets, lottos.getLottos().size());
    }

    @Test
    void 로또_묶음_테스트() {
        Lotto lotto = Lotto.createLotto(randomNumber);
        List<Integer> lottoNumbers = lotto.getLottoNumbers();

        Assertions.assertEquals(6, lottoNumbers.size());
        Assertions.assertTrue(lottoNumbers.stream().allMatch(num -> num >= 1 && num <= 45));
    }

    @Test
    void 로또_번호_중복_테스트() {
        Lotto lotto = Lotto.createLotto(randomNumber);
        List<Integer> lottoNumbers = lotto.getLottoNumbers();

        Assertions.assertEquals(6, lottoNumbers.size());
        Assertions.assertTrue(lottoNumbers.stream().distinct().count() == lottoNumbers.size());
    }

    @Test
    void 당첨금_확인_테스트() {
        List<Integer> userLottoNumbers = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);

        Lotto lotto = new Lotto(userLottoNumbers);
        LottoResult lottoResult = new LottoResult(lotto.getLottoNumbers(), winningNumbers);

        Assertions.assertEquals(LottoRank.FIRST, lottoResult.getLottoRank());
        Assertions.assertEquals(2000000000, LottoRank.FIRST.getPrize());
    }
}
