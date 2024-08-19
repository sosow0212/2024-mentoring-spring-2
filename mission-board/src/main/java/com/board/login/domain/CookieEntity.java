package com.board.login.domain;

import jakarta.persistence.*;

@Entity
public class CookieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String cookieName;
    @Column
    private String cookieContent;

    public CookieEntity(String cookieName, String cookieContent) {
        this.cookieName = cookieName;
        this.cookieContent = cookieContent;
    }

    protected CookieEntity() {

    }
}
