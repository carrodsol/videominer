package com.aiss.peertubeminer.model.peertube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PTCaptionDatum {

    @JsonProperty("language")
    private PTCaptionLanguage language;

    @JsonProperty("fileUrl")
    private String link;

    public PTCaptionLanguage getLanguage() { return language; }
    public void setLanguage(PTCaptionLanguage language) { this.language = language; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }
}