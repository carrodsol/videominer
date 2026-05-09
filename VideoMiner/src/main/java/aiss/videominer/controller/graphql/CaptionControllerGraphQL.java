package aiss.videominer.controller.graphql;

import aiss.videominer.exception.CaptionNotFoundException;
import aiss.videominer.model.Caption;
import aiss.videominer.repository.CaptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class CaptionControllerGraphQL {

    @Autowired
    CaptionRepository repository;

    @QueryMapping
    public List<Caption> captions() {
        return repository.findAll();
    }

    @QueryMapping
    public Caption caption(@Argument String id) throws CaptionNotFoundException {
        return repository.findById(id).orElseThrow(CaptionNotFoundException::new);
    }

    @MutationMapping
    public Caption updateCaption(@Argument String id, @Argument String name, @Argument String language) throws CaptionNotFoundException {
        Optional<Caption> foundCaption = repository.findById(id);
        if (foundCaption.isPresent()) {
            Caption caption = foundCaption.get();
            caption.setName(name);
            caption.setLanguage(language);
            repository.save(caption);
            return caption;
        } else {
            throw new CaptionNotFoundException();
        }
    }

    @MutationMapping
    public Boolean deleteCaption(@Argument String id) throws CaptionNotFoundException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        throw new CaptionNotFoundException();
    }
}
