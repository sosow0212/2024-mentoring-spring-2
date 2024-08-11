package com.lotto.user.controller;

import com.lotto.user.domain.entity.User;
import com.lotto.user.service.UserService;
import com.lotto.user.controller.dto.CreateUserResponse;
import com.lotto.user.service.dto.CreateUserRequest;
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
import java.util.List;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void 유저등록테스트() throws Exception {

        //given
        CreateUserRequest request = new CreateUserRequest(null, "Seungboong", 1000, 0);
        User user = new User(1L, "John Doe", 1000, 0, 0, null);
        CreateUserResponse response = new CreateUserResponse(1L, "Seeungboong", 1000, 0);


        //when
        Mockito.when(userService.registerUser(request.userName(), request.balance())).thenReturn(user);


        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\":\"John Doe\",\"balance\":1000}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("Seungboong"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(1000));
    }

    @Test
    public void 유저아이디로조회테스트() throws Exception {

        //given
        User user = new User(1L, "Seungboong", 1000, 0, 0, null);
        CreateUserResponse response = new CreateUserResponse(1L, "John Doe", 1000, 0);

        //when
        Mockito.when(userService.getUserById(1L)).thenReturn(user);

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/{userId}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(1000));
    }

    @Test
    public void 유저모두조회테스트() throws Exception {

        //given
        User user1 = new User(1L, "Seungboong", 1000, 0, 0, null);
        User user2 = new User(2L, "sosow0212", 2000, 1, 0, null);
        List<User> users = Arrays.asList(user1, user2);
        List<CreateUserResponse> response = Arrays.asList(
                new CreateUserResponse(1L, "seungboong", 1000, 0),
                new CreateUserResponse(2L, "sosow0212", 2000, 1)
        );

        //when
        Mockito.when(userService.getAllUsers()).thenReturn(users);

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/allUsers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].userId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userName").value("seungboong"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].userName").value("sosow0212"));
    }
}
