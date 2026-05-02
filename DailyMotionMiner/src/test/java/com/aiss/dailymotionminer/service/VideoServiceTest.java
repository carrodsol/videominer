package com.aiss.dailymotionminer.service;

import com.aiss.dailymotionminer.model.dailymotion.Video;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VideoServiceTest {

    @Autowired
    private VideoService videoService;

    @Test
    @DisplayName("Obtiene todos los videos")
    void findAllVideos() {
        List<Video> listaVideos = videoService.findAllVideos(10,2);
        assertFalse(listaVideos.isEmpty());
        System.out.println("listaVideos: " + listaVideos);
    }


    @Test
    @DisplayName("Obtiene todos los videos dado un canal")
    void findAllVideosByChannelId() {
        List<Video> listaVideos = videoService.findAllVideosByChannelId("shortfilms",10,2);
        assertFalse(listaVideos.isEmpty());
        System.out.println("listaVideos: " + listaVideos);
    }
}