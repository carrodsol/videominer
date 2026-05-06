package com.aiss.dailymotionminer.service;


import com.aiss.dailymotionminer.model.dailymotion.InfoPaginacionSubtitles;
import com.aiss.dailymotionminer.model.dailymotion.Subtitles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SubtitleService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${DailyMotionMiner.baseUri}")
    private String baseUri;

    // Campos solicitados, todos definidos en application.propeties:
    // id,link,languages
    @Value("${DailyMotionMiner.subtitleFields}")
    private String fields;

    // Encuentra los subtítulos de un vídeo en concreto
    public InfoPaginacionSubtitles findSubtitlesByVideoId(String videoId) {
        String url = this.baseUri + "/video/" + videoId + "/subtitles" + fields;
        return restTemplate.getForObject(url, InfoPaginacionSubtitles.class);
    }


}
