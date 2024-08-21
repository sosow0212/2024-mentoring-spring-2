package com.board.member.repository;

import com.board.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberLoginId(String memberLoginId);
    boolean existsByMemberNickName(String memberNickName);
    boolean existsByMemberLoginId(String memberLoginId);
}
