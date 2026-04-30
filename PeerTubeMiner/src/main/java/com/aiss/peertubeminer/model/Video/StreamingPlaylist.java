
package aiss.peertubeminer.model;

import java.util.List;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StreamingPlaylist {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("type")
    private Integer type;
    @JsonProperty("playlistUrl")
    private String playlistUrl;
    @JsonProperty("segmentsSha256Url")
    private String segmentsSha256Url;
    @JsonProperty("files")
    private List<File__1> files;
    @JsonProperty("redundancies")
    private List<Redundancy> redundancies;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("type")
    public Integer getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(Integer type) {
        this.type = type;
    }

    @JsonProperty("playlistUrl")
    public String getPlaylistUrl() {
        return playlistUrl;
    }

    @JsonProperty("playlistUrl")
    public void setPlaylistUrl(String playlistUrl) {
        this.playlistUrl = playlistUrl;
    }

    @JsonProperty("segmentsSha256Url")
    public String getSegmentsSha256Url() {
        return segmentsSha256Url;
    }

    @JsonProperty("segmentsSha256Url")
    public void setSegmentsSha256Url(String segmentsSha256Url) {
        this.segmentsSha256Url = segmentsSha256Url;
    }

    @JsonProperty("files")
    public List<File__1> getFiles() {
        return files;
    }

    @JsonProperty("files")
    public void setFiles(List<File__1> files) {
        this.files = files;
    }

    @JsonProperty("redundancies")
    public List<Redundancy> getRedundancies() {
        return redundancies;
    }

    @JsonProperty("redundancies")
    public void setRedundancies(List<Redundancy> redundancies) {
        this.redundancies = redundancies;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(StreamingPlaylist.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("playlistUrl");
        sb.append('=');
        sb.append(((this.playlistUrl == null)?"<null>":this.playlistUrl));
        sb.append(',');
        sb.append("segmentsSha256Url");
        sb.append('=');
        sb.append(((this.segmentsSha256Url == null)?"<null>":this.segmentsSha256Url));
        sb.append(',');
        sb.append("files");
        sb.append('=');
        sb.append(((this.files == null)?"<null>":this.files));
        sb.append(',');
        sb.append("redundancies");
        sb.append('=');
        sb.append(((this.redundancies == null)?"<null>":this.redundancies));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
