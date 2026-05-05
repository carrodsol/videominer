package com.aiss.dailymotionminer.model.dailymotion;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "username",
        "url",
        "avatar_720_url"
})
@Generated("jsonschema2pojo")
public class User {

    @JsonProperty("owner.id")
    private String id;
    @JsonProperty("owner.username")
    private String username;
    @JsonProperty("owner.url")
    private String url;
    @JsonProperty("owner.avatar_720_url")
    private String avatar720Url;

    @JsonProperty("owner.id")
    public String getId() {
        return id;
    }

    @JsonProperty("owner.id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("owner.username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("owner.username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("owner.url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("owner.url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("owner.avatar_720_url")
    public String getAvatar720Url() {
        return avatar720Url;
    }

    @JsonProperty("owner.avatar_720_url")
    public void setAvatar720Url(String avatar720Url) {
        this.avatar720Url = avatar720Url;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", url='" + url + '\'' +
                ", avatar720Url='" + avatar720Url + '\'' +
                '}';
    }
}