package com.aiss.peertubeminer.model.peertube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PTCommentList {

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("data")
    private List<PTCommentDatum> data;

    public Integer getTotal() { return total; }
    public void setTotal(Integer total) { this.total = total; }

    public List<PTCommentDatum> getData() { return data; }
    public void setData(List<PTCommentDatum> data) { this.data = data; }
}