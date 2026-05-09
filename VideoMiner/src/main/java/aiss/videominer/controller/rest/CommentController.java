package aiss.videominer.controller.rest;

import aiss.videominer.exception.CommentNotFoundException;
import aiss.videominer.model.Comment;
import aiss.videominer.repository.CommentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videominer/comments")
public class CommentController {

    @Autowired
    CommentRepository repository;

    // GET http://localhost:8080/videominer/api/comments
    @GetMapping
    public List<Comment> findAll() { return repository.findAll(); }

    // GET http://localhost:8080/videominer/api/comments/{id}
    @GetMapping("/{id}")
    public Comment findOne(@PathVariable String id) throws CommentNotFoundException {
        Optional<Comment> comment = repository.findById(id);
        if(comment.isEmpty()) {
            throw new CommentNotFoundException();
        }
        return comment.get();
    }

    // PUT http://localhost:8080/videominer/api/comments/{id}
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

    // DELETE http://localhost:8080/videominer/api/comments/{id}
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }
}
