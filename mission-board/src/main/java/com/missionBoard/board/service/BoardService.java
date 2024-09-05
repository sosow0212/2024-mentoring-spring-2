package com.missionBoard.board.service;

import com.missionBoard.board.domain.Board;
import com.missionBoard.board.dto.BoardRequest;
import com.missionBoard.exception.NotFoundBoardException;
import com.missionBoard.board.repository.BoardRepository;
import com.missionBoard.exception.NotFoundUserException;
import com.missionBoard.exception.UnauthorizedActionException;
import com.missionBoard.login.domain.Member;
import com.missionBoard.login.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public BoardService(BoardRepository boardRepository, MemberRepository memberRepository) {
        this.boardRepository = boardRepository;
        this.memberRepository = memberRepository;
    }

    public void createBoard(BoardRequest createRequest) {
        Member member = memberRepository.findByLoginId(createRequest.loginId())
                .orElseThrow(NotFoundUserException::new);
        Board board = createRequest.toEntity(member);
        boardRepository.save(board);
    }

    public Board readBoard(Long boardId, Member loginUser) {
        Board board = findBoardById(boardId);
        checkAuthority(board, loginUser);
        return board;
    }

    public Board findBoardById(Long boardId) {
        return boardRepository.findBoardById(boardId)
                .orElseThrow(NotFoundBoardException::new);
    }

    public List<Board> findBoardByMember(Member member) {
        return boardRepository.findBoardByMember(member)
                .orElseThrow(NotFoundBoardException::new);
    }

    private void checkAuthority(Board board, Member loginUser) {
        if (!board.getMember().getLoginId().equals(loginUser.getLoginId())) {
            log.info("로그인 아이디 {}", loginUser.getLoginId());
            log.info("작성자 아이디 {}", board.getMember().getLoginId());
            throw new UnauthorizedActionException();
        }
    }

    public Board editBoard(Long BoardId, BoardRequest editRequest, Member loginUser) {
        Board board = findBoardById(BoardId);
        checkAuthority(board, loginUser);
        board.update(editRequest.title(), editRequest.content());
        return boardRepository.save(board);
    }

    public void deleteBoard(Long id, Member loginUser) {
        Board board = findBoardById(id);
        checkAuthority(board, loginUser);
        boardRepository.delete(board);
    }
}
