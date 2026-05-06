package com.aiss.peertubeminer.etl;

import com.aiss.peertubeminer.model.peertube.PTVideo;
import com.aiss.peertubeminer.model.videominer.VMCaption;
import com.aiss.peertubeminer.model.videominer.VMComment;
import com.aiss.peertubeminer.model.videominer.VMUser;
import com.aiss.peertubeminer.model.videominer.VMVideo;
import com.aiss.peertubeminer.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class VideoETL {

    private final UserETL userETL;
    private final CommentETL commentETL;
    private final CaptionETL captionETL;
    private final VideoService videoService;

    @Autowired
    public VideoETL(UserETL userETL, CommentETL commentETL, CaptionETL captionETL, VideoService videoService) {
        this.userETL = userETL;
        this.commentETL = commentETL;
        this.captionETL = captionETL;
        this.videoService = videoService;
    }

    @Async("etlExecutor")
    public CompletableFuture<List<VMVideo>> transform(String channelId, int maxVideos, int maxComments) {
        List<PTVideo> ptVideos = videoService.getVideosFromChannel(channelId, maxVideos).getData();

        if (ptVideos == null || ptVideos.isEmpty()) {
            return CompletableFuture.completedFuture(Collections.emptyList());
        }

        List<CompletableFuture<VMVideo>> futures = ptVideos.stream()
                .filter(video -> video != null)
                .map(video -> {
                    VMVideo vmVideo = new VMVideo();
                    vmVideo.setId(video.getId());
                    vmVideo.setName(video.getName());
                    vmVideo.setDescription(video.getDescription());

                    String releaseTime = video.getReleaseTime() != null ? video.getReleaseTime() : video.getCreatedAt();
                    vmVideo.setReleaseTime(releaseTime);

                    CompletableFuture<VMUser> authorFuture = userETL.transform(video.getAccount());
                    if (authorFuture == null) {
                        authorFuture = CompletableFuture.completedFuture(null);
                    }

                    CompletableFuture<List<VMComment>> commentsFuture;
                    CompletableFuture<List<VMCaption>> captionsFuture;

                    if (video.getId() != null) {
                        commentsFuture = commentETL.transform(video.getId(), maxComments);
                        captionsFuture = captionETL.transform(video.getId());
                    } else {
                        commentsFuture = CompletableFuture.completedFuture(Collections.emptyList());
                        captionsFuture = CompletableFuture.completedFuture(Collections.emptyList());
                    }

                    CompletableFuture<VMUser> finalAuthorFuture = authorFuture;
                    return CompletableFuture.allOf(finalAuthorFuture, commentsFuture, captionsFuture)
                            .thenApply(v -> {
                                vmVideo.setAuthor(finalAuthorFuture.join());
                                
                                List<VMComment> comments = commentsFuture.join();
                                vmVideo.setComments(comments != null ? comments : Collections.emptyList());
                                
                                List<VMCaption> captions = captionsFuture.join();
                                vmVideo.setCaptions(captions != null ? captions : Collections.emptyList());
                                
                                return vmVideo;
                            });
                })
                .collect(Collectors.toList());

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList()));
    }
}
