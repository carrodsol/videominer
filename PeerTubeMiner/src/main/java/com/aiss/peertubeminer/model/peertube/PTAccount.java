package com.aiss.peertubeminer.model.peertube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PTAccount {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("url")
    private String userLink;

    @JsonProperty("avatars")
    private List<PTAccountAvatar> avatars;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUserLink() { return userLink; }
    public void setUserLink(String userLink) { this.userLink = userLink; }

    public List<PTAccountAvatar> getAvatars() { return avatars; }
    public void setAvatars(List<PTAccountAvatar> avatars) { this.avatars = avatars; }

    @Override
    public String toString() {
        return "PTAccount{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userLink='" + userLink + '\'' +
                ", avatars=" + avatars +
                '}';
    }
}