package com.aiss.peertubeminer.etl;

import com.aiss.peertubeminer.model.videominer.VMUser;
import com.aiss.peertubeminer.service.PeerTubeService;
import org.springframework.stereotype.Component;

@Component
public class UserETL {

    private final PeerTubeService userService;
    public UserETL(PeerTubeService userService) {
        this.userService = userService;
    }
    //TODO
    public VMUser transform() {
        return null;
    }
}
