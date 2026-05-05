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
        PTChannel canal = channelService.getChannelById("319b8650-fe12-48f6-8b81-e523dd1e5fd4");
        assertNotNull(canal, "No puede ser nulo.");
        System.out.println(canal);

    }

    @Test
    @DisplayName("Obtener videos a partir de un canal")
    void getVideosFromChannel() {
        PTVideoList result = videoService.getVideosFromChannel("75efc529-ab08-46d3-a974-272be9466f2f", 5);
        assertNotNull(result);
        assertFalse(result.getData().isEmpty());
        System.out.println(result);

    }

    @Test
    @DisplayName("Obtener subtítulos")
    void getCaptions() {
        // Primero obtenemos un vídeo real para tener su UUID
        PTCaptionList captions = captionService.getCaptionsById("bc5b5ab6-b322-43c3-8881-0d4f17974ca6");
        assertNotNull(captions);
        System.out.println(captions);
    }

    @Test
    @DisplayName("Obtener comentarios")
    void getComments() {
        PTCommentList comments = commentService.getComments("bc5b5ab6-b322-43c3-8881-0d4f17974ca6", 1);
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