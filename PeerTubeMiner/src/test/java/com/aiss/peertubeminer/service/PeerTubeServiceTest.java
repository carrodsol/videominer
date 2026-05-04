package com.aiss.peertubeminer.service;

import com.aiss.peertubeminer.model.peertube.PTChannel;
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
        PTChannel canal = service.getChannel("1");
        assertNotNull(canal,"No puede ser nulo.");
        System.out.println(canal);
//TODO ARREGLAR
    }

    @Test
    void getVideosFromChannel() {
    }

    @Test
    void getCaptions() {
    }

    @Test
    void getComments() {
    }
}