package com.aiss.dailymotionminer.controller;

import com.aiss.dailymotionminer.etl.ChannelETL;
import com.aiss.dailymotionminer.etl.VideoETL;
import com.aiss.dailymotionminer.model.videominer.VMChannel;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
// Ruta: localhost:8081/DailyMotion/api/v1/channels/{id}
@Tag(name = "Channels", description = "API encargada de la gestión y exportación de canales de DailyMotion")
@RequestMapping("/DailyMotion/api/v1/channels")
public class ChannelController {

    private final VideoService videoService;
    private final VideoETL videoETL;
    private final ChannelETL channelETL;
    private final RestTemplate restTemplate;

    @Value("${VideoMiner.uri}")
    private String videoMinerUri;

    @Autowired
    public ChannelController(VideoService videoService,
                             VideoETL videoETL, ChannelETL channelETL, RestTemplate restTemplate) {
        this.videoService = videoService;
        this.videoETL = videoETL;
        this.channelETL = channelETL;
        this.restTemplate = restTemplate;
    }

    @Operation(summary = "Obtener canal por ID", description = "Recupera y transforma un canal de DailyMotion.", tags = {"channels", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Canal obtenido con éxito", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Parámetros incorrectos",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Parámetros incorrecto de búsqueda\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"El recurso solicitado no existe en DailyMotion\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Error interno del servidor\"}"), mediaType = "application/json"))
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VMChannel getChannelById(
            @Parameter(example = "music") @PathVariable String id,
            @RequestParam(defaultValue = "10") Integer maxVideos,
            @RequestParam(defaultValue = "2") Integer maxPages) {

        List<CompletableFuture<VMVideo>> videosAsync = videoService.findAllVideosByChannelId(id, maxVideos, maxPages)
                .stream()
                .map(videoETL::transform)
                .toList();
        // Gestionamos asincronia de forma separada. Al principio, se consiguen todas las "promesas" y luego ya se obtienen sus valores.
        List<VMVideo> videos = videosAsync.stream()
                .map(CompletableFuture::join)
                .toList();

        return channelETL.transform(videos, id);
    }

    @Operation(summary = "Exportar canal a VideoMiner", tags = {"channels", "post"}, description = "Envía un canal a VideoMiner")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Canal exportado con éxito", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Parámetros incorrectos",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Parámetros incorrecto de búsqueda\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"El recurso solicitado no existe en DailyMotion\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Error interno del servidor\"}"), mediaType = "application/json"))
    })
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public VMChannel postChannelToVideoMiner(
            @PathVariable String id,
            @RequestParam(defaultValue = "10") Integer maxVideos,
            @RequestParam(defaultValue = "2") Integer maxPages) {

        VMChannel channel = getChannelById(id, maxVideos, maxPages);
        restTemplate.postForObject(videoMinerUri + "/channels", channel, VMChannel.class);

        return channel;
    }
}