package com.lotto.lottoTicket.controller;

import com.lotto.lottoTicket.controller.dto.BuyTicketsRequest;
import com.lotto.lottoTicket.controller.dto.BuyTicketsResponse;
import com.lotto.lottoTicket.controller.dto.WinningNumbersDTO;
import com.lotto.lottoTicket.service.LottoService;
import com.lotto.lottoTicket.domain.entity.Lotto;
import com.lotto.lottoTicket.infrastructure.LottoPrice;
import com.lotto.lottoTicket.infrastructure.vo.LottoTicket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebMvcTest(LottoController.class)
public class LottoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LottoService lottoService;

    @Test
    public void 구매_테스트() throws Exception {
        BuyTicketsRequest request = new BuyTicketsRequest(1L, 5);
        BuyTicketsResponse response = new BuyTicketsResponse(1L, 5);

        Mockito.doNothing().when(lottoService).buyLotto(request.userId(), request.ticketCount());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/lottos/buy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":1,\"ticketCount\":5}"))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.userId")
                        .value(1))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.ticketCount")
                        .value(5));
    }

    @Test
    public void 당첨번호테스트() throws Exception {
        WinningNumbersDTO dto = new WinningNumbersDTO(Arrays.asList(1, 2, 3, 4, 5, 6));

        Mockito.doNothing().when(lottoService).setWinningNumbers(dto.winningNumbers());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/lottos/setWinningNumbers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"winningNumbers\":[1,2,3,4,5,6]}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("SettingWinningNumbers"));
    }



    @Test
    public void 유저티켓조회테스트() throws Exception {

        //given
        Lotto lotto = new Lotto();
        List<Lotto> lottos = Arrays.asList(lotto);
        Map<LottoPrice, Long> winnings = new HashMap<>();
        winnings.put(LottoPrice.FIRST_PRIZE, 1L);

        //when
        Mockito.when(lottoService.getLottoTicketsByUser(1L)).thenReturn(lottos);
        Mockito.when(lottoService.calculateWinnings()).thenReturn(winnings);

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/lottos/tickets/{userId}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userId").value(lotto.getUser().getUserId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userName").value(lotto.getUser().getUserName()));
    }
}
