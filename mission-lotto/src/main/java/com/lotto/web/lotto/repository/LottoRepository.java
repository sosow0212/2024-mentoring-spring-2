package com.lotto.web.lotto.repository;

import com.lotto.web.lotto.entity.LottoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LottoRepository extends JpaRepository<LottoEntity, Long> {

    List<LottoEntity> findAllByMemberId(Long memberId);
}
