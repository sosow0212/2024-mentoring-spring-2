package com.missionBoard.login.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false, unique = true)
    private String loginId;

    @Column(length = 20, nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(length = 10, nullable = false)
    private String username;
}
