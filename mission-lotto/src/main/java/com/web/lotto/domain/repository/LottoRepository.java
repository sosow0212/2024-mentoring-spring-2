package com.web.lotto.domain.repository;

import com.web.lotto.domain.entity.Lotto;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface LottoRepository extends JpaRepository<Lotto, Long> {

}
