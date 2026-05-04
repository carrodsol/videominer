package com.aiss.dailymotionminer.model.dailymotion;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "tags",
        "createdTime"
})


public class Tags {
    @JsonProperty("tags")
    private List<String> tags;
    @JsonProperty("created_time")
    private Integer createdTime;

    @JsonProperty("tags")
    public List<String> getTags() {return tags;}

    @JsonProperty("tags")
    public void setTags(List<String> tags) {this.tags=tags;}

    @JsonProperty("created_time")
    public Integer getCreatedTime() { return createdTime; }

    @JsonProperty("created_time")
    public void setCreatedTime(Integer createdTime) { this.createdTime=createdTime; }

    @Override
    public String toString() {
        return "Tags{tags='" + tags + "', createdTime='" + createdTime +"'}";
    }
}
