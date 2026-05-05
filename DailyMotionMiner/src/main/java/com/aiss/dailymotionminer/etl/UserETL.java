package com.aiss.dailymotionminer.etl;

import com.aiss.dailymotionminer.model.dailymotion.User;
import com.aiss.dailymotionminer.model.videominer.VMUser;
import com.aiss.dailymotionminer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class UserETL {

    private final UserService userService;

    @Autowired
    public UserETL(UserService userService) {
        this.userService = userService;
    }

    @Async
    public CompletableFuture<VMUser> transform(String videoId) {
        User owner = userService.findUserByVideoId(videoId);
        return CompletableFuture.completedFuture(new VMUser(owner.getAvatar720Url(), owner.getUrl(), owner.getUsername(), null));
    }
}
