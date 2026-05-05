package com.aiss.peertubeminer.etl;


import com.aiss.peertubeminer.model.peertube.PTChannel;
import com.aiss.peertubeminer.model.videominer.VMChannel;
import com.aiss.peertubeminer.model.videominer.VMVideo;
import com.aiss.peertubeminer.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChannelETL {

    private final ChannelService channelService;
    private final VideoETL videoETL;

    @Autowired
    public ChannelETL(ChannelService channelService, VideoETL videoETL) {
        this.channelService = channelService;
        this.videoETL = videoETL;
    }

    public VMChannel transform(String channelId, int maxVideos, int maxComments) {
        PTChannel channel = channelService.getChannelById(channelId);

        // Delegamos a VideoETL la responsabilidad de usar maxVideos y maxComments
        List<VMVideo> vmVideos = videoETL.transform(channelId, maxVideos, maxComments);

        return new VMChannel(channelId, vmVideos, channel.getCreatedTime().toString(), channel.getDescription(), channel.getName());
    }
}
