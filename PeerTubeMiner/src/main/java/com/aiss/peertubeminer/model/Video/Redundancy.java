
package aiss.peertubeminer.model;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Redundancy {

    @JsonProperty("baseUrl")
    private String baseUrl;

    @JsonProperty("baseUrl")
    public String getBaseUrl() {
        return baseUrl;
    }

    @JsonProperty("baseUrl")
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Redundancy.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("baseUrl");
        sb.append('=');
        sb.append(((this.baseUrl == null)?"<null>":this.baseUrl));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
