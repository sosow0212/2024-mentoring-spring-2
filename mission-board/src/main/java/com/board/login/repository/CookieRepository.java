package com.board.login.repository;

import com.board.login.domain.CookieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CookieRepository extends JpaRepository<CookieEntity, Long> {
    CookieEntity findByCookieName(String cookieName);
}
