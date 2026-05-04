package com.aiss.dailymotionminer.etl;


import com.aiss.dailymotionminer.model.dailymotion.Channels;
import com.aiss.dailymotionminer.model.videominer.VMChannel;
import com.aiss.dailymotionminer.model.videominer.VMVideo;
import com.aiss.dailymotionminer.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChannelETL {

    private final ChannelService channelService;

    @Autowired
    public ChannelETL(ChannelService channelService) {
        this.channelService = channelService;
    }

    public VMChannel transform(List<VMVideo> videos, String channelId) {
        Channels channel = channelService.findChannelById(channelId);
        return new VMChannel(channelId, videos, channel.getCreatedTime().toString(), channel.getDescription(), channel.getName());
    }
}
