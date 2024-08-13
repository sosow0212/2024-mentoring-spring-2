package com.lotto.web.lotto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lotto.web.lotto.dto.LottoRequest;
import com.lotto.web.lotto.entity.LottoEntity;
import com.lotto.web.lotto.service.LottoService;
import com.lotto.web.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = LottoController.class)
class LottoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private LottoService lottoService;

    @Test
    @DisplayName("로또 생성하기.")
    void buyLottos() throws Exception {
        // given
        LottoRequest lottoRequest = new LottoRequest(1L, 3);
        willDoNothing().given(lottoService).buyLotto(lottoRequest);

        // then
        mockMvc.perform(post("/api/lottos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(lottoRequest)))
                .andExpect(status().isCreated());

        verify(lottoService).buyLotto(lottoRequest);
    }

    @Test
    @DisplayName("사용자 id로 로또 조회하기.")
    void showLottos() throws Exception {
        // given
        Member member = new Member("jay", 5000);
        List<LottoEntity> lottoEntities = List.of(new LottoEntity(member, "[1, 2, 3, 4, 5, 6]", 3));
        given(lottoService.getLottos(1L)).willReturn(lottoEntities);

        // then
        mockMvc.perform(get("/api/members/{memberId}/lottos", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(lottoService).getLottos(1L);
    }

    @Test
    @DisplayName("사용자 id로 로또 조회해서 첫 번째 로또 조회하기.")
    void showLotto() throws Exception {
        // given
        Member member = new Member("jay", 5000);
        List<LottoEntity> lottoEntities = List.of(new LottoEntity(member, "[1, 2, 3, 4, 5, 6]", 3));
        given(lottoService.getLottos(1L)).willReturn(lottoEntities);

        // then
        mockMvc.perform(get("/api/members/{memberId}/lottos", 1L, 1)
                        .param("order", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(lottoService).getLottos(1L);
    }
}
