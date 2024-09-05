package com.missionBoard.exception;

public class NotFoundBoardException extends RuntimeException{
    public NotFoundBoardException(){
        super("게시글을 찾을 수 없습니다.");
    }
}
