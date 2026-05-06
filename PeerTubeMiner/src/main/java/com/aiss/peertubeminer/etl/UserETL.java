package com.aiss.peertubeminer.etl;

import com.aiss.peertubeminer.model.peertube.PTAccount;
import com.aiss.peertubeminer.model.peertube.PTAccountAvatar;
import com.aiss.peertubeminer.model.videominer.VMUser;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.aiss.peertubeminer.service.UserService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class UserETL {

    @Async("etlExecutor")
    public CompletableFuture<VMUser> transform(PTAccount account) {
        if (account == null) {
            return null;
        }
        VMUser vmUser = new VMUser();
        vmUser.setName(account.getName());
        vmUser.setUser_link(account.getUserLink());

        if (account.getAvatars() != null && !account.getAvatars().isEmpty()) {
            vmUser.setPicture_link(account.getAvatars().get(0).getPictureLink());
        }

        return CompletableFuture.completedFuture(vmUser);
    }
}
