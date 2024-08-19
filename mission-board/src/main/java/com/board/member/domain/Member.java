package com.board.member.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String memName;

    @Column
    private String memNickName;

    @Column
    private String memLoginId;

    @Column
    private String memPassword;

    public Member(String memName, String memNickName, String memLoginId, String memPassword){
        this.memName = memName;
        this.memNickName = memNickName;
        this.memLoginId = memLoginId;
        this.memPassword = memPassword;
    }

    protected Member() {

    }
}
