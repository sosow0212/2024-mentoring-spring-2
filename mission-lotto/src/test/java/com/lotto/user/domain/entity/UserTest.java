package com.lotto.user.domain.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void 돈입력_테스트() {
        User user = new User(1L, "sy", 10000, 0,0,null);


        assertEquals(10000, user.getBalance());
    }

}
