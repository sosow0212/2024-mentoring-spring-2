package com.board.member.service.event;

import com.board.member.domain.Member;
import lombok.Getter;

import java.util.concurrent.CompletableFuture;

@Getter
public class MemberFindEvent {

    private final Long memberId;
    private final CompletableFuture<Member> future = new CompletableFuture<>();

    public MemberFindEvent(Long memberId) {
        this.memberId = memberId;
    }

    public CompletableFuture<Member> getFuture() {
        return future;
    }
}
