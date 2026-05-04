package com.aiss.dailymotionminer.etl;


import com.aiss.dailymotionminer.model.dailymotion.Subtitles;
import com.aiss.dailymotionminer.model.videominer.VMCaption;
import com.aiss.dailymotionminer.service.SubtitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubtitlesETL {

    private final SubtitleService subtitleService;

    @Autowired
    public SubtitlesETL(SubtitleService subtitleService) {
        this.subtitleService = subtitleService;
    }
    public List<VMCaption> transform(String videoId) {
        List<Subtitles> subtitles = subtitleService.findSubtitlesByVideoId(videoId).getSubtitles();
        List<VMCaption> vmCaptions = new ArrayList<>();
        subtitles.forEach(subtitle -> vmCaptions.add(new VMCaption(
                subtitle.getId(),
                subtitle.getLanguage(),
                subtitle.getUrl()
        )));
        return vmCaptions;
    }
}
