package com.aiss.dailymotionminer.service;

import com.aiss.dailymotionminer.model.dailymotion.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class TagsService {
    @Autowired
    RestTemplate restTemplate;

    @Value("${DailyMotionMiner.baseUri}")
    private String baseUri;

    // Campos solicitados, todos definidos en application.propeties:
    // tags,createdOn
    @Value("${DailyMotinminer.commentFields}")
    private String fields;

    // Encuentra los tags de un vídeo
    public Tags findTagsByVideoId(String videoId) {
        String url = this.baseUri + "/video/" + videoId + fields;
        return restTemplate.getForObject(url, Tags.class);
    }
}
