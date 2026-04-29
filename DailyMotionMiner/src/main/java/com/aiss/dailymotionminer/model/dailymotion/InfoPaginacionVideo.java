package com.aiss.dailymotionminer.model.dailymotion;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "page",
        "limit",
        "explicit",
        "total",
        "has_more",
        "list"
})
@Generated("jsonschema2pojo")
public class InfoPaginacionVideo {

    @JsonProperty("page")
    private Integer page;
    @JsonProperty("limit")
    private Integer limit;
    @JsonProperty("explicit")
    private Boolean explicit;
    @JsonProperty("total")
    private Integer total;
    @JsonProperty("has_more")
    private Boolean hasMore;
    @JsonProperty("list")
    private java.util.List<Video> videos;

    @JsonProperty("page")
    public Integer getPage() {
        return page;
    }

    @JsonProperty("page")
    public void setPage(Integer page) {
        this.page = page;
    }

    @JsonProperty("limit")
    public Integer getLimit() {
        return limit;
    }

    @JsonProperty("limit")
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @JsonProperty("explicit")
    public Boolean getExplicit() {
        return explicit;
    }

    @JsonProperty("explicit")
    public void setExplicit(Boolean explicit) {
        this.explicit = explicit;
    }

    @JsonProperty("total")
    public Integer getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(Integer total) {
        this.total = total;
    }

    @JsonProperty("has_more")
    public Boolean getHasMore() {
        return hasMore;
    }

    @JsonProperty("has_more")
    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    @JsonProperty("list")
    public java.util.List<Video> getVideos() {
        return videos;
    }

    @JsonProperty("list")
    public void setVideos(java.util.List<Video> list) {
        this.videos = list;
    }

}