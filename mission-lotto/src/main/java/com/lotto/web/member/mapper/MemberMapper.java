package com.lotto.web.member.mapper;

import com.lotto.web.member.dto.CreateRequest;
import com.lotto.web.member.dto.CreatedResponse;
import com.lotto.web.member.dto.MemberResponse;
import com.lotto.web.member.dto.MemberResponses;
import com.lotto.web.member.entity.Member;
import com.lotto.web.member.entity.MemberLotto;

import java.util.List;

public class MemberMapper {

    public static Member toMember(CreateRequest createRequest) {
        return new Member(createRequest.name(), createRequest.money());
    }

    public static MemberResponse toMemberResponse(MemberLotto memberLotto) {
        return new MemberResponse(memberLotto.getId(), memberLotto.getMember().getName(), memberLotto.getLottoCount(), memberLotto.getWinningPrice());
    }

    public static CreatedResponse toCreatedResponse(Member member){
        return new CreatedResponse(member.getId(), member.getName(), member.getMoney());
    }

    public static MemberResponses toMemberResponses(List<MemberResponse> memberResponses) {
        return MemberResponses.form(memberResponses);
    }
}
