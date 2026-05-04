package com.aiss.dailymotionminer.etl;

import com.aiss.dailymotionminer.model.dailymotion.User;
import com.aiss.dailymotionminer.model.dailymotion.Video;
import com.aiss.dailymotionminer.model.videominer.VMVideo;
import com.aiss.dailymotionminer.service.SubtitleService;
import com.aiss.dailymotionminer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        String comments = null; // TODO
        return null;
    }
}
