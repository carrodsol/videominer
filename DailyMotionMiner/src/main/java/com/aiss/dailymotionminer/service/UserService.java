package com.aiss.dailymotionminer.service;


import com.aiss.dailymotionminer.model.dailymotion.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${DailyMotionMiner.baseUri}")
    private String baseUri;

    // Campos solicitados, todos definidos en application.propeties:
    // owner.id,owner.username,owner.url,owner.avatar_720_url
    @Value("${DailyMotionMiner.userFields}")
    private String fields;

    // Encuentra el usuario de un video en concreto
    // GET https://api.dailymotion.com/video/:id?fields=owner.id,owner.username,owner.url,owner.avatar_720_url;
    public User findUserByVideoId(String videoId) {
        String url = this.baseUri + "/video/" + videoId + fields;
        return restTemplate.getForObject(url, User.class);
    }



}
