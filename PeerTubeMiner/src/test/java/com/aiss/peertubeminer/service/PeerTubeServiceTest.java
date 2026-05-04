package com.aiss.peertubeminer.service;

import com.aiss.peertubeminer.model.peertube.PTCaptionList;
import com.aiss.peertubeminer.model.peertube.PTChannel;
import com.aiss.peertubeminer.model.peertube.PTCommentList;
import com.aiss.peertubeminer.model.peertube.PTVideoList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PeerTubeServiceTest {

    @Autowired
    PeerTubeService service;

    @Test
    @DisplayName("Obtener canal")
    void getChannel() {
        PTChannel canal = service.getChannel("319b8650-fe12-48f6-8b81-e523dd1e5fd4");
        assertNotNull(canal,"No puede ser nulo.");
        System.out.println(canal);

    }

    @Test
    void getVideosFromChannel() {
        PTVideoList result = service.getVideosFromChannel("75efc529-ab08-46d3-a974-272be9466f2f", 5);
        assertNotNull(result);
        assertFalse(result.getData().isEmpty());
    }

    @Test
    void getCaptions() {
        // Primero obtenemos un vídeo real para tener su UUID
        PTCaptionList captions = service.getCaptions("bc5b5ab6-b322-43c3-8881-0d4f17974ca6");
        assertNotNull(captions);
    }

    @Test
    void getComments() {
        PTCommentList comments = service.getComments("bc5b5ab6-b322-43c3-8881-0d4f17974ca6", 1);
        assertNotNull(comments);
        }
    }