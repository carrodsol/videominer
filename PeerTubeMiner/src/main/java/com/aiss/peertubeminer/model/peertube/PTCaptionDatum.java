package com.aiss.peertubeminer.model.peertube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PTCaptionDatum {

    @JsonProperty("language")
    private PTCaptionLanguage language;

    @JsonProperty("fileUrl")
    private String fileUrl;

    public PTCaptionLanguage getLanguage() { return language; }
    public void setLanguage(PTCaptionLanguage language) { this.language = language; }

    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }
}