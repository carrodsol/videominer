package com.aiss.peertubeminer.model.peertube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PTVideoList {

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("data")
    private List<PTVideo> data;

    public Integer getTotal() { return total; }
    public void setTotal(Integer total) { this.total = total; }

    public List<PTVideo> getData() { return data; }
    public void setData(List<PTVideo> data) { this.data = data; }
}