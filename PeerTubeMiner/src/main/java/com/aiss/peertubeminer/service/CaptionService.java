package com.aiss.peertubeminer.service;

import com.aiss.peertubeminer.model.peertube.PTCaptionList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CaptionService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${peertube.base.url}")
    private String baseUrl;

    public PTCaptionList getCaptionsById(String videoUuid) {
        String url = baseUrl + "/api/v1/videos/" + videoUuid + "/captions";
        return restTemplate.getForObject(url, PTCaptionList.class);
    }
}
