package com.missionBoard.board.controller;

import com.missionBoard.board.domain.Board;
import com.missionBoard.board.dto.BoardRequest;
import com.missionBoard.board.dto.BoardResponse;
import com.missionBoard.board.service.BoardService;
import com.missionBoard.config.LoginUser;
import com.missionBoard.login.domain.Member;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createBoard(@ModelAttribute BoardRequest createRequest) {
        boardService.createBoard(createRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResponse> readBoard(@PathVariable(name = "boardId") Long boardId, @LoginUser Member loginUser) {
        Board board = boardService.readBoard(boardId, loginUser);
        BoardResponse boardResponse = BoardResponse.from(board);
        return ResponseEntity.ok(boardResponse);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BoardResponse>> getBoardsByUser(@LoginUser Member loginUser) {
        List<Board> boards = boardService.findBoardByMember(loginUser);
        List<BoardResponse> boardResponses = boards.stream()
                .map(BoardResponse::from)
                .toList();
        return ResponseEntity.ok(boardResponses);
    }

    @PatchMapping ("/{boardId}")
    public ResponseEntity<BoardResponse> editBoard(@PathVariable(name = "boardId") Long boardId, @RequestBody BoardRequest boardRequest, @LoginUser Member loginUser) {
        Board updateBoard = boardService.editBoard(boardId, boardRequest, loginUser);
        return ResponseEntity.ok(BoardResponse.from((updateBoard)));
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<Void> deleteBoard(@PathVariable(name = "boardId") Long boardId, @LoginUser Member loginUser) {
        boardService.deleteBoard(boardId, loginUser);
        return ResponseEntity.ok().build();
    }
}
