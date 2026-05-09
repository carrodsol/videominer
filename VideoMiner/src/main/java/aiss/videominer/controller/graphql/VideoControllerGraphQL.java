package aiss.videominer.controller.graphql;

import aiss.videominer.exception.VideoNotFoundException;
import aiss.videominer.model.Caption;
import aiss.videominer.model.Comment;
import aiss.videominer.model.User;
import aiss.videominer.model.Video;
import aiss.videominer.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class VideoControllerGraphQL {

    private static final MethodParameter CREATE_COMMENT_ID_PARAMETER = new MethodParameter(
            Objects.requireNonNull(ReflectionUtils.findMethod(VideoControllerGraphQL.class, "createComment", String.class, String.class, String.class, String.class)), 1);
    private static final MethodParameter CREATE_CAPTION_ID_PARAMETER = new MethodParameter(
            Objects.requireNonNull(ReflectionUtils.findMethod(VideoControllerGraphQL.class, "createCaption", String.class, String.class, String.class, String.class)), 1);

    @Autowired
    VideoRepository repository;

    @QueryMapping
    public List<Video> videos() {
        return repository.findAll();
    }

    @QueryMapping
    public Video video(@Argument String id) throws VideoNotFoundException {
        return repository.findById(id).orElseThrow(VideoNotFoundException::new);
    }

    @QueryMapping
    public List<Comment> commentsByVideo(@Argument String videoId) throws VideoNotFoundException {
        Optional<Video> video = repository.findById(videoId);
        return video.map(Video::getComments).orElseThrow(VideoNotFoundException::new);
    }

    @QueryMapping
    public List<Caption> captionsByVideo(@Argument String videoId) throws VideoNotFoundException {
        Optional<Video> video = repository.findById(videoId);
        return video.map(Video::getCaptions).orElseThrow(VideoNotFoundException::new);
    }

    @MutationMapping
    public Video updateVideo(@Argument String id, @Argument String name, @Argument String description, @Argument String releaseTime,
                             @Argument List<Comment> comments, @Argument List<Caption> captions, @Argument User author) throws VideoNotFoundException {
        Optional<Video> foundVideo = repository.findById(id);
        if (foundVideo.isPresent()) {
            Video video = foundVideo.get();
            video.setName(name);
            video.setDescription(description);
            video.setReleaseTime(releaseTime);
            if (comments != null) {
                video.setComments(comments);
            }
            if (captions != null) {
                video.setCaptions(captions);
            }
            video.setAuthor(author);
            repository.save(video);
            return video;
        } else {
            throw new VideoNotFoundException();
        }
    }

    @MutationMapping
    public Boolean deleteVideo(@Argument String id) throws VideoNotFoundException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        throw new VideoNotFoundException();
    }

    @MutationMapping
    public Comment createComment(@Argument String videoId, @Argument String id, @Argument String text, @Argument String createdOn) throws VideoNotFoundException, MethodArgumentNotValidException {
        Optional<Video> foundVideo = repository.findById(videoId);
        if (foundVideo.isPresent()) {
            Video video = foundVideo.get();
            requireId(id, "Comment", CREATE_COMMENT_ID_PARAMETER);
            Comment comment = new Comment();
            comment.setId(id);
            comment.setText(text);
            comment.setCreatedOn(createdOn);
            video.getComments().add(comment);
            repository.save(video);
            return comment;
        } else {
            throw new VideoNotFoundException();
        }
    }

    @MutationMapping
    public Caption createCaption(@Argument String videoId, @Argument String id, @Argument String name, @Argument String language) throws VideoNotFoundException, MethodArgumentNotValidException {
        Optional<Video> foundVideo = repository.findById(videoId);
        if (foundVideo.isPresent()) {
            Video video = foundVideo.get();
            Caption caption = new Caption();
            requireId(id, "Caption", CREATE_CAPTION_ID_PARAMETER);
            caption.setId(id);
            caption.setName(name);
            caption.setLanguage(language);
            video.getCaptions().add(caption);
            repository.save(video);
            return caption;
        } else {
            throw new VideoNotFoundException();
        }
    }

    private void requireId(String id, String resource, MethodParameter methodParameter) throws MethodArgumentNotValidException {
        if (id == null || id.isBlank()) {
            BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(new Object(), "input");
            bindingResult.addError(new FieldError("input", "id", id, false, null, null, resource + " id is required"));
            throw new MethodArgumentNotValidException(methodParameter, bindingResult);
        }
    }
}
