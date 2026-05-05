package com.aiss.peertubeminer.etl;

import com.aiss.peertubeminer.model.peertube.PTAccount;
import com.aiss.peertubeminer.model.peertube.PTAccountAvatar;
import com.aiss.peertubeminer.model.videominer.VMUser;

import java.util.List;

import com.aiss.peertubeminer.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserETL {

    public VMUser transform(PTAccount account) {
        VMUser vmUser = new VMUser();
        vmUser.setName(account.getName());
        vmUser.setUser_link(account.getUserLink());

        if (account.getAvatars() != null && !account.getAvatars().isEmpty()) {
            vmUser.setPicture_link(account.getAvatars().get(0).getPictureLink());
        }

        return vmUser;
    }
}
