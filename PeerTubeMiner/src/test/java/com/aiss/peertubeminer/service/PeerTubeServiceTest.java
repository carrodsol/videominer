package com.aiss.peertubeminer.service;

import com.aiss.peertubeminer.model.peertube.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PeerTubeServiceTest {

    @Autowired
    CaptionService captionService;
    @Autowired
    CommentService commentService;
    @Autowired
    ChannelService channelService;
    @Autowired
    VideoService videoService;
    @Autowired
    UserService userService;


    @Test
    @DisplayName("Obtener canal")
    void getChannel() {
        PTChannel canal = channelService.getChannelById("tv");
        assertNotNull(canal, "No puede ser nulo.");
        System.out.println(canal);

    }

    @Test
    @DisplayName("Obtener videos a partir de un canal")
    void getVideosFromChannel() {
        PTVideoList result = videoService.getVideosFromChannel("tv", 5);
        assertNotNull(result);
        assertFalse(result.getData().isEmpty());
        System.out.println(result);

    }

    @Test
    @DisplayName("Obtener subtítulos")
    void getCaptions() {
        // Primero obtenemos un vídeo real para tener su UUID
        PTCaptionList captions = captionService.getCaptionsById("06845579-afba-433f-bb8c-a5f5a6a692f5");
        assertNotNull(captions);
        System.out.println(captions);
    }

    @Test
    @DisplayName("Obtener comentarios")
    void getComments() {
        PTCommentList comments = commentService.getComments("06845579-afba-433f-bb8c-a5f5a6a692f5", 10);
        assertNotNull(comments);
        System.out.println(comments);
    }


    @Test
    @DisplayName("Obtener usuario")
    void getAccount() {
        PTAccount account = userService.getAccount("peertube");
        assertNotNull(account);
        System.out.println(account);

    }

}