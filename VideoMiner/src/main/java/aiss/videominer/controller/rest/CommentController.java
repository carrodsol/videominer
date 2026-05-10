package aiss.videominer.controller.rest;

import aiss.videominer.exception.CommentNotFoundException;
import aiss.videominer.model.Comment;
import aiss.videominer.repository.CommentRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videominer/comments")
@Tag(name = "Comments", description = "API para la gestión de comentarios de vídeos")
public class CommentController {

    @Autowired
    CommentRepository repository;

    // GET http://localhost:8080/videominer/comments
    @Operation(summary = "Obtener todos los comentarios", description = "Devuelve la lista completa de comentarios.", tags = {"Comments", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de comentarios obtenida con éxito",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Error interno del servidor\"}"), mediaType = "application/json"))
    })
    @GetMapping
    public List<Comment> findAll() { return repository.findAll(); }

    // GET http://localhost:8080/videominer/comments/{id}
    @Operation(summary = "Obtener comentario por ID", description = "Devuelve un comentario concreto dado su id.", tags = {"Comments", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Comentario obtenido con éxito",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Comentario no encontrado",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Comentario no encontrado\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Error interno del servidor\"}"), mediaType = "application/json"))
    })
    @GetMapping("/{id}")
    public Comment findOne(@PathVariable String id) throws CommentNotFoundException {
        Optional<Comment> comment = repository.findById(id);
        if(comment.isEmpty()) {
            throw new CommentNotFoundException();
        }
        return comment.get();
    }

    // PUT http://localhost:8080/videominer/comments/{id}
    @Operation(summary = "Actualizar comentario", description = "Actualiza el texto y fecha de un comentario existente.", tags = {"Comments", "put"})
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Comentario actualizado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Datos inválidos\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "No autorizado",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"No autorizado: falta la API Key en los headers\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Comentario no encontrado",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Comentario no encontrado\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Error interno del servidor\"}"), mediaType = "application/json"))
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Comment updatedComment, @PathVariable String id) throws CommentNotFoundException {
        Optional<Comment> comment = repository.findById(id);
        if(comment.isEmpty()) {
            throw new CommentNotFoundException();
        }
        Comment _comment = comment.get();
        _comment.setText(updatedComment.getText());
        _comment.setCreatedOn(updatedComment.getCreatedOn());
        repository.save(_comment);
    }

    // DELETE http://localhost:8080/videominer/comments/{id}
    @Operation(summary = "Eliminar comentario", description = "Elimina un comentario dado su id.", tags = {"Comments", "delete"})
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Comentario eliminado con éxito"),
            @ApiResponse(responseCode = "401", description = "No autorizado",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"No autorizado: falta la API Key en los headers\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Comentario no encontrado",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Comentario no encontrado\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Error interno del servidor\"}"), mediaType = "application/json"))
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) throws CommentNotFoundException{
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new CommentNotFoundException();
        }
    }
}
