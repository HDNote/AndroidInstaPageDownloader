package hadi.greencode.instapagedownloader.network.models;

import com.google.gson.annotations.SerializedName;

public class UpdatePackage {

    @SerializedName("update_id")
    private int updateId;

    @SerializedName("update_version_code")
    private int updateVersionCode;

    @SerializedName("update_version_name")
    private String updateVersionName;

    @SerializedName("update_version_link")
    private String updateVersionLink;

    @SerializedName("update_is_force")
    private int updateIsForce;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    public int getUpdateId() {
        return updateId;
    }

    public void setUpdateId(int updateId) {
        this.updateId = updateId;
    }

    public int getUpdateVersionCode() {
        return updateVersionCode;
    }

    public void setUpdateVersionCode(int updateVersionCode) {
        this.updateVersionCode = updateVersionCode;
    }

    public String getUpdateVersionName() {
        return updateVersionName;
    }

    public void setUpdateVersionName(String updateVersionName) {
        this.updateVersionName = updateVersionName;
    }

    public String getUpdateVersionLink() {
        return updateVersionLink;
    }

    public void setUpdateVersionLink(String updateVersionLink) {
        this.updateVersionLink = updateVersionLink;
    }

    public int getUpdateIsForce() {
        return updateIsForce;
    }

    public void setUpdateIsForce(int updateIsForce) {
        this.updateIsForce = updateIsForce;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
