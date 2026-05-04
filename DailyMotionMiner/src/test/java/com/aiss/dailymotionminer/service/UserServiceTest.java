package com.aiss.dailymotionminer.service;

import com.aiss.dailymotionminer.model.dailymotion.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    @DisplayName("Obtiene un usuario dado un video")
    void findUserByVideoIdPositive() {
        User user = userService.findUserByVideoId("xa7ksfk");
        assertNotNull(user);
        System.out.println(user);
    }
    @Test
    @DisplayName("Lanza error al buscar el dueño de un video que no existe")
    void findUserByVideoIdNegative() {
        assertThrows(HttpClientErrorException.class, () -> userService.findUserByVideoId("nuncaHabraUnVideo"),
                "Debería lanzar error 404");
    }
}