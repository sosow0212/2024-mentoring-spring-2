package com.missionBoard.board.repository;

import com.missionBoard.board.domain.Board;
import com.missionBoard.login.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Optional<List<Board>> findBoardByMember(Member member);

    Optional<Board> findBoardById(Long id);
}
