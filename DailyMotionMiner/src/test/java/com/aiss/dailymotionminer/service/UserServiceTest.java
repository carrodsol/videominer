package com.aiss.dailymotionminer.service;

import com.aiss.dailymotionminer.model.dailymotion.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    @DisplayName("Obtiene un usuario dado un video")
    void findUserByVideoId() {
        User user = userService.findUserByVideoId("xa7vgfi");
        assertNotNull(user);
        System.out.println(user);
    }
}