package com.aiss.dailymotionminer.service;

import com.aiss.dailymotionminer.model.dailymotion.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class VideoService {

    @Autowired
    RestTemplate restTemplate;


}
