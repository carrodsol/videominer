package aiss.videominer.controller.graphql;

import aiss.videominer.exception.ChannelNotFoundException;
import aiss.videominer.model.*;
import aiss.videominer.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ChannelControllerGraphQL {
    @Autowired
    ChannelRepository repository;

    @QueryMapping
    public List<Channel> channels() {
        return repository.findAll();
    }

    @QueryMapping
    public Channel channel(@Argument String id) throws ChannelNotFoundException {
        return repository.findById(id).orElseThrow(ChannelNotFoundException::new);
    }

    @QueryMapping
    public List<Video> videosByChannel(@Argument String channelId) throws ChannelNotFoundException {
        Optional<Channel> channel = repository.findById(channelId);
        return channel.map(Channel::getVideos).orElseThrow(ChannelNotFoundException::new);
    }

    @MutationMapping
    public Channel createChannel(
            @Argument String id,
            @Argument String name,
            @Argument String description,
            @Argument String createdTime,
            @Argument List<Video> videos) {

        Channel channel = new Channel();
        if (id != null) channel.setId(id);
        guardarCanal(name, description, createdTime, videos, channel);
        return repository.save(channel);
    }

    @MutationMapping
    public Channel updateChannel(@Argument String id, @Argument String name, @Argument String description, @Argument String createdTime,
                                 @Argument List<Video> videos) throws ChannelNotFoundException {
        Optional<Channel> foundChannel = repository.findById(id);
        if (foundChannel.isPresent()) {
            Channel channel = foundChannel.get();
            guardarCanal(name, description, createdTime, videos, channel);
            repository.save(channel);
            return channel;
        } else {
            throw new ChannelNotFoundException();
        }
    }

    private void guardarCanal(@Argument String name, @Argument String description, @Argument String createdTime, @Argument List<Video> videos, Channel channel) {
        channel.setName(name);
        channel.setDescription(description);
        channel.setCreatedTime(createdTime);
        if(videos != null) {
            videos.forEach(v -> {
                if (v.getComments() == null) v.setComments(new ArrayList<>());
                if (v.getCaptions() == null) v.setCaptions(new ArrayList<>());
            });
        }
        channel.setVideos(videos != null ? videos : new ArrayList<>());
    }

    @MutationMapping
    public Boolean deleteChannel(@Argument String id) throws ChannelNotFoundException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            throw new ChannelNotFoundException();
        }
    }

    @MutationMapping
    public Video createVideo(@Argument String channelId, @Argument String id, @Argument String name, @Argument String description, @Argument String releaseTime,
    @Argument List<Comment> comments, @Argument List<Caption> captions, @Argument User author) throws ChannelNotFoundException {
        Optional<Channel> foundChannel = repository.findById(channelId);
        if (foundChannel.isPresent()) {
            Channel channel = foundChannel.get();
            Video video = new Video();
            video.setId(id);
            video.setName(name);
            video.setDescription(description);
            video.setReleaseTime(releaseTime);
            channel.getVideos().add(video);
            video.setComments(comments != null ? comments : new ArrayList<>());
            video.setCaptions(captions != null ? captions : new ArrayList<>());
            video.setAuthor(author);
            repository.save(channel);
            return video;
        } else {
            throw new ChannelNotFoundException();
        }
    }
}

