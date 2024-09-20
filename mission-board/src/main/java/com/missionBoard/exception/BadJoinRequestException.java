package com.missionBoard.exception;

public class BadJoinRequestException extends RuntimeException{
    public BadJoinRequestException(){
        super("사용중인 아이디 입니다.");
    }
}
