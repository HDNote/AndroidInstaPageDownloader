package hadi.greencode.instapagedownloader.models;

import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("code")
    private int code;

    @SerializedName("status")
    private String status;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
