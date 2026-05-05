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

    private final ChannelService service;

        @Autowired
        public ChannelETL(ChannelService service) {
            this.service = service;
        }

        public VMChannel transform(List<VMVideo> videos, String channelId) {
            PTChannel channel = service.getChannelById(channelId);
            return new VMChannel(channelId, videos, channel.getCreatedTime().toString(), channel.getDescription(), channel.getName());
        }
    }
