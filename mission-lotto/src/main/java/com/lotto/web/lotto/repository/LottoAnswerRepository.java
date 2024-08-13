package com.lotto.web.lotto.repository;

import com.lotto.web.lotto.entity.LottoAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LottoAnswerRepository extends JpaRepository<LottoAnswer, Long> {

    Optional<LottoAnswer> findFirstByOrderById();
}
