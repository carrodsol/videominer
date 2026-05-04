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

    @JsonProperty("displayName")
    private String displayName;

    @JsonProperty("url")
    private String url;

    @JsonProperty("avatars")
    private List<PTAvatar> avatars;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public List<PTAvatar> getAvatars() { return avatars; }
    public void setAvatars(List<PTAvatar> avatars) { this.avatars = avatars; }
}