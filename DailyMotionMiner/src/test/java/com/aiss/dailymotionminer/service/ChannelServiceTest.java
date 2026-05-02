package com.aiss.dailymotionminer.service;

import com.aiss.dailymotionminer.model.dailymotion.Channels;
import com.aiss.dailymotionminer.model.dailymotion.Video;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChannelServiceTest {

    @Autowired
    private ChannelService channelService;

    @Test
    @DisplayName("Obtiene un canal dado un id")
    void findChannelById() {
        Channels channel = channelService.findChannelById("shortfilms");
        assertNotNull(channel);
        System.out.println("Canal shortfilms: " + channel);
    }

}