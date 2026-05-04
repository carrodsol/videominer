package com.aiss.dailymotionminer.etl;

import com.aiss.dailymotionminer.model.dailymotion.Subtitles;
import com.aiss.dailymotionminer.model.dailymotion.User;
import com.aiss.dailymotionminer.model.dailymotion.Video;
import com.aiss.dailymotionminer.model.videominer.VMCaption;
import com.aiss.dailymotionminer.model.videominer.VMComment;
import com.aiss.dailymotionminer.model.videominer.VMUser;
import com.aiss.dailymotionminer.model.videominer.VMVideo;
import com.aiss.dailymotionminer.service.SubtitleService;
import com.aiss.dailymotionminer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VideoETL {

    private final UserService userService;
    private final SubtitleService subtitleService;
    @Autowired
    public VideoETL(UserService userService, SubtitleService subtitleService) {
        this.userService = userService;
        this.subtitleService = subtitleService;
    }
    public VMVideo transform(Video video) {
        /*
        this.id = id;
        this.name = name;
        this.description = description;
        this.author = author;
        this.releaseTime = releaseTime;
        this.comments = comments;
        this.captions = captions;
         */
        // TODO: Hacer transformaciones
        User owner = userService.findUserByVideoId(video.getId());
        List<Subtitles> subtitles = subtitleService.findSubtitlesByVideoId(video.getId()).getSubtitles();
        List<String> comments = video.getTags();
        VMUser vmUser = new VMUser(owner.getAvatar720Url(), owner.getUrl(), owner.getUsername(), owner.getId());
        List<VMComment> vmComments = new ArrayList<>();
        // A cada video se le asocia un comentario con un id autogenerado empezando por 0
        for(int i=0; i<comments.size(); i++) {
            vmComments.add(
                    new VMComment(
                            video.getId()+"_"+i,
                            // Cada comentario tiene como fecha de creación la del vídeo
                            video.getCreatedTime().toString(),
                            comments.get(i)
                    )
            );
        }
        List<VMCaption> vmCaptions = new ArrayList<>();
        subtitles.forEach(subtitle -> vmCaptions.add(new VMCaption(
                subtitle.getId(),
                subtitle.getLanguage(),
                subtitle.getUrl()
        )));

        return new VMVideo(video.getId(), video.getTitle(), video.getDescription(), vmUser, video.getCreatedTime().toString(), vmComments, vmCaptions);
    }
}
