package com.missionBoard.exception;

public class UnauthorizedActionException extends RuntimeException{
    public UnauthorizedActionException(){
        super("권한이 없습니다.");
    }
}
