package com.aiss.dailymotionminer.etl;

import com.aiss.dailymotionminer.model.dailymotion.Video;
import com.aiss.dailymotionminer.model.videominer.VMUser;
import org.springframework.stereotype.Component;

@Component
public class UserETL {

    public VMUser transform(Video video) {
        return new VMUser(video.getOwnerAvatar720Url(), video.getOwnerUrl(), video.getOwnerUsername(), null);
    }
}