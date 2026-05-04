package com.aiss.peertubeminer.etl;


import com.aiss.peertubeminer.model.peertube.PTChannel;
import com.aiss.peertubeminer.model.videominer.VMChannel;
import com.aiss.peertubeminer.model.videominer.VMVideo;
import com.aiss.peertubeminer.service.PeerTubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChannelETL {

    private final PeerTubeService service;

        @Autowired
        public ChannelETL(PeerTubeService service) {
            this.service = service;
        }

        public VMChannel transform(List<VMVideo> videos, String channelId) {
            PTChannel channel = service.getChannel(channelId);
            return new VMChannel(channelId, videos, channel.getCreatedTime().toString(), channel.getDescription(), channel.getName());
        }
    }
