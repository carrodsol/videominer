package com.aiss.peertubeminer.service;

import com.aiss.peertubeminer.model.peertube.PTAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${peertube.base.url}")
    private String baseUrl;

    public PTAccount getAccount(String accountName) {
        String url = baseUrl + "/api/v1/accounts/" + accountName;
        return restTemplate.getForObject(url, PTAccount.class);
    }
}
