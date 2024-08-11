package com.lotto.repository;

import com.lotto.entity.UserLotto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LottoRepository extends JpaRepository<UserLotto, Long> {
    Optional<List<UserLotto>> findByUser_UserName(String userName);

    Optional<UserLotto> findByLottoId(Long lottoId);
}
