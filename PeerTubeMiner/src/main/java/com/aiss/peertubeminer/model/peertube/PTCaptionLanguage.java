package com.aiss.peertubeminer.model.peertube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PTCaptionLanguage {

    @JsonProperty("id")
    private String id;

    @JsonProperty("label")
    private String label;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    @Override
    public String toString() {
        return "PTCaptionLanguage{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}