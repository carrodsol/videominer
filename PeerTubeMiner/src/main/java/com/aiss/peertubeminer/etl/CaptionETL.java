package com.aiss.peertubeminer.etl;

import com.aiss.peertubeminer.model.peertube.PTCaptionDatum;
import com.aiss.peertubeminer.model.peertube.PTCaptionList;
import com.aiss.peertubeminer.model.videominer.VMCaption;
import com.aiss.peertubeminer.service.PeerTubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CaptionETL {

    private final PeerTubeService captionService;

    @Autowired
    public CaptionETL(PeerTubeService captionService) {
        this.captionService = captionService;
    }

    public List<VMCaption> transform(String videoId) {
        List<PTCaptionDatum> captions = captionService.getCaptions(videoId).getData();
        List<VMCaption> vmCaptions = new ArrayList<>();
        captions.forEach(caption -> vmCaptions.add(new VMCaption(
                caption.getLanguage().getId(),
                caption.getLanguage().getLabel(),
                caption.getLink()
        )));
        return vmCaptions;
    }

    // TODO: FIX ETL

}
