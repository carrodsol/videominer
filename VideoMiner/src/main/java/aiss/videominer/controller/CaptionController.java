package aiss.videominer.controller;

import aiss.videominer.exception.CaptionNotFoundException;
import aiss.videominer.model.Caption;
import aiss.videominer.repository.CaptionRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videominer/captions")
@Tag(name = "Captions", description = "API para la gestión de subtítulos de vídeos")
public class CaptionController {

    @Autowired
    CaptionRepository repository;

    // GET http://localhost:8080/videominer/captions
    @Operation(summary = "Obtener todos los subtítulos", description = "Devuelve la lista completa de subtítulos.", tags = {"Captions", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de subtítulos obtenida con éxito",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Error interno del servidor\"}"), mediaType = "application/json"))
    })
    @GetMapping
    public List<Caption> findAll() { return repository.findAll(); }

    // GET http://localhost:8080/videominer/captions/{id}
    @Operation(summary = "Obtener subtítulo por ID", description = "Devuelve un subtítulo concreto dado su id.", tags = {"Captions", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Subtítulo obtenido con éxito",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Subtítulo no encontrado",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Subtítulo no encontrado\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Error interno del servidor\"}"), mediaType = "application/json"))
    })
    @GetMapping("/{id}")
    public Caption findOne(@PathVariable String id) throws CaptionNotFoundException {
        Optional<Caption> caption = repository.findById(id);
        if(caption.isEmpty()) {
            throw new CaptionNotFoundException();
        }
        return caption.get();
    }

    // PUT http://localhost:8080/videominer/captions/{id}
    @Operation(summary = "Actualizar subtítulo", description = "Actualiza los datos de un subtítulo existente.", tags = {"Captions", "put"})
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Subtítulo actualizado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Datos inválidos\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Subtítulo no encontrado",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Subtítulo no encontrado\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Error interno del servidor\"}"), mediaType = "application/json"))
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Caption updatedCaption, @PathVariable String id) throws CaptionNotFoundException {
        Optional<Caption> caption = repository.findById(id);
        if(caption.isEmpty()) {
            throw new CaptionNotFoundException();
        }
        Caption _caption = caption.get();
        _caption.setName(updatedCaption.getName());
        _caption.setLanguage(updatedCaption.getLanguage());
        repository.save(_caption);
    }

    // DELETE http://localhost:8080/videominer/captions/{id}
    @Operation(summary = "Eliminar subtítulo", description = "Elimina un subtítulo dado su id.", tags = {"Captions", "delete"})
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Subtítulo eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Subtítulo no encontrado",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Subtítulo no encontrado\"}"), mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno",
                    content = @Content(examples = @ExampleObject(value = "{\"message\": \"Error interno del servidor\"}"), mediaType = "application/json"))
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) throws CaptionNotFoundException{
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new CaptionNotFoundException();
        }
    }
}
