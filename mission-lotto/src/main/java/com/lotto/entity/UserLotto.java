package com.lotto.entity;

import com.lotto.domain.LottoRank;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "lotto")
public class UserLotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lottoId;

    @ElementCollection
    private List<Integer> lottoNumbers;

    @Enumerated(EnumType.STRING)
    @Column(name = "RANK")
    private LottoRank lottoRank;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public static UserLotto create(List<Integer> lottoNumbers, User user) {
        UserLotto userLotto = UserLotto.builder()
                .lottoNumbers(lottoNumbers)
                .user(user)
                .lottoRank(LottoRank.NONE)
                .build();
        user.addUserLotto(userLotto);
        return userLotto;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
