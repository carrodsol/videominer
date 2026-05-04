package com.aiss.dailymotionminer.etl;

import com.aiss.dailymotionminer.model.dailymotion.Video;
import com.aiss.dailymotionminer.model.videominer.VMCaption;
import com.aiss.dailymotionminer.model.videominer.VMComment;
import com.aiss.dailymotionminer.model.videominer.VMUser;
import com.aiss.dailymotionminer.model.videominer.VMVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
    public VMVideo transform(Video video) {
        VMUser vmUser = userETL.transform(video.getId());
        List<VMComment> vmComments = commentETL.transform(video.getId());
        List<VMCaption> vmCaptions = subtitlesETL.transform(video.getId());
        return new VMVideo(video.getId(), video.getTitle(), video.getDescription(), vmUser, video.getCreatedTime().toString(), vmComments, vmCaptions);
    }
}
