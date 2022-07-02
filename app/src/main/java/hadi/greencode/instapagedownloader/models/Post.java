package hadi.greencode.instapagedownloader.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Post {
   @SerializedName("image")
   private String  image;
   @SerializedName("comments_count")
   private Integer commentsCount;
   @SerializedName("caption")
   private String  caption;
   @SerializedName("likes")
   private Integer likes;
   @SerializedName("shortcode")
   private String  shortcode;
   @SerializedName("video_view_count")
   private Integer  videoViewCount;
   @SerializedName("video_duration")
   private String  videoDuration;
   @SerializedName("is_video")
   private Boolean isVideo;
   @SerializedName("pcaption")
   private String  pcaption;
   @SerializedName("profile")
   private String  profile;
   @SerializedName("video_url")
   private String  videoUrl;
   @SerializedName("images")
   private List<PostImages>  postImages;

   private boolean isChecked;
   private int downloadId;

   public String getImage() {
      return image;
   }

   public void setImage(String image) {
      this.image = image;
   }

   public Integer getCommentsCount() {
      return commentsCount;
   }

   public void setCommentsCount(Integer commentsCount) {
      this.commentsCount = commentsCount;
   }

   public String getCaption() {
      return caption;
   }

   public void setCaption(String caption) {
      this.caption = caption;
   }

   public Integer getLikes() {
      return likes;
   }

   public void setLikes(Integer likes) {
      this.likes = likes;
   }

   public String getShortcode() {
      return shortcode;
   }

   public void setShortcode(String shortcode) {
      this.shortcode = shortcode;
   }

   public Integer getVideoViewCount() {
      return videoViewCount;
   }

   public void setVideoViewCount(Integer videoViewCount) {
      this.videoViewCount = videoViewCount;
   }

   public String getVideoDuration() {
      return videoDuration;
   }

   public void setVideoDuration(String videoDuration) {
      this.videoDuration = videoDuration;
   }

   public Boolean getVideo() {
      return isVideo;
   }

   public void setVideo(Boolean video) {
      isVideo = video;
   }

   public String getPcaption() {
      return pcaption;
   }

   public void setPcaption(String pcaption) {
      this.pcaption = pcaption;
   }

   public String getProfile() {
      return profile;
   }

   public void setProfile(String profile) {
      this.profile = profile;
   }

   public String getVideoUrl() {
      return videoUrl;
   }

   public void setVideoUrl(String videoUrl) {
      this.videoUrl = videoUrl;
   }

   public boolean isChecked() {
      return isChecked;
   }

   public void setChecked(boolean checked) {
      isChecked = checked;
   }

   public int getDownloadId() {
      return downloadId;
   }

   public void setDownloadId(int downloadId) {
      this.downloadId = downloadId;
   }

   public List<PostImages> getPostImages() {
      return postImages;
   }

   public void setPostImages(List<PostImages> postImages) {
      this.postImages = postImages;
   }
}
