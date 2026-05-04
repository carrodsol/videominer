package com.aiss.peertubeminer.service;

import com.aiss.peertubeminer.model.peertube.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PeerTubeService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${peertube.base.url}")
    private String baseUrl;

    public PTChannel getChannel(String channelId) {
        String url = baseUrl  + "/api/v1/video-channels/" + channelId;
        return restTemplate.getForObject(url, PTChannel.class);
    }

    public PTVideoList getVideosFromChannel(String channelId, int maxVideos) {
        String url = baseUrl + "/api/v1/video-channels/" + channelId + "/videos?count=" + maxVideos;
        return restTemplate.getForObject(url, PTVideoList.class);
    }

    public PTCaptionList getCaptions(String videoUuid) {
        String url = baseUrl + "/api/v1/videos/" + videoUuid + "/captions";
        return restTemplate.getForObject(url, PTCaptionList.class);
    }

    public PTCommentList getComments(String videoUuid, int maxComments) {
      String url = baseUrl + "/api/v1/videos/" + videoUuid + "/comment-threads?count=" + maxComments;
        return restTemplate.getForObject(url, PTCommentList.class);
    }

    public PTAccount getAccount(String accountName) {
      String url = baseUrl + "api/v1/accounts/" + accountName;
        return restTemplate.getForObject(url, PTAccount.class);
    }

}