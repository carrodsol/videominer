package aiss.videominer.controller.graphql;

import aiss.videominer.exception.CommentNotFoundException;
import aiss.videominer.model.Comment;
import aiss.videominer.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class CommentControllerGraphQL {

    @Autowired
    CommentRepository repository;

    @QueryMapping
    public List<Comment> comments() {
        return repository.findAll();
    }

    @QueryMapping
    public Comment comment(@Argument String id) throws CommentNotFoundException {
        return repository.findById(id).orElseThrow(CommentNotFoundException::new);
    }

    @MutationMapping
    public Comment updateComment(@Argument String id, @Argument String text, @Argument String createdOn) throws CommentNotFoundException {
        Optional<Comment> foundComment = repository.findById(id);
        if (foundComment.isPresent()) {
            Comment comment = foundComment.get();
            comment.setText(text);
            comment.setCreatedOn(createdOn);
            repository.save(comment);
            return comment;
        } else {
            throw new CommentNotFoundException();
        }
    }

    @MutationMapping
    public Boolean deleteComment(@Argument String id) throws CommentNotFoundException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        throw new CommentNotFoundException();
    }
}
