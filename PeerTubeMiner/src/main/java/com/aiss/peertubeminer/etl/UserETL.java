package com.aiss.peertubeminer.etl;

import com.aiss.peertubeminer.model.peertube.PTAccount;
import com.aiss.peertubeminer.model.peertube.PTAccountAvatar;
import com.aiss.peertubeminer.model.videominer.VMUser;

import java.util.List;

import com.aiss.peertubeminer.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserETL {

    private final UserService userService;

    public UserETL(UserService userService) {
        this.userService = userService;
    }
    public VMUser transform(PTAccount account) {
        if (account == null) {
            return null;
        }

        VMUser user = new VMUser();
        user.setName(account.getName());
        user.setUser_link(account.getUserLink());

        List<PTAccountAvatar> avatars = account.getAvatars();
        if (avatars != null && !avatars.isEmpty() && avatars.get(0) != null) {
            user.setPicture_link(avatars.get(0).getPictureLink());
        }

        return user;
    }
}
