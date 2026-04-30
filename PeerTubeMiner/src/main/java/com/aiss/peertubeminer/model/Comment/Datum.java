
package aiss.peertubeminer.model;

import java.util.List;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Datum {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("url")
    private String url;
    @JsonProperty("text")
    private String text;
    @JsonProperty("heldForReview")
    private Boolean heldForReview;
    @JsonProperty("threadId")
    private Integer threadId;
    @JsonProperty("inReplyToCommentId")
    private Integer inReplyToCommentId;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("updatedAt")
    private String updatedAt;
    @JsonProperty("account")
    private Account account;
    @JsonProperty("video")
    private Video video;
    @JsonProperty("automaticTags")
    private List<String> automaticTags;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("heldForReview")
    public Boolean getHeldForReview() {
        return heldForReview;
    }

    @JsonProperty("heldForReview")
    public void setHeldForReview(Boolean heldForReview) {
        this.heldForReview = heldForReview;
    }

    @JsonProperty("threadId")
    public Integer getThreadId() {
        return threadId;
    }

    @JsonProperty("threadId")
    public void setThreadId(Integer threadId) {
        this.threadId = threadId;
    }

    @JsonProperty("inReplyToCommentId")
    public Integer getInReplyToCommentId() {
        return inReplyToCommentId;
    }

    @JsonProperty("inReplyToCommentId")
    public void setInReplyToCommentId(Integer inReplyToCommentId) {
        this.inReplyToCommentId = inReplyToCommentId;
    }

    @JsonProperty("createdAt")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("createdAt")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("updatedAt")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updatedAt")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("account")
    public Account getAccount() {
        return account;
    }

    @JsonProperty("account")
    public void setAccount(Account account) {
        this.account = account;
    }

    @JsonProperty("video")
    public Video getVideo() {
        return video;
    }

    @JsonProperty("video")
    public void setVideo(Video video) {
        this.video = video;
    }

    @JsonProperty("automaticTags")
    public List<String> getAutomaticTags() {
        return automaticTags;
    }

    @JsonProperty("automaticTags")
    public void setAutomaticTags(List<String> automaticTags) {
        this.automaticTags = automaticTags;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Datum.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("url");
        sb.append('=');
        sb.append(((this.url == null)?"<null>":this.url));
        sb.append(',');
        sb.append("text");
        sb.append('=');
        sb.append(((this.text == null)?"<null>":this.text));
        sb.append(',');
        sb.append("heldForReview");
        sb.append('=');
        sb.append(((this.heldForReview == null)?"<null>":this.heldForReview));
        sb.append(',');
        sb.append("threadId");
        sb.append('=');
        sb.append(((this.threadId == null)?"<null>":this.threadId));
        sb.append(',');
        sb.append("inReplyToCommentId");
        sb.append('=');
        sb.append(((this.inReplyToCommentId == null)?"<null>":this.inReplyToCommentId));
        sb.append(',');
        sb.append("createdAt");
        sb.append('=');
        sb.append(((this.createdAt == null)?"<null>":this.createdAt));
        sb.append(',');
        sb.append("updatedAt");
        sb.append('=');
        sb.append(((this.updatedAt == null)?"<null>":this.updatedAt));
        sb.append(',');
        sb.append("account");
        sb.append('=');
        sb.append(((this.account == null)?"<null>":this.account));
        sb.append(',');
        sb.append("video");
        sb.append('=');
        sb.append(((this.video == null)?"<null>":this.video));
        sb.append(',');
        sb.append("automaticTags");
        sb.append('=');
        sb.append(((this.automaticTags == null)?"<null>":this.automaticTags));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
