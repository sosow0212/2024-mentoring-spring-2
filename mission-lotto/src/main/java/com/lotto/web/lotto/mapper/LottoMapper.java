package com.lotto.web.lotto.mapper;

import com.lotto.web.lotto.dto.LottoResponse;
import com.lotto.web.lotto.dto.LottoResponses;
import com.lotto.web.lotto.entity.LottoEntity;
import com.lotto.web.member.entity.Member;

import java.util.List;

public class LottoMapper {

    public static LottoEntity toLottoEntity(Member member, String lottoNumber, int count) {
        return new LottoEntity(member, lottoNumber, count);
    }

    public static LottoResponse toLottoResponse(LottoEntity lottoEntity) {
        return new LottoResponse(lottoEntity.getLottoNumber(), lottoEntity.getWin());
    }

    public static LottoResponses toLottoResponses(List<LottoResponse> lottoResponses) {
        return LottoResponses.from(lottoResponses);
    }

    public static LottoResponses toCheckedOrderLottoResponses(List<LottoResponse> lottoResponses, int order) {
        return LottoResponses.of(lottoResponses, order);
    }
}
