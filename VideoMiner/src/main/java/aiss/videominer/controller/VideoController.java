package aiss.videominer.controller;

import aiss.videominer.exception.VideoNotFoundException;
import aiss.videominer.model.Caption;
import aiss.videominer.model.Comment;
import aiss.videominer.model.Video;
import aiss.videominer.repository.VideoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("videominer/api/videos")
public class VideoController {
    @Autowired
    VideoRepository repository;

    // GET http://localhost:8080/videominer/api/videos
    @GetMapping
    public List<Video> findAll() { return repository.findAll(); }

    // GET http://localhost:8080/videominer/api/videos/{id}
    @GetMapping("/{id}")
    public Video findOne(@PathVariable String id) throws VideoNotFoundException {
        Optional<Video> video = repository.findById(id);
        if(video.isEmpty()) {
            throw new VideoNotFoundException();
        }
        return video.get();
    }

    // GET http://localhost:8080/videominer/api/videos/{id}/captions
    @GetMapping("/{id}/captions")
    public List<Caption> findCaptionsByVideoId(@PathVariable String id) throws VideoNotFoundException {
        Optional<Video> video = repository.findById(id);
        if(video.isEmpty()) {
            throw new VideoNotFoundException();
        }
        return video.get().getCaptions();
    }

    // GET http://localhost:8080/videominer/api/videos/{id}/comments
    @GetMapping("/{id}/comments")
    public List<Comment> findCommentsByVideoId(@PathVariable String id) throws VideoNotFoundException {
        Optional<Video> video = repository.findById(id);
        if(video.isEmpty()) {
            throw new VideoNotFoundException();
        }
        return video.get().getComments();
    }

    // POST http://localhost:8080/videominer/videos/{id}/comments
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

    // PUT http://localhost:8080/videominer/api/videos/{id}
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
        _video.setComments(updatedVideo.getComments());
        _video.setCaptions(updatedVideo.getCaptions());
        repository.save(_video);
    }

    // DELETE http://localhost:8080/videominer/api/videos/{id]
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }
}
