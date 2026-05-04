package com.aiss.peertubeminer.model.peertube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PTVideo {

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("publishedAt")
    private String releaseTime;

    @JsonProperty("createdAt")
    private String createdAt;

    @JsonProperty("account")
    private PTAccount account;

    public String getUuid() { return uuid; }
    public void setUuid(String uuid) { this.uuid = uuid; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getReleaseTime() { return releaseTime; }
    public void setReleaseTime(String releaseTime) { this.releaseTime = releaseTime; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public PTAccount getAccount() { return account; }
    public void setAccount(PTAccount account) { this.account = account; }
}