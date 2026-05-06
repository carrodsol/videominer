package com.aiss.peertubeminer.controller;

import com.aiss.peertubeminer.etl.ChannelETL;
import com.aiss.peertubeminer.model.videominer.VMChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/peertubeminer")
public class ChannelController {

    @Autowired
    ChannelETL channelETL;

    @Autowired
    RestTemplate restTemplate;

    @Value("${videominer.base.url:http://localhost:8080}")
    private String videoMinerBaseUrl;

    @GetMapping("/{id}")
    public VMChannel getChannel(@PathVariable String id,
                                @RequestParam(defaultValue = "10") int maxVideos,
                                @RequestParam(defaultValue = "2") int maxComments) {

        return channelETL.transform(id, maxVideos, maxComments);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public VMChannel postChannel(@PathVariable String id,
                                 @RequestParam(defaultValue = "10") int maxVideos,
                                 @RequestParam(defaultValue = "2") int maxComments) {
        VMChannel channel = getChannel(id, maxVideos, maxComments);
        // Load into VideoMiner
        restTemplate.postForObject(videoMinerBaseUrl + "/videominer/channels", channel, VMChannel.class);
        return channel;
    }
}
