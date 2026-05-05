package com.aiss.dailymotionminer.service;

import com.aiss.dailymotionminer.model.dailymotion.InfoPaginacionSubtitles;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SubtitleServiceTest {

    @Autowired
    SubtitleService subtitleService;

    @Test
    @DisplayName("Obtiene los subtítulos de un vídeo")
    void findSubtitlesByVideoIdPositive() {
        // Este video no tiene subtítulos
        InfoPaginacionSubtitles subtitles = subtitleService.findSubtitlesByVideoId("xa7ksfk");
        assertNotNull(subtitles);
        System.out.println(subtitles);
    }

    @Test
    @DisplayName("Debería lanzar error al buscar los subtitulos de un video que no existe")
    void findSubtitlesByVideoIdNegative() {
        assertThrows(HttpClientErrorException.class, () -> subtitleService.findSubtitlesByVideoId("esteNoExiste"),
                "Debería lanzar error 404");
    }

}