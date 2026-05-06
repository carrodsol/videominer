package com.aiss.peertubeminer.service;

import com.aiss.peertubeminer.model.peertube.PTCommentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CommentService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${peertube.base.url}")
    private String baseUrl;

    public PTCommentList getComments(String videoUuid, int maxComments) {
        String url = baseUrl + "/api/v1/videos/" + videoUuid + "/comment-threads?count=" + maxComments;
        return restTemplate.getForObject(url, PTCommentList.class);
    }
}
