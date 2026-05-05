package aiss.videominer.controller;

import aiss.videominer.exception.ChannelNotFoundException;
import aiss.videominer.model.Channel;
import aiss.videominer.model.Video;
import aiss.videominer.repository.ChannelRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("videominer/api/channels")
public class ChannelController {
    @Autowired
    ChannelRepository repository;

    // GET http://localhost:8080/videominer/api/channels
    @GetMapping
    public List<Channel> findAll() { return repository.findAll(); }

    // GET http://localhost:8080/videominer/api/channels/{id}
    @GetMapping("/{id}")
    public Channel findOne(@PathVariable String id) throws ChannelNotFoundException {
        Optional<Channel> channel = repository.findById(id);
        if (channel.isEmpty()) {
            throw new ChannelNotFoundException();
        }
        return channel.get();
    }

    // GET http://localhost:8080/videominer/api/channels/{id}/videos
    @GetMapping("/{id}/videos")
    public List<Video> findVideosByChannelId(@PathVariable String id) throws ChannelNotFoundException {
        Optional<Channel> channel = repository.findById(id);
        if (channel.isEmpty()) {
            throw new ChannelNotFoundException();
        }
        return channel.get().getVideos();
    }

    // POST http://localhost:8080/videominer/api/channels
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Channel create(@Valid @RequestBody Channel channel) {
        return repository.save(channel);
    }

    // POST http://localhost:8080/videominer/channels/{id}/videos
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}/videos")
    public Video create(@Valid @RequestBody Video video, @PathVariable String id) throws ChannelNotFoundException {     // id del Channel al que pertenecera el Video
        Optional<Channel> foundChannel = repository.findById(id);
        if (foundChannel.isEmpty()) {
            throw new ChannelNotFoundException();
        }
        Channel _channel = foundChannel.get();
        _channel.getVideos().add(video);
        repository.save(_channel);
        return video;
    }

    // PUT http://localhost:8080/videominer/api/channels/{id}
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Channel updatedChannel, @PathVariable String id) throws ChannelNotFoundException {
        Optional<Channel> foundChannel = repository.findById(id);
        if (foundChannel.isEmpty()) {
            throw new ChannelNotFoundException();
        }
        Channel _channel = foundChannel.get();
        _channel.setName(updatedChannel.getName());
        _channel.setDescription(updatedChannel.getDescription());
        _channel.setCreatedTime(updatedChannel.getCreatedTime());
        _channel.setVideos(updatedChannel.getVideos());
        repository.save(_channel);
    }

    // DELETE http://localhost:8080/videominer/api/channels/{id}
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }
}
