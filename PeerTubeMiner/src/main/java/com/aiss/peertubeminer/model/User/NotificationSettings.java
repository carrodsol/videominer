
package aiss.peertubeminer.model;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationSettings {

    @JsonProperty("abuseAsModerator")
    private Integer abuseAsModerator;
    @JsonProperty("videoAutoBlacklistAsModerator")
    private Integer videoAutoBlacklistAsModerator;
    @JsonProperty("newUserRegistration")
    private Integer newUserRegistration;
    @JsonProperty("newVideoFromSubscription")
    private Integer newVideoFromSubscription;
    @JsonProperty("blacklistOnMyVideo")
    private Integer blacklistOnMyVideo;
    @JsonProperty("myVideoPublished")
    private Integer myVideoPublished;
    @JsonProperty("myVideoImportFinished")
    private Integer myVideoImportFinished;
    @JsonProperty("commentMention")
    private Integer commentMention;
    @JsonProperty("newCommentOnMyVideo")
    private Integer newCommentOnMyVideo;
    @JsonProperty("newFollow")
    private Integer newFollow;
    @JsonProperty("newInstanceFollower")
    private Integer newInstanceFollower;
    @JsonProperty("autoInstanceFollowing")
    private Integer autoInstanceFollowing;
    @JsonProperty("abuseStateChange")
    private Integer abuseStateChange;
    @JsonProperty("abuseNewMessage")
    private Integer abuseNewMessage;
    @JsonProperty("newPeerTubeVersion")
    private Integer newPeerTubeVersion;
    @JsonProperty("newPluginVersion")
    private Integer newPluginVersion;
    @JsonProperty("myVideoStudioEditionFinished")
    private Integer myVideoStudioEditionFinished;
    @JsonProperty("myVideoTranscriptionGenerated")
    private Integer myVideoTranscriptionGenerated;

    @JsonProperty("abuseAsModerator")
    public Integer getAbuseAsModerator() {
        return abuseAsModerator;
    }

    @JsonProperty("abuseAsModerator")
    public void setAbuseAsModerator(Integer abuseAsModerator) {
        this.abuseAsModerator = abuseAsModerator;
    }

    @JsonProperty("videoAutoBlacklistAsModerator")
    public Integer getVideoAutoBlacklistAsModerator() {
        return videoAutoBlacklistAsModerator;
    }

    @JsonProperty("videoAutoBlacklistAsModerator")
    public void setVideoAutoBlacklistAsModerator(Integer videoAutoBlacklistAsModerator) {
        this.videoAutoBlacklistAsModerator = videoAutoBlacklistAsModerator;
    }

    @JsonProperty("newUserRegistration")
    public Integer getNewUserRegistration() {
        return newUserRegistration;
    }

    @JsonProperty("newUserRegistration")
    public void setNewUserRegistration(Integer newUserRegistration) {
        this.newUserRegistration = newUserRegistration;
    }

    @JsonProperty("newVideoFromSubscription")
    public Integer getNewVideoFromSubscription() {
        return newVideoFromSubscription;
    }

    @JsonProperty("newVideoFromSubscription")
    public void setNewVideoFromSubscription(Integer newVideoFromSubscription) {
        this.newVideoFromSubscription = newVideoFromSubscription;
    }

    @JsonProperty("blacklistOnMyVideo")
    public Integer getBlacklistOnMyVideo() {
        return blacklistOnMyVideo;
    }

    @JsonProperty("blacklistOnMyVideo")
    public void setBlacklistOnMyVideo(Integer blacklistOnMyVideo) {
        this.blacklistOnMyVideo = blacklistOnMyVideo;
    }

    @JsonProperty("myVideoPublished")
    public Integer getMyVideoPublished() {
        return myVideoPublished;
    }

    @JsonProperty("myVideoPublished")
    public void setMyVideoPublished(Integer myVideoPublished) {
        this.myVideoPublished = myVideoPublished;
    }

    @JsonProperty("myVideoImportFinished")
    public Integer getMyVideoImportFinished() {
        return myVideoImportFinished;
    }

    @JsonProperty("myVideoImportFinished")
    public void setMyVideoImportFinished(Integer myVideoImportFinished) {
        this.myVideoImportFinished = myVideoImportFinished;
    }

    @JsonProperty("commentMention")
    public Integer getCommentMention() {
        return commentMention;
    }

    @JsonProperty("commentMention")
    public void setCommentMention(Integer commentMention) {
        this.commentMention = commentMention;
    }

    @JsonProperty("newCommentOnMyVideo")
    public Integer getNewCommentOnMyVideo() {
        return newCommentOnMyVideo;
    }

    @JsonProperty("newCommentOnMyVideo")
    public void setNewCommentOnMyVideo(Integer newCommentOnMyVideo) {
        this.newCommentOnMyVideo = newCommentOnMyVideo;
    }

    @JsonProperty("newFollow")
    public Integer getNewFollow() {
        return newFollow;
    }

    @JsonProperty("newFollow")
    public void setNewFollow(Integer newFollow) {
        this.newFollow = newFollow;
    }

    @JsonProperty("newInstanceFollower")
    public Integer getNewInstanceFollower() {
        return newInstanceFollower;
    }

    @JsonProperty("newInstanceFollower")
    public void setNewInstanceFollower(Integer newInstanceFollower) {
        this.newInstanceFollower = newInstanceFollower;
    }

    @JsonProperty("autoInstanceFollowing")
    public Integer getAutoInstanceFollowing() {
        return autoInstanceFollowing;
    }

    @JsonProperty("autoInstanceFollowing")
    public void setAutoInstanceFollowing(Integer autoInstanceFollowing) {
        this.autoInstanceFollowing = autoInstanceFollowing;
    }

    @JsonProperty("abuseStateChange")
    public Integer getAbuseStateChange() {
        return abuseStateChange;
    }

    @JsonProperty("abuseStateChange")
    public void setAbuseStateChange(Integer abuseStateChange) {
        this.abuseStateChange = abuseStateChange;
    }

    @JsonProperty("abuseNewMessage")
    public Integer getAbuseNewMessage() {
        return abuseNewMessage;
    }

    @JsonProperty("abuseNewMessage")
    public void setAbuseNewMessage(Integer abuseNewMessage) {
        this.abuseNewMessage = abuseNewMessage;
    }

    @JsonProperty("newPeerTubeVersion")
    public Integer getNewPeerTubeVersion() {
        return newPeerTubeVersion;
    }

    @JsonProperty("newPeerTubeVersion")
    public void setNewPeerTubeVersion(Integer newPeerTubeVersion) {
        this.newPeerTubeVersion = newPeerTubeVersion;
    }

    @JsonProperty("newPluginVersion")
    public Integer getNewPluginVersion() {
        return newPluginVersion;
    }

    @JsonProperty("newPluginVersion")
    public void setNewPluginVersion(Integer newPluginVersion) {
        this.newPluginVersion = newPluginVersion;
    }

    @JsonProperty("myVideoStudioEditionFinished")
    public Integer getMyVideoStudioEditionFinished() {
        return myVideoStudioEditionFinished;
    }

    @JsonProperty("myVideoStudioEditionFinished")
    public void setMyVideoStudioEditionFinished(Integer myVideoStudioEditionFinished) {
        this.myVideoStudioEditionFinished = myVideoStudioEditionFinished;
    }

    @JsonProperty("myVideoTranscriptionGenerated")
    public Integer getMyVideoTranscriptionGenerated() {
        return myVideoTranscriptionGenerated;
    }

    @JsonProperty("myVideoTranscriptionGenerated")
    public void setMyVideoTranscriptionGenerated(Integer myVideoTranscriptionGenerated) {
        this.myVideoTranscriptionGenerated = myVideoTranscriptionGenerated;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(NotificationSettings.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("abuseAsModerator");
        sb.append('=');
        sb.append(((this.abuseAsModerator == null)?"<null>":this.abuseAsModerator));
        sb.append(',');
        sb.append("videoAutoBlacklistAsModerator");
        sb.append('=');
        sb.append(((this.videoAutoBlacklistAsModerator == null)?"<null>":this.videoAutoBlacklistAsModerator));
        sb.append(',');
        sb.append("newUserRegistration");
        sb.append('=');
        sb.append(((this.newUserRegistration == null)?"<null>":this.newUserRegistration));
        sb.append(',');
        sb.append("newVideoFromSubscription");
        sb.append('=');
        sb.append(((this.newVideoFromSubscription == null)?"<null>":this.newVideoFromSubscription));
        sb.append(',');
        sb.append("blacklistOnMyVideo");
        sb.append('=');
        sb.append(((this.blacklistOnMyVideo == null)?"<null>":this.blacklistOnMyVideo));
        sb.append(',');
        sb.append("myVideoPublished");
        sb.append('=');
        sb.append(((this.myVideoPublished == null)?"<null>":this.myVideoPublished));
        sb.append(',');
        sb.append("myVideoImportFinished");
        sb.append('=');
        sb.append(((this.myVideoImportFinished == null)?"<null>":this.myVideoImportFinished));
        sb.append(',');
        sb.append("commentMention");
        sb.append('=');
        sb.append(((this.commentMention == null)?"<null>":this.commentMention));
        sb.append(',');
        sb.append("newCommentOnMyVideo");
        sb.append('=');
        sb.append(((this.newCommentOnMyVideo == null)?"<null>":this.newCommentOnMyVideo));
        sb.append(',');
        sb.append("newFollow");
        sb.append('=');
        sb.append(((this.newFollow == null)?"<null>":this.newFollow));
        sb.append(',');
        sb.append("newInstanceFollower");
        sb.append('=');
        sb.append(((this.newInstanceFollower == null)?"<null>":this.newInstanceFollower));
        sb.append(',');
        sb.append("autoInstanceFollowing");
        sb.append('=');
        sb.append(((this.autoInstanceFollowing == null)?"<null>":this.autoInstanceFollowing));
        sb.append(',');
        sb.append("abuseStateChange");
        sb.append('=');
        sb.append(((this.abuseStateChange == null)?"<null>":this.abuseStateChange));
        sb.append(',');
        sb.append("abuseNewMessage");
        sb.append('=');
        sb.append(((this.abuseNewMessage == null)?"<null>":this.abuseNewMessage));
        sb.append(',');
        sb.append("newPeerTubeVersion");
        sb.append('=');
        sb.append(((this.newPeerTubeVersion == null)?"<null>":this.newPeerTubeVersion));
        sb.append(',');
        sb.append("newPluginVersion");
        sb.append('=');
        sb.append(((this.newPluginVersion == null)?"<null>":this.newPluginVersion));
        sb.append(',');
        sb.append("myVideoStudioEditionFinished");
        sb.append('=');
        sb.append(((this.myVideoStudioEditionFinished == null)?"<null>":this.myVideoStudioEditionFinished));
        sb.append(',');
        sb.append("myVideoTranscriptionGenerated");
        sb.append('=');
        sb.append(((this.myVideoTranscriptionGenerated == null)?"<null>":this.myVideoTranscriptionGenerated));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
