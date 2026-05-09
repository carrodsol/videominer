package aiss.videominer.controller.rest;

import aiss.videominer.exception.VideoNotFoundException;
import aiss.videominer.model.Caption;
import aiss.videominer.model.Comment;
import aiss.videominer.model.Video;
import aiss.videominer.repository.VideoRepository;
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
@RequestMapping("/videominer/videos")
@Tag(name = "Videos", description = "API para la gestión de vídeos, sus comentarios y subtítulos")
public class VideoController {
    @Autowired
    VideoRepository repository;

    // GET http://localhost:8080/videominer/videos
    @Operation(summary = "Obtener todos los vídeos", description = "Devuelve la lista completa de vídeos almacenados.", tags = {"Videos", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de vídeos obtenida con éxito",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Error interno del servidor\"}"), mediaType = "application/json"))
    })
    @GetMapping
    public List<Video> findAll() { return repository.findAll(); }

    // GET http://localhost:8080/videominer/videos/{id}
    @Operation(summary = "Obtener vídeo por ID", description = "Devuelve un vídeo concreto dado su id.", tags = {"Videos", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vídeo obtenido con éxito",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Vídeo no encontrado",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Vídeo no encontrado\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Error interno del servidor\"}"), mediaType = "application/json"))
    })
    @GetMapping("/{id}")
    public Video findOne(@PathVariable String id) throws VideoNotFoundException {
        Optional<Video> video = repository.findById(id);
        if(video.isEmpty()) {
            throw new VideoNotFoundException();
        }
        return video.get();
    }

    // GET http://localhost:8080/videominer/videos/{id}/captions
    @Operation(summary = "Obtener subtítulos de un vídeo", description = "Devuelve todos los subtítulos asociados a un vídeo dado su id.", tags = {"Videos", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de subtítulos obtenida con éxito",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Vídeo no encontrado",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Vídeo no encontrado\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Error interno del servidor\"}"), mediaType = "application/json"))
    })
    @GetMapping("/{id}/captions")
    public List<Caption> findCaptionsByVideoId(@PathVariable String id) throws VideoNotFoundException {
        Optional<Video> video = repository.findById(id);
        if(video.isEmpty()) {
            throw new VideoNotFoundException();
        }
        return video.get().getCaptions();
    }

    // GET http://localhost:8080/videominer/videos/{id}/comments
    @Operation(summary = "Obtener comentarios de un vídeo", description = "Devuelve todos los comentarios asociados a un vídeo dado su id.", tags = {"Videos", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de comentarios obtenida con éxito",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Vídeo no encontrado",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Vídeo no encontrado\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Error interno del servidor\"}"), mediaType = "application/json"))
    })
    @GetMapping("/{id}/comments")
    public List<Comment> findCommentsByVideoId(@PathVariable String id) throws VideoNotFoundException {
        Optional<Video> video = repository.findById(id);
        if(video.isEmpty()) {
            throw new VideoNotFoundException();
        }
        return video.get().getComments();
    }

    // POST http://localhost:8080/videominer/videos/{id}/comments
    @Operation(summary = "Añadir comentario a un vídeo", description = "Crea un nuevo comentario y lo asocia al vídeo indicado.", tags = {"Comments", "post"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Comentario añadido con éxito",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Datos inválidos\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Vídeo no encontrado",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Vídeo no encontrado\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Error interno del servidor\"}"), mediaType = "application/json"))
    })
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}/comments")
    public Comment create(@Valid @RequestBody Comment comment, @PathVariable String id) throws VideoNotFoundException {
        Optional<Video> video = repository.findById(id);
        if(video.isEmpty()) {
            throw new VideoNotFoundException();
        }
        Video _video = video.get();
        _video.getComments().add(comment);
        repository.save(_video);
        return comment;
    }

    // POST http://localhost:8080/videominer/videos/{id}/captions
    @Operation(summary = "Añadir subtítulo a un vídeo", description = "Crea un nuevo subtítulo y lo asocia al vídeo indicado.", tags = {"Captions", "post"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Subtítulo añadido con éxito",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Datos inválidos\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Vídeo no encontrado",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Vídeo no encontrado\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Error interno del servidor\"}"), mediaType = "application/json"))
    })
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}/captions")
    public Caption create(@Valid @RequestBody Caption caption, @PathVariable String id) throws VideoNotFoundException {
        Optional<Video> video = repository.findById(id);
        if(video.isEmpty()) {
            throw new VideoNotFoundException();
        }
        Video _video = video.get();
        _video.getCaptions().add(caption);
        repository.save(_video);
        return caption;
    }

    // PUT http://localhost:8080/videominer/videos/{id}
    @Operation(summary = "Actualizar vídeo", description = "Actualiza los datos de un vídeo existente.", tags = {"Videos", "put"})
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Vídeo actualizado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Datos inválidos\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Vídeo no encontrado",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Vídeo no encontrado\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Error interno del servidor\"}"), mediaType = "application/json"))
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Video updatedVideo, @PathVariable String id) throws VideoNotFoundException{
        Optional<Video> foundVideo = repository.findById(id);
        if(foundVideo.isEmpty()) {
            throw new VideoNotFoundException();
        }
        Video _video = foundVideo.get();
        _video.setName(updatedVideo.getName());
        _video.setDescription(updatedVideo.getDescription());
        _video.setReleaseTime(updatedVideo.getReleaseTime());
        _video.setAuthor(updatedVideo.getAuthor());
        if (updatedVideo.getComments() != null) {
            _video.setComments(updatedVideo.getComments());
        }
        if (updatedVideo.getCaptions() != null) {
            _video.setCaptions(updatedVideo.getCaptions());
        }
        repository.save(_video);
    }

    // DELETE http://localhost:8080/videominer/videos/{id]
    @Operation(summary = "Eliminar vídeo", description = "Elimina un vídeo dado su id.", tags = {"Videos", "delete"})
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Vídeo eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Vídeo no encontrado",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Vídeo no encontrado\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Error interno del servidor\"}"), mediaType = "application/json"))
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) throws VideoNotFoundException{
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new VideoNotFoundException();
        }
    }
}
