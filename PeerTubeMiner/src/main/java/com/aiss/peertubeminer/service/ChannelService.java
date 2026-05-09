package com.aiss.peertubeminer.service;

import com.aiss.peertubeminer.model.peertube.PTChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChannelService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${peertube.base.url}")
    private String baseUrl;

    public PTChannel getChannelById(String channelId) {
        String url = baseUrl  + "/api/v1/video-channels/" + channelId;
        return restTemplate.getForObject(url, PTChannel.class);
    }
}
