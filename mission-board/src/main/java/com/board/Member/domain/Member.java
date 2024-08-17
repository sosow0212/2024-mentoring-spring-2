package com.board.Member.domain;

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
    private String memId;

    @Column
    private String memPassword;

    public Member(String memName, String memNickName, String memId, String memPassword){
        this.memName = memName;
        this.memNickName = memNickName;
        this.memId = memId;
        this.memPassword = memPassword;
    }

    protected Member() {

    }
}
