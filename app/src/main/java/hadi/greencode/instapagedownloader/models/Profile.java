package hadi.greencode.instapagedownloader.models;

import com.google.gson.annotations.SerializedName;

public class Profile {
    @SerializedName("profile_pic_url")
    private String  profilePicUrl;
    @SerializedName("followers")
    private Integer followers;
    @SerializedName("full_name")
    private String  fullName;
    @SerializedName("followees")
    private Integer followees;
    @SerializedName("post_count")
    private Integer postCount;
    @SerializedName("is_private")
    private Boolean isPrivate;
    @SerializedName("biography")
    private String  biography;
    @SerializedName("followed_by_viewer")
    private Boolean  followedByViewer;

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getFollowees() {
        return followees;
    }

    public void setFollowees(Integer followees) {
        this.followees = followees;
    }

    public Integer getPostCount() {
        return postCount;
    }

    public void setPostCount(Integer postCount) {
        this.postCount = postCount;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Boolean getFollowedByViewer() {
        return followedByViewer;
    }

    public void setFollowedByViewer(Boolean followedByViewer) {
        this.followedByViewer = followedByViewer;
    }
}
