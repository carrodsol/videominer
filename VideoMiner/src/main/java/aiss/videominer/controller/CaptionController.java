package aiss.videominer.controller;

import aiss.videominer.exception.CaptionNotFoundException;
import aiss.videominer.model.Caption;
import aiss.videominer.repository.CaptionRepository;
import org.springframework.beans.factory.annotation.Value;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videominer/api/captions")
public class CaptionController {

    @Autowired
    CaptionRepository repository;

    @Value("${videominer.api.key:}")
    private String configuredApiKey;

    // GET http://localhost:8080/videominer/api/captions
    @GetMapping
    public List<Caption> findAll() { return repository.findAll(); }


    // GET http://localhost:8080/videominer/api/captions/{id}
    @GetMapping("/{id}")
    public Caption findOne(@PathVariable String id) throws CaptionNotFoundException {
        Optional<Caption> caption = repository.findById(id);
        if(caption.isEmpty()) {
            throw new CaptionNotFoundException();
        }
        return caption.get();
    }

    // PUT http://localhost:8080/videominer/api/captions/{id}
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

    // DELETE http://localhost:8080/videominer/api/captions/{id}
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }
}
