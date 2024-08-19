package com.board.member.repository;

import com.board.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByMemLoginId(String memberLoginId);
    boolean existsByMemNickName(String memNickName);
    boolean existsByMemLoginId(String memLoginId);
}
