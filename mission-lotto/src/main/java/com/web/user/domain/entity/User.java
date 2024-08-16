package com.web.user.domain.entity;

import com.web.lotto.domain.entity.Lotto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "balance")
    private int balance;

    @Column(name = "lottoCount")
    private int lottoCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lotto_id")
    private Lotto lotto;

}