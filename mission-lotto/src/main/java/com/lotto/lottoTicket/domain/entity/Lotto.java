package com.lotto.lottoTicket.domain.entity;

import com.lotto.lottoTicket.infrastructure.vo.LottoTicket;
import com.lotto.user.domain.entity.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Lottos")
public class Lotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "lotto_tickets", joinColumns = @JoinColumn(name = "lotto_id"))
    private List<LottoTicket> lottoTickets;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
