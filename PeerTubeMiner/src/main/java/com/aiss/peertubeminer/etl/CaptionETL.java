package com.aiss.peertubeminer.etl;

import com.aiss.peertubeminer.model.peertube.PTCaptionDatum;
import com.aiss.peertubeminer.model.videominer.VMCaption;
import com.aiss.peertubeminer.service.CaptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class CaptionETL {

    private final CaptionService captionService;

    @Autowired
    public CaptionETL(CaptionService captionService) {
        this.captionService = captionService;
    }

    @Async("etlExecutor")
    public CompletableFuture<List<VMCaption>> transform(String videoId) {
        List<PTCaptionDatum> captions = captionService.getCaptionsById(videoId).getData();
        List<VMCaption> vmCaptions = new ArrayList<>();
        if (captions == null || captions.isEmpty()) {
            return CompletableFuture.completedFuture(vmCaptions);
        }

        captions.forEach(ptCaption -> {
            if (ptCaption == null) {
                return;
            }
            VMCaption caption = new VMCaption();
            if (ptCaption.getLanguage() != null) {
                caption.setId(ptCaption.getLanguage().getId());
                caption.setName(ptCaption.getLink());
                caption.setLanguage(ptCaption.getLanguage().getLabel());
            
            vmCaptions.add(caption);
        }});
        return CompletableFuture.completedFuture(vmCaptions);
    }



}
