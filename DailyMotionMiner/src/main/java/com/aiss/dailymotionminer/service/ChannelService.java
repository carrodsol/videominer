package com.aiss.dailymotionminer.service;


import com.aiss.dailymotionminer.model.dailymotion.Channels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChannelService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${DailyMotionMiner.baseUri}")
    private String baseUri;

    // Campos solicitados, todos definidos en application.propeties:
    // id,name,description (por defecto vienen esos)
    @Value("${DailyMotionMiner.channelFields}")
    private String fields;

    // Encuentra un canal concreto
    // GET https://api.dailymotion.com/channel/:id
    public Channels findChannelById(String id) {
        String url = this.baseUri + "/channel/" + id + fields;
        return restTemplate.getForObject(url, Channels.class);
    }

}
