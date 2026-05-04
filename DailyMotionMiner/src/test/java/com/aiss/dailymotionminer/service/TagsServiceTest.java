package com.aiss.dailymotionminer.service;

import com.aiss.dailymotionminer.model.dailymotion.Tags;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TagsServiceTest {
    @Autowired
    TagsService tagsService;

    @Test
    @DisplayName("Obtiene los tags de un video")
    void findTagsByVideoId() {
        Tags tags = tagsService.findTagsByVideoId("xa7vgfi");
        assertNotNull(tags);
        System.out.println(tags);
    }
}