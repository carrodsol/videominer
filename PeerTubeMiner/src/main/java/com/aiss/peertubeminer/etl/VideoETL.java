package com.aiss.peertubeminer.etl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    //TODO: FINISH ETL
}
