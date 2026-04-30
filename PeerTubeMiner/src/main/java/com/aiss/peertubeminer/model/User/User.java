
package aiss.peertubeminer.model;

import java.util.List;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("email")
    private String email;
    @JsonProperty("emailVerified")
    private Boolean emailVerified;
    @JsonProperty("emailPublic")
    private Boolean emailPublic;
    @JsonProperty("nsfwPolicy")
    private String nsfwPolicy;
    @JsonProperty("nsfwFlagsDisplayed")
    private Integer nsfwFlagsDisplayed;
    @JsonProperty("nsfwFlagsHidden")
    private Integer nsfwFlagsHidden;
    @JsonProperty("nsfwFlagsWarned")
    private Integer nsfwFlagsWarned;
    @JsonProperty("nsfwFlagsBlurred")
    private Integer nsfwFlagsBlurred;
    @JsonProperty("adminFlags")
    private Integer adminFlags;
    @JsonProperty("autoPlayNextVideo")
    private Boolean autoPlayNextVideo;
    @JsonProperty("autoPlayNextVideoPlaylist")
    private Boolean autoPlayNextVideoPlaylist;
    @JsonProperty("autoPlayVideo")
    private Boolean autoPlayVideo;
    @JsonProperty("p2pEnabled")
    private Boolean p2pEnabled;
    @JsonProperty("videosHistoryEnabled")
    private Boolean videosHistoryEnabled;
    @JsonProperty("videoLanguages")
    private List<String> videoLanguages;
    @JsonProperty("language")
    private String language;
    @JsonProperty("videoQuota")
    private Integer videoQuota;
    @JsonProperty("videoQuotaDaily")
    private Integer videoQuotaDaily;
    @JsonProperty("role")
    private Role role;
    @JsonProperty("theme")
    private String theme;
    @JsonProperty("account")
    private Account account;
    @JsonProperty("notificationSettings")
    private NotificationSettings notificationSettings;
    @JsonProperty("videoChannels")
    private List<VideoChannel> videoChannels;
    @JsonProperty("blocked")
    private Boolean blocked;
    @JsonProperty("blockedReason")
    private String blockedReason;
    @JsonProperty("noInstanceConfigWarningModal")
    private Boolean noInstanceConfigWarningModal;
    @JsonProperty("noAccountSetupWarningModal")
    private Boolean noAccountSetupWarningModal;
    @JsonProperty("noWelcomeModal")
    private Boolean noWelcomeModal;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("pluginAuth")
    private String pluginAuth;
    @JsonProperty("lastLoginDate")
    private String lastLoginDate;
    @JsonProperty("twoFactorEnabled")
    private Boolean twoFactorEnabled;
    @JsonProperty("newFeaturesInfoRead")
    private Integer newFeaturesInfoRead;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("emailVerified")
    public Boolean getEmailVerified() {
        return emailVerified;
    }

    @JsonProperty("emailVerified")
    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    @JsonProperty("emailPublic")
    public Boolean getEmailPublic() {
        return emailPublic;
    }

    @JsonProperty("emailPublic")
    public void setEmailPublic(Boolean emailPublic) {
        this.emailPublic = emailPublic;
    }

    @JsonProperty("nsfwPolicy")
    public String getNsfwPolicy() {
        return nsfwPolicy;
    }

    @JsonProperty("nsfwPolicy")
    public void setNsfwPolicy(String nsfwPolicy) {
        this.nsfwPolicy = nsfwPolicy;
    }

    @JsonProperty("nsfwFlagsDisplayed")
    public Integer getNsfwFlagsDisplayed() {
        return nsfwFlagsDisplayed;
    }

    @JsonProperty("nsfwFlagsDisplayed")
    public void setNsfwFlagsDisplayed(Integer nsfwFlagsDisplayed) {
        this.nsfwFlagsDisplayed = nsfwFlagsDisplayed;
    }

    @JsonProperty("nsfwFlagsHidden")
    public Integer getNsfwFlagsHidden() {
        return nsfwFlagsHidden;
    }

    @JsonProperty("nsfwFlagsHidden")
    public void setNsfwFlagsHidden(Integer nsfwFlagsHidden) {
        this.nsfwFlagsHidden = nsfwFlagsHidden;
    }

    @JsonProperty("nsfwFlagsWarned")
    public Integer getNsfwFlagsWarned() {
        return nsfwFlagsWarned;
    }

    @JsonProperty("nsfwFlagsWarned")
    public void setNsfwFlagsWarned(Integer nsfwFlagsWarned) {
        this.nsfwFlagsWarned = nsfwFlagsWarned;
    }

    @JsonProperty("nsfwFlagsBlurred")
    public Integer getNsfwFlagsBlurred() {
        return nsfwFlagsBlurred;
    }

    @JsonProperty("nsfwFlagsBlurred")
    public void setNsfwFlagsBlurred(Integer nsfwFlagsBlurred) {
        this.nsfwFlagsBlurred = nsfwFlagsBlurred;
    }

    @JsonProperty("adminFlags")
    public Integer getAdminFlags() {
        return adminFlags;
    }

    @JsonProperty("adminFlags")
    public void setAdminFlags(Integer adminFlags) {
        this.adminFlags = adminFlags;
    }

    @JsonProperty("autoPlayNextVideo")
    public Boolean getAutoPlayNextVideo() {
        return autoPlayNextVideo;
    }

    @JsonProperty("autoPlayNextVideo")
    public void setAutoPlayNextVideo(Boolean autoPlayNextVideo) {
        this.autoPlayNextVideo = autoPlayNextVideo;
    }

    @JsonProperty("autoPlayNextVideoPlaylist")
    public Boolean getAutoPlayNextVideoPlaylist() {
        return autoPlayNextVideoPlaylist;
    }

    @JsonProperty("autoPlayNextVideoPlaylist")
    public void setAutoPlayNextVideoPlaylist(Boolean autoPlayNextVideoPlaylist) {
        this.autoPlayNextVideoPlaylist = autoPlayNextVideoPlaylist;
    }

    @JsonProperty("autoPlayVideo")
    public Boolean getAutoPlayVideo() {
        return autoPlayVideo;
    }

    @JsonProperty("autoPlayVideo")
    public void setAutoPlayVideo(Boolean autoPlayVideo) {
        this.autoPlayVideo = autoPlayVideo;
    }

    @JsonProperty("p2pEnabled")
    public Boolean getP2pEnabled() {
        return p2pEnabled;
    }

    @JsonProperty("p2pEnabled")
    public void setP2pEnabled(Boolean p2pEnabled) {
        this.p2pEnabled = p2pEnabled;
    }

    @JsonProperty("videosHistoryEnabled")
    public Boolean getVideosHistoryEnabled() {
        return videosHistoryEnabled;
    }

    @JsonProperty("videosHistoryEnabled")
    public void setVideosHistoryEnabled(Boolean videosHistoryEnabled) {
        this.videosHistoryEnabled = videosHistoryEnabled;
    }

    @JsonProperty("videoLanguages")
    public List<String> getVideoLanguages() {
        return videoLanguages;
    }

    @JsonProperty("videoLanguages")
    public void setVideoLanguages(List<String> videoLanguages) {
        this.videoLanguages = videoLanguages;
    }

    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    @JsonProperty("videoQuota")
    public Integer getVideoQuota() {
        return videoQuota;
    }

    @JsonProperty("videoQuota")
    public void setVideoQuota(Integer videoQuota) {
        this.videoQuota = videoQuota;
    }

    @JsonProperty("videoQuotaDaily")
    public Integer getVideoQuotaDaily() {
        return videoQuotaDaily;
    }

    @JsonProperty("videoQuotaDaily")
    public void setVideoQuotaDaily(Integer videoQuotaDaily) {
        this.videoQuotaDaily = videoQuotaDaily;
    }

    @JsonProperty("role")
    public Role getRole() {
        return role;
    }

    @JsonProperty("role")
    public void setRole(Role role) {
        this.role = role;
    }

    @JsonProperty("theme")
    public String getTheme() {
        return theme;
    }

    @JsonProperty("theme")
    public void setTheme(String theme) {
        this.theme = theme;
    }

    @JsonProperty("account")
    public Account getAccount() {
        return account;
    }

    @JsonProperty("account")
    public void setAccount(Account account) {
        this.account = account;
    }

    @JsonProperty("notificationSettings")
    public NotificationSettings getNotificationSettings() {
        return notificationSettings;
    }

    @JsonProperty("notificationSettings")
    public void setNotificationSettings(NotificationSettings notificationSettings) {
        this.notificationSettings = notificationSettings;
    }

    @JsonProperty("videoChannels")
    public List<VideoChannel> getVideoChannels() {
        return videoChannels;
    }

    @JsonProperty("videoChannels")
    public void setVideoChannels(List<VideoChannel> videoChannels) {
        this.videoChannels = videoChannels;
    }

    @JsonProperty("blocked")
    public Boolean getBlocked() {
        return blocked;
    }

    @JsonProperty("blocked")
    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    @JsonProperty("blockedReason")
    public String getBlockedReason() {
        return blockedReason;
    }

    @JsonProperty("blockedReason")
    public void setBlockedReason(String blockedReason) {
        this.blockedReason = blockedReason;
    }

    @JsonProperty("noInstanceConfigWarningModal")
    public Boolean getNoInstanceConfigWarningModal() {
        return noInstanceConfigWarningModal;
    }

    @JsonProperty("noInstanceConfigWarningModal")
    public void setNoInstanceConfigWarningModal(Boolean noInstanceConfigWarningModal) {
        this.noInstanceConfigWarningModal = noInstanceConfigWarningModal;
    }

    @JsonProperty("noAccountSetupWarningModal")
    public Boolean getNoAccountSetupWarningModal() {
        return noAccountSetupWarningModal;
    }

    @JsonProperty("noAccountSetupWarningModal")
    public void setNoAccountSetupWarningModal(Boolean noAccountSetupWarningModal) {
        this.noAccountSetupWarningModal = noAccountSetupWarningModal;
    }

    @JsonProperty("noWelcomeModal")
    public Boolean getNoWelcomeModal() {
        return noWelcomeModal;
    }

    @JsonProperty("noWelcomeModal")
    public void setNoWelcomeModal(Boolean noWelcomeModal) {
        this.noWelcomeModal = noWelcomeModal;
    }

    @JsonProperty("createdAt")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("createdAt")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("pluginAuth")
    public String getPluginAuth() {
        return pluginAuth;
    }

    @JsonProperty("pluginAuth")
    public void setPluginAuth(String pluginAuth) {
        this.pluginAuth = pluginAuth;
    }

    @JsonProperty("lastLoginDate")
    public String getLastLoginDate() {
        return lastLoginDate;
    }

    @JsonProperty("lastLoginDate")
    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    @JsonProperty("twoFactorEnabled")
    public Boolean getTwoFactorEnabled() {
        return twoFactorEnabled;
    }

    @JsonProperty("twoFactorEnabled")
    public void setTwoFactorEnabled(Boolean twoFactorEnabled) {
        this.twoFactorEnabled = twoFactorEnabled;
    }

    @JsonProperty("newFeaturesInfoRead")
    public Integer getNewFeaturesInfoRead() {
        return newFeaturesInfoRead;
    }

    @JsonProperty("newFeaturesInfoRead")
    public void setNewFeaturesInfoRead(Integer newFeaturesInfoRead) {
        this.newFeaturesInfoRead = newFeaturesInfoRead;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(User.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("username");
        sb.append('=');
        sb.append(((this.username == null)?"<null>":this.username));
        sb.append(',');
        sb.append("email");
        sb.append('=');
        sb.append(((this.email == null)?"<null>":this.email));
        sb.append(',');
        sb.append("emailVerified");
        sb.append('=');
        sb.append(((this.emailVerified == null)?"<null>":this.emailVerified));
        sb.append(',');
        sb.append("emailPublic");
        sb.append('=');
        sb.append(((this.emailPublic == null)?"<null>":this.emailPublic));
        sb.append(',');
        sb.append("nsfwPolicy");
        sb.append('=');
        sb.append(((this.nsfwPolicy == null)?"<null>":this.nsfwPolicy));
        sb.append(',');
        sb.append("nsfwFlagsDisplayed");
        sb.append('=');
        sb.append(((this.nsfwFlagsDisplayed == null)?"<null>":this.nsfwFlagsDisplayed));
        sb.append(',');
        sb.append("nsfwFlagsHidden");
        sb.append('=');
        sb.append(((this.nsfwFlagsHidden == null)?"<null>":this.nsfwFlagsHidden));
        sb.append(',');
        sb.append("nsfwFlagsWarned");
        sb.append('=');
        sb.append(((this.nsfwFlagsWarned == null)?"<null>":this.nsfwFlagsWarned));
        sb.append(',');
        sb.append("nsfwFlagsBlurred");
        sb.append('=');
        sb.append(((this.nsfwFlagsBlurred == null)?"<null>":this.nsfwFlagsBlurred));
        sb.append(',');
        sb.append("adminFlags");
        sb.append('=');
        sb.append(((this.adminFlags == null)?"<null>":this.adminFlags));
        sb.append(',');
        sb.append("autoPlayNextVideo");
        sb.append('=');
        sb.append(((this.autoPlayNextVideo == null)?"<null>":this.autoPlayNextVideo));
        sb.append(',');
        sb.append("autoPlayNextVideoPlaylist");
        sb.append('=');
        sb.append(((this.autoPlayNextVideoPlaylist == null)?"<null>":this.autoPlayNextVideoPlaylist));
        sb.append(',');
        sb.append("autoPlayVideo");
        sb.append('=');
        sb.append(((this.autoPlayVideo == null)?"<null>":this.autoPlayVideo));
        sb.append(',');
        sb.append("p2pEnabled");
        sb.append('=');
        sb.append(((this.p2pEnabled == null)?"<null>":this.p2pEnabled));
        sb.append(',');
        sb.append("videosHistoryEnabled");
        sb.append('=');
        sb.append(((this.videosHistoryEnabled == null)?"<null>":this.videosHistoryEnabled));
        sb.append(',');
        sb.append("videoLanguages");
        sb.append('=');
        sb.append(((this.videoLanguages == null)?"<null>":this.videoLanguages));
        sb.append(',');
        sb.append("language");
        sb.append('=');
        sb.append(((this.language == null)?"<null>":this.language));
        sb.append(',');
        sb.append("videoQuota");
        sb.append('=');
        sb.append(((this.videoQuota == null)?"<null>":this.videoQuota));
        sb.append(',');
        sb.append("videoQuotaDaily");
        sb.append('=');
        sb.append(((this.videoQuotaDaily == null)?"<null>":this.videoQuotaDaily));
        sb.append(',');
        sb.append("role");
        sb.append('=');
        sb.append(((this.role == null)?"<null>":this.role));
        sb.append(',');
        sb.append("theme");
        sb.append('=');
        sb.append(((this.theme == null)?"<null>":this.theme));
        sb.append(',');
        sb.append("account");
        sb.append('=');
        sb.append(((this.account == null)?"<null>":this.account));
        sb.append(',');
        sb.append("notificationSettings");
        sb.append('=');
        sb.append(((this.notificationSettings == null)?"<null>":this.notificationSettings));
        sb.append(',');
        sb.append("videoChannels");
        sb.append('=');
        sb.append(((this.videoChannels == null)?"<null>":this.videoChannels));
        sb.append(',');
        sb.append("blocked");
        sb.append('=');
        sb.append(((this.blocked == null)?"<null>":this.blocked));
        sb.append(',');
        sb.append("blockedReason");
        sb.append('=');
        sb.append(((this.blockedReason == null)?"<null>":this.blockedReason));
        sb.append(',');
        sb.append("noInstanceConfigWarningModal");
        sb.append('=');
        sb.append(((this.noInstanceConfigWarningModal == null)?"<null>":this.noInstanceConfigWarningModal));
        sb.append(',');
        sb.append("noAccountSetupWarningModal");
        sb.append('=');
        sb.append(((this.noAccountSetupWarningModal == null)?"<null>":this.noAccountSetupWarningModal));
        sb.append(',');
        sb.append("noWelcomeModal");
        sb.append('=');
        sb.append(((this.noWelcomeModal == null)?"<null>":this.noWelcomeModal));
        sb.append(',');
        sb.append("createdAt");
        sb.append('=');
        sb.append(((this.createdAt == null)?"<null>":this.createdAt));
        sb.append(',');
        sb.append("pluginAuth");
        sb.append('=');
        sb.append(((this.pluginAuth == null)?"<null>":this.pluginAuth));
        sb.append(',');
        sb.append("lastLoginDate");
        sb.append('=');
        sb.append(((this.lastLoginDate == null)?"<null>":this.lastLoginDate));
        sb.append(',');
        sb.append("twoFactorEnabled");
        sb.append('=');
        sb.append(((this.twoFactorEnabled == null)?"<null>":this.twoFactorEnabled));
        sb.append(',');
        sb.append("newFeaturesInfoRead");
        sb.append('=');
        sb.append(((this.newFeaturesInfoRead == null)?"<null>":this.newFeaturesInfoRead));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
