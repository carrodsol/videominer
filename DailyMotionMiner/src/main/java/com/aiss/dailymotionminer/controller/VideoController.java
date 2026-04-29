package com.aiss.dailymotionminer.controller;

import com.aiss.dailymotionminer.model.dailymotion.Video;
import com.aiss.dailymotionminer.model.videominer.VMVideo;
import com.aiss.dailymotionminer.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
// Por ejemplo. Ahora mismo se inicia todo en localhost:8081.
@RequestMapping("/DailyMotion/api/v1/videos")
public class VideoController {

    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) { this.videoService = videoService; }

    @GetMapping()
    public List<Video> getVideos(
            @RequestParam(defaultValue = "10") Integer maxVideos,
            @RequestParam(defaultValue = "2") Integer maxPages
    ) {
        List<Video> video = videoService.findAllVideos(maxVideos, maxPages);
        /* TODO: Terminar la transformación y tal. Ahora mismo esta implementado que devuelva
        sin transformar nada. */
        return videoService.findAllVideos(maxVideos, maxPages);
    }

    // TODO: El POST a VideoMiner
}
