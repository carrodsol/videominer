package com.aiss.peertubeminer.service;

import com.aiss.peertubeminer.model.peertube.PTVideoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VideoService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${peertube.base.url}")
    private String baseUrl;

    public PTVideoList getVideosFromChannel(String channelId, int maxVideos) {
        String url = baseUrl + "/api/v1/video-channels/" + channelId + "/videos?count=" + maxVideos;
        return restTemplate.getForObject(url, PTVideoList.class);
    }
}


