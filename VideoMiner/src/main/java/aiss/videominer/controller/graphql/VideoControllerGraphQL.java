package aiss.videominer.controller.graphql;

import aiss.videominer.exception.VideoNotFoundException;
import aiss.videominer.model.Caption;
import aiss.videominer.model.Comment;
import aiss.videominer.model.User;
import aiss.videominer.model.Video;
import aiss.videominer.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class VideoControllerGraphQL {

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
    public Comment createComment(@Argument String videoId, @Argument String id, @Argument String text, @Argument String createdOn) throws VideoNotFoundException {
        Optional<Video> foundVideo = repository.findById(videoId);
        if (foundVideo.isPresent()) {
            Video video = foundVideo.get();
            if (id == null || id.isBlank()) {
                throw new IllegalArgumentException("Comment id is required");
            }
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
    public Caption createCaption(@Argument String videoId, @Argument String id, @Argument String name, @Argument String language) throws VideoNotFoundException {
        Optional<Video> foundVideo = repository.findById(videoId);
        if (foundVideo.isPresent()) {
            Video video = foundVideo.get();
            Caption caption = new Caption();
            if (id == null || id.isBlank()) {
                throw new IllegalArgumentException("Caption id is required");
            }
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
}
