package com.board.member.domain;

import com.board.member.domain.exception.ExistMemberPasswordException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank
    private String memberName;

    @Column
    @NotBlank
    private String memberNickName;

    @Column
    @NotBlank
    private String memberLoginId;

    @Column
    @NotBlank
    private String memberPassword;

    public Member(String memberName, String memberNickName, String memberLoginId, String memberPassword) {
        this.memberName = memberName;
        this.memberNickName = memberNickName;
        this.memberLoginId = memberLoginId;
        this.memberPassword = memberPassword;
    }

    public void updateMember(Long id, String memberName, String memberNickName, String memberLoginId, String memberPassword) {
        this.id = id;
        this.memberName = memberName;
        this.memberNickName = memberNickName;
        this.memberLoginId = memberLoginId;
        this.memberPassword = memberPassword;
    }

    public void checkPassword(String requestPassword) {
        if (!Objects.equals(memberPassword, requestPassword)) {
            throw new ExistMemberPasswordException();
        }
    }

    public boolean isSameMember(Long boardObjectId) {
        return Objects.equals(id, boardObjectId);
    }
}
