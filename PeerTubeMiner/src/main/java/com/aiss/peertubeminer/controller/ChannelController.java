package com.aiss.peertubeminer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import com.aiss.peertubeminer.etl.ChannelETL;
import com.aiss.peertubeminer.model.videominer.VMChannel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@Tag(name = "Channels", description = "API encargada de la gestión y exportación de canales de PeerTube")
@RequestMapping("/peertube/channels")
public class ChannelController {

    @Autowired
    ChannelETL channelETL;

    @Autowired
    RestTemplate restTemplate;

    @Value("${videominer.base.url}")
    private String videoMinerBaseUrl;

    @Operation(summary = "Obtener canal por ID", description = "Recupera y transforma un canal de PeerTube.", tags = {"Channels", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Canal obtenido con éxito", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Parámetros incorrectos",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Parámetros incorrectos de búsqueda\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"El recurso solicitado no existe en PeerTube\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Error interno del servidor\"}"), mediaType = "application/json"))
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VMChannel getChannel(@Parameter(example = "tv") @PathVariable String id,
                                @RequestParam(defaultValue = "10") int maxVideos,
                                @RequestParam(defaultValue = "2") int maxComments) {

        return channelETL.transform(id, maxVideos, maxComments);
    }

    @Operation(summary = "Exportar canal a VideoMiner", tags = {"Channels", "post"}, description = "Envía un canal a VideoMiner")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Canal exportado con éxito", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Parámetros incorrectos",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Parámetros incorrectos de búsqueda\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "No autorizado",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"No autorizado: falta la API Key en los headers\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"El recurso solicitado no existe en PeerTube\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Error interno del servidor\"}"), mediaType = "application/json"))
    })
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public VMChannel postChannel(@Parameter(example = "tv") @PathVariable String id,
                                 @RequestParam(defaultValue = "10") int maxVideos,
                                 @RequestParam(defaultValue = "2") int maxComments,
                                 @RequestHeader(value = "Authorization", required = false) String apiKey) {
        VMChannel channel = getChannel(id, maxVideos, maxComments);
        
        HttpHeaders headers = new HttpHeaders();
        if(apiKey != null && apiKey.startsWith("Bearer")) {
            headers.set("Authorization", apiKey);
        } else {
            headers.setBearerAuth(apiKey);
        }
        HttpEntity<VMChannel> requestEntity = new HttpEntity<>(channel, headers);

        restTemplate.postForObject(videoMinerBaseUrl + "/channels", requestEntity, VMChannel.class);
        return channel;
    }
}