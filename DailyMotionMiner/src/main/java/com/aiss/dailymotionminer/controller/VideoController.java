package com.aiss.dailymotionminer.controller;

import com.aiss.dailymotionminer.etl.VideoETL;
import com.aiss.dailymotionminer.model.dailymotion.Video;
import com.aiss.dailymotionminer.model.videominer.VMVideo;
import com.aiss.dailymotionminer.service.VideoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//TODO: Documentar servicio con Swagger

@RestController
// Por ejemplo. Ahora mismo se inicia todo en localhost:8081.
@Tag(name = "Video", description = "API encargada del manejo de videos de DailyMotion")
@RequestMapping("/DailyMotion/api/v1/videos")
public class VideoController {

    private final VideoService videoService;
    private final VideoETL videoETL;

    @Value("${VideoMiner.uri}")
    private String videoMinerUri;

    @Autowired
    public VideoController(VideoService videoService, VideoETL videoETL) {
        this.videoService = videoService;
        this.videoETL = videoETL; }

    @Autowired
    RestTemplate restTemplate;

    @Operation(
            summary = "Devuelve la lista de los últimos vídeos",
            description = "GET una lista de videos",
            tags = {"videos", "get"})
    @ApiResponse(responseCode = "200")
    @GetMapping()
    public List<VMVideo> getVideos(
            @RequestParam(defaultValue = "10") Integer maxVideos,
            @RequestParam(defaultValue = "2") Integer maxPages
    ) {
        List<Video> video = videoService.findAllVideos(maxVideos, maxPages);
        List<VMVideo> vmVideos = video.stream().map(videoETL::transform).toList();
        return vmVideos;
    }
    @Operation(
            summary = "Envia la lista de los últimos vídeos a VideoMiner",
            description = "POST una lista de videos a VideoMiner",
            tags = {"videos", "post"})
    @ApiResponse(responseCode = "201")
    @PostMapping()
    public List<VMVideo> sendToVideoMiner(@RequestParam(defaultValue = "10") Integer maxVideos,
                                          @RequestParam(defaultValue = "2") Integer maxPages) {
        List<VMVideo> videos = getVideos(maxVideos, maxPages);
        videos.forEach(v -> restTemplate.postForObject(videoMinerUri+"/videominer/videos", v, VMVideo.class));
        return videos;

    }

}
