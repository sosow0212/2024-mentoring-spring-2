package com.web.lottoTicket.controller;

import com.web.lotto.controller.LottoController;
import com.web.lotto.controller.dto.BuyTicketsRequest;
import com.web.lotto.controller.dto.BuyTicketsResponse;
import com.web.lotto.controller.dto.WinningNumbersResponse;
import com.web.lotto.service.LottoService;
import com.web.lotto.domain.entity.Lotto;
import com.web.lotto.infrastructure.LottoPrice;
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
        WinningNumbersResponse dto = new WinningNumbersResponse(Arrays.asList(1, 2, 3, 4, 5, 6));

        Mockito.doNothing().when(lottoService).getWinningLottoTickets(dto.winningNumbers());

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
        Mockito.when(lottoService.getLottoTicketsById(1L)).thenReturn(lotto);
//        Mockito.when(lottoService.calculateTotalWinnings(lotto)).thenReturn(w);

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/lottos/tickets/{userId}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userId").value(lotto.getUser().getUserId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userName").value(lotto.getUser().getUserName()));
    }
}
