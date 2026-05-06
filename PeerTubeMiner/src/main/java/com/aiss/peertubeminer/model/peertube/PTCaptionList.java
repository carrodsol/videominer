package com.aiss.peertubeminer.model.peertube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PTCaptionList {

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("data")
    private List<PTCaptionDatum> data;

    public Integer getTotal() { return total; }
    public void setTotal(Integer total) { this.total = total; }

    public List<PTCaptionDatum> getData() { return data; }
    public void setData(List<PTCaptionDatum> data) { this.data = data; }

    @Override
    public String toString() {
        return "PTCaptionList{" +
                "total=" + total +
                ", data=" + data +
                '}';
    }
}