package com.aiss.dailymotionminer.service;

import com.aiss.dailymotionminer.model.dailymotion.Channels;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChannelServiceTest {

    @Autowired
    private ChannelService channelService;

    @Test
    @DisplayName("Obtiene un canal dado un id")
    void findChannelByIdPositive() {
        Channels channel = channelService.findChannelById("shortfilms");
        assertNotNull(channel);
        System.out.println("Canal shortfilms: " + channel);
    }
    @Test
    @DisplayName("Debería lanzar error 404 al buscar un canal que no existe")
    void findChannelByIdNegative() {
        assertThrows(HttpClientErrorException.class, () -> channelService.findChannelById("estaCategoriaNoExiste"),
                "Debería lanzar error 404");
    }

}