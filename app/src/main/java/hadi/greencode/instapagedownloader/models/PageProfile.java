package hadi.greencode.instapagedownloader.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PageProfile {

   @SerializedName("code")
   private Integer code;
   @SerializedName("status")
   private String         status;
   @SerializedName("profile")
   private Profile     profile;
   @SerializedName("posts")
   private List<Post> posts;

   public Integer getCode() {
      return code;
   }

   public void setCode(Integer code) {
      this.code = code;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public Profile getProfile() {
      return profile;
   }

   public void setProfile(Profile profile) {
      this.profile = profile;
   }

   public List<Post> getPosts() {
      return posts;
   }

   public void setPosts(List<Post> posts) {
      this.posts = posts;
   }
}
