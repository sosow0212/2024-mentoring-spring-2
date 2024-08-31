package com.board.member.service.event;

import com.board.member.domain.Member;
import com.board.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberEventListener {

    private final MemberService memberService;

    @EventListener
    public void handleMemberEvent(MemberFindEvent memberFindEvent){
        Member member = memberService.findMember(memberFindEvent.getMemberId());
        memberFindEvent.getFuture()
                .complete(member);
    }
}
