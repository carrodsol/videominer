package com.aiss.dailymotionminer.service;

import com.aiss.dailymotionminer.model.dailymotion.Video;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VideoServiceTest {

    @Autowired
    private VideoService videoService;

    @Test
    @DisplayName("Obtiene todos los videos")
    void findAllVideosPositive() {
        List<Video> listaVideos = videoService.findAllVideos(10,2);
        assertFalse(listaVideos.isEmpty());
        System.out.println("listaVideos: " + listaVideos);
    }


    @Test
    @DisplayName("Obtiene todos los videos dado un canal")
    void findAllVideosByChannelIdPositive() {
        List<Video> listaVideos = videoService.findAllVideosByChannelId("shortfilms",10,2);
        assertFalse(listaVideos.isEmpty());
        System.out.println("listaVideos: " + listaVideos);
    }

    @Test
    @DisplayName("Lanza error al buscar un id que no existe")
    void findAllVideosByChannelIdNegative() {
        assertThrows(HttpClientErrorException.class, () -> videoService.findAllVideosByChannelId("elCanalDeDios27_12",10,2),
                "Debería lanzar error 404");

    }


    @Test
    @DisplayName("Encuentra videos por ID")
    void findVideoByIdPositive() {
        Video video = videoService.findVideoById("xa7ksfk");
        System.out.println("video: " + video);
    }
    @Test
    @DisplayName("Test que lanza error al no encontrar vídeo por ese ID")
    void findVideoByIdNegative() {
        assertThrows(HttpClientErrorException.class, () -> videoService.findVideoById("xa7vgfi"),
                "Debería lanzar error 404");
    }
}