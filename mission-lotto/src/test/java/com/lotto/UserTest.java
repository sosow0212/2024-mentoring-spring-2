package com.lotto;

import com.lotto.dto.request.RegisterRequest;
import com.lotto.exception.NotExistUserNameException;
import com.lotto.repository.UserRepository;
import com.lotto.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void 사용자_등록_테스트() {
        RegisterRequest registerRequest = new RegisterRequest("user1", 3000);
        userService.registerUser(registerRequest);

        boolean userExists = userRepository.existsByUserName("user1");
        Assertions.assertTrue(userExists);
    }

    @Test
    void 사용자_존재하지_않을때_오류_발생() {
        RegisterRequest registerRequest = new RegisterRequest("user1", 1000);
        userService.registerUser(registerRequest);

        Assertions.assertThrows(NotExistUserNameException.class, () -> {
            userService.getUserByName("user2");
        });
    }
}
