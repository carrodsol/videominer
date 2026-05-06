package com.aiss.dailymotionminer.etl;


import com.aiss.dailymotionminer.model.dailymotion.Subtitles;
import com.aiss.dailymotionminer.model.videominer.VMCaption;
import com.aiss.dailymotionminer.service.SubtitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class SubtitlesETL {

    private final SubtitleService subtitleService;

    @Autowired
    public SubtitlesETL(SubtitleService subtitleService) {
        this.subtitleService = subtitleService;
    }

    @Async("etlExecutor")
    public CompletableFuture<List<VMCaption>> transform(String videoId) {
        List<Subtitles> subtitles = subtitleService.findSubtitlesByVideoId(videoId).getSubtitles();
        List<VMCaption> vmCaptions = new ArrayList<>();
        subtitles.forEach(subtitle -> vmCaptions.add(new VMCaption(
                subtitle.getId(),
                subtitle.getLanguage(),
                subtitle.getUrl()
        )));
        return CompletableFuture.completedFuture(vmCaptions);
    }
}
