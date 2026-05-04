package com.aiss.peertubeminer.model.peertube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PTAccountAvatar {

    @JsonProperty("fileUrl")
    private String pictureLink;

    public String getPictureLink() { return pictureLink; }
    public void setPictureLink(String pictureLink) { this.pictureLink = pictureLink; }
}