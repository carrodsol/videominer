package com.aiss.peertubeminer.etl;

import com.aiss.peertubeminer.model.peertube.PTVideo;
import com.aiss.peertubeminer.model.videominer.VMCaption;
import com.aiss.peertubeminer.model.videominer.VMComment;
import com.aiss.peertubeminer.model.videominer.VMVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class VideoETL {

    private final UserETL userETL;
    private final CommentETL commentETL;
    private final CaptionETL captionETL;

    @Autowired
    public VideoETL(UserETL userETL, CommentETL commentETL, CaptionETL captionETL) {
        this.userETL = userETL;
        this.commentETL = commentETL;
        this.captionETL = captionETL;
    }
    public VMVideo transform(PTVideo video){
        if (video == null) {
            return null;
        }

        VMVideo vmVideo = new VMVideo();
        vmVideo.setId(video.getId());
        vmVideo.setName(video.getName());
        vmVideo.setDescription(video.getDescription());

        String releaseTime = video.getReleaseTime() != null ? video.getReleaseTime() : video.getCreatedAt();
        vmVideo.setReleaseTime(releaseTime);
        vmVideo.setAuthor(userETL.transform(video.getAccount()));

        if (video.getId() != null) {
            List<VMComment> comments = commentETL.transform(video.getId(), 2);
            List<VMCaption> captions = captionETL.transform(video.getId());
            vmVideo.setComments(comments);
            vmVideo.setCaptions(captions);
        } else {
            vmVideo.setComments(Collections.emptyList());
            vmVideo.setCaptions(Collections.emptyList());
        }

        return vmVideo;
    }
}
