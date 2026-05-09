package aiss.videominer.controller.rest;

import aiss.videominer.exception.ChannelNotFoundException;
import aiss.videominer.model.Channel;
import aiss.videominer.model.Video;
import aiss.videominer.repository.ChannelRepository;
import jakarta.servlet.http.HttpServletRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videominer/channels")
@Tag(name = "Channels", description = "API para la gestión de canales y sus vídeos asociados")
public class ChannelController {
    @Autowired
    ChannelRepository repository;

    // GET http://localhost:8080/videominer/api/channels/{id}/private
        @Operation(summary = "PREMIUM: Obtener canal por ID", description = "Devuelve un canal concreto dado su id. Si no eres usuario premium limita los videos obtenidos a 5, si eres premium devuelve hasta 50", 
                   tags = {"Channels", "get", "Premium"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Canal obtenido con éxito",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Canal no encontrado",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Canal no encontrado\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Error interno del servidor\"}"), mediaType = "application/json"))
    })
    @GetMapping("/{id}/private")
    public Channel findOnePrivate(@PathVariable String id, HttpServletRequest request) throws ChannelNotFoundException {
        Optional<Channel> channelOpt = repository.findById(id);
        if (channelOpt.isEmpty()) {
            throw new ChannelNotFoundException();
        }

        Channel channel = channelOpt.get();


        Object premiumAttr = request.getAttribute("isPremiumUser");
        boolean isPremium = premiumAttr != null && (boolean) premiumAttr;


        List<Video> videos = channel.getVideos();

        if (videos != null && !videos.isEmpty()) {
            int maxVideos;

            if (isPremium) {
                maxVideos = 50;
                System.out.println("Petición VIP: Devolviendo hasta 50 vídeos.");
            } else {
                maxVideos = 5;
                System.out.println("Petición Gratuita: Devolviendo máximo 5 vídeos.");
            }


            if (videos.size() > maxVideos) {
                channel.setVideos(videos.subList(0, maxVideos));
            }
        }

        return channel;
    }

    // GET http://localhost:8080/videominer/channels
    @Operation(summary = "Obtener todos los canales", description = "Devuelve la lista completa de canales.", tags = {"Channels", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de canales obtenida con éxito",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Error interno del servidor\"}"), mediaType = "application/json"))
    })
    @GetMapping
    public List<Channel> findAll() { return repository.findAll(); }

    // GET http://localhost:8080/videominer/channels/{id}
    @Operation(summary = "Obtener canal por ID", description = "Devuelve un canal concreto dado su id.", tags = {"Channels", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Canal obtenido con éxito",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Canal no encontrado",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Canal no encontrado\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Error interno del servidor\"}"), mediaType = "application/json"))
    })
    @GetMapping("/{id}")
    public Channel findOne(@PathVariable String id) throws ChannelNotFoundException {
        Optional<Channel> channel = repository.findById(id);
        if (channel.isEmpty()) {
            throw new ChannelNotFoundException();
        }
        return channel.get();
    }

    // GET http://localhost:8080/videominer/channels/{id}/videos
    @Operation(summary = "Obtener vídeos de un canal", description = "Devuelve todos los vídeos asociados a un canal dado su ID.", tags = {"Videos", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de vídeos obtenida con éxito",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Canal no encontrado",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Canal no encontrado\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Error interno del servidor\"}"), mediaType = "application/json"))
    })
    @GetMapping("/{id}/videos")
    public List<Video> findVideosByChannelId(@PathVariable String id) throws ChannelNotFoundException {
        Optional<Channel> channel = repository.findById(id);
        if (channel.isEmpty()) {
            throw new ChannelNotFoundException();
        }
        return channel.get().getVideos();
    }

    // POST http://localhost:8080/videominer/channels
    @Operation(summary = "Crear canal", description = "Crea y almacena un nuevo canal.", tags = {"Channels", "post"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Canal creado con éxito",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Datos inválidos\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Error interno del servidor\"}"), mediaType = "application/json"))
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Channel create(@Valid @RequestBody Channel channel) {
        return repository.save(channel);
    }

    // POST http://localhost:8080/videominer/channels/{id}/videos
    @Operation(summary = "Añadir vídeo a un canal", description = "Crea un nuevo vídeo y lo asocia al canal indicado.", tags = {"Videos", "post"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Vídeo añadido con éxito",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Datos inválidos\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Canal no encontrado",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Canal no encontrado\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Error interno del servidor\"}"), mediaType = "application/json"))
    })
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}/videos")
    public Video create(@Valid @RequestBody Video video, @PathVariable String id) throws ChannelNotFoundException {     // id del Channel al que pertenecera el Video
        Optional<Channel> foundChannel = repository.findById(id);
        if (foundChannel.isEmpty()) {
            throw new ChannelNotFoundException();
        }
        Channel _channel = foundChannel.get();
        _channel.getVideos().add(video);
        repository.save(_channel);
        return video;
    }

    // PUT http://localhost:8080/videominer/channels/{id}
    @Operation(summary = "Actualizar canal", description = "Actualiza los datos de un canal existente.", tags = {"Channels", "put"})
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Canal actualizado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Datos inválidos\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Canal no encontrado",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Canal no encontrado\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Error interno del servidor\"}"), mediaType = "application/json"))
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Channel updatedChannel, @PathVariable String id) throws ChannelNotFoundException {
        Optional<Channel> foundChannel = repository.findById(id);
        if (foundChannel.isEmpty()) {
            throw new ChannelNotFoundException();
        }
        Channel _channel = foundChannel.get();
        _channel.setName(updatedChannel.getName());
        _channel.setDescription(updatedChannel.getDescription());
        _channel.setCreatedTime(updatedChannel.getCreatedTime());
        _channel.setVideos(updatedChannel.getVideos());
        repository.save(_channel);
    }

    // DELETE http://localhost:8080/videominer/channels/{id}
    @Operation(summary = "Eliminar canal", description = "Elimina un canal dado su id.", tags = {"Channels", "delete"})
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Canal eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Canal no encontrado",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Canal no encontrado\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Error interno del servidor\"}"), mediaType = "application/json"))
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) throws ChannelNotFoundException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ChannelNotFoundException();
        }
    }
}
