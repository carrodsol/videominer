package com.aiss.dailymotionminer.controller;

import com.aiss.dailymotionminer.etl.VideoETL;
import com.aiss.dailymotionminer.model.dailymotion.Video;
import com.aiss.dailymotionminer.model.videominer.VMVideo;
import com.aiss.dailymotionminer.service.VideoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@Tag(name = "Video", description = "API encargada del manejo de videos de DailyMotion")
@RequestMapping("/DailyMotion/api/v1/videos")
public class VideoController {

    private final VideoService videoService;
    private final VideoETL videoETL;


    @Autowired
    public VideoController(VideoService videoService, VideoETL videoETL) {
        this.videoService = videoService;
        this.videoETL = videoETL;
    }

    @Operation(
            summary = "Obtener lista de vídeos",
            description = "Recupera y transforma una lista de los últimos vídeos globales de DailyMotion.",
            tags = {"videos", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de vídeos obtenida con éxito", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Parámetros incorrectos",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Parámetros incorrecto de búsqueda\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Error interno del servidor\"}"), mediaType = "application/json"))
    })
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<VMVideo> getVideos(
            @Parameter(description = "Número máximo de vídeos a devolver", example = "10")
            @RequestParam(defaultValue = "10") Integer maxVideos,
            @Parameter(description = "Número máximo de páginas de resultados a consultar", example = "2")
            @RequestParam(defaultValue = "2") Integer maxPages
    ) {
        List<Video> videos = videoService.findAllVideos(maxVideos, maxPages);
        // Gestionamos asincronia de forma separada. Al principio, se consiguen todas las "promesas" y luego ya se obtienen sus valores.
        List<CompletableFuture<VMVideo>> videosAsync = videos.stream().map(videoETL::transform).toList();
        return videosAsync.stream().map(CompletableFuture::join).toList();
    }

}