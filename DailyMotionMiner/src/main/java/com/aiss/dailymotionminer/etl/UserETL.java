package com.aiss.dailymotionminer.etl;

import com.aiss.dailymotionminer.model.dailymotion.User;
import com.aiss.dailymotionminer.model.videominer.VMUser;
import com.aiss.dailymotionminer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserETL {

    private final UserService userService;

    @Autowired
    public UserETL(UserService userService) {
        this.userService = userService;
    }
    public VMUser transform(String videoId) {
        User owner = userService.findUserByVideoId(videoId);
        return new VMUser(owner.getAvatar720Url(), owner.getUrl(), owner.getUsername(), owner.getId());
    }
}
