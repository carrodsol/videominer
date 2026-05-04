package com.aiss.dailymotionminer.service;

import com.aiss.dailymotionminer.model.dailymotion.InfoPaginacionSubtitles;
import com.aiss.dailymotionminer.model.dailymotion.Subtitles;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SubtitleServiceTest {

    @Autowired
    SubtitleService subtitleService;

    @Test
    @DisplayName("Obtiene los subtítulos de un vídeo")
    void findSubtitlesByVideoId() {
        // Este video no tiene subtítulos
        InfoPaginacionSubtitles subtitles = subtitleService.findSubtitlesByVideoId("xa7vgfi");
        assertNotNull(subtitles);
        System.out.println(subtitles);
    }
}