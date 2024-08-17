package com.board.Member.repository;

import com.board.Member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByMemNickName(String memNickName);
    boolean existsByMemId(String memId);
}
