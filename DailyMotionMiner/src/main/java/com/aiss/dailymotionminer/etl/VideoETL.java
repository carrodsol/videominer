package com.aiss.dailymotionminer.etl;

import com.aiss.dailymotionminer.model.dailymotion.Video;
import com.aiss.dailymotionminer.model.videominer.VMCaption;
import com.aiss.dailymotionminer.model.videominer.VMComment;
import com.aiss.dailymotionminer.model.videominer.VMUser;
import com.aiss.dailymotionminer.model.videominer.VMVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class VideoETL {

    private final UserETL userETL;
    private final CommentETL commentETL;
    private final SubtitlesETL subtitlesETL;

    @Autowired
    public VideoETL(UserETL userETL, CommentETL commentETL,  SubtitlesETL subtitlesETL) {
        this.userETL = userETL;
        this.subtitlesETL = subtitlesETL;
        this.commentETL = commentETL;
    }

    // https://spring.io/guides/gs/async-method Usamos asincronía porque luego cada transform hace llamadas a la API
    // Antes eran muy lentas, ahora carga más rápido
    public CompletableFuture<VMVideo> transform(Video video) {
        CompletableFuture<VMUser> vmUser = userETL.transform(video.getId());

        CompletableFuture<List<VMComment>> vmComments = commentETL.transform(video.getId());

        CompletableFuture<List<VMCaption>> vmCaptions = subtitlesETL.transform(video.getId());

        return CompletableFuture.allOf(vmUser, vmComments, vmCaptions)
                .thenApply(v -> {
                    VMUser user = vmUser.join();
                    List<VMComment> comments = vmComments.join();
                    List<VMCaption> captions = vmCaptions.join();

                    return new VMVideo(
                            video.getId(),
                            video.getTitle(),
                            video.getDescription(),
                            user,
                            video.getCreatedTime().toString(),
                            comments,
                            captions
                    );
                });
    }
}
