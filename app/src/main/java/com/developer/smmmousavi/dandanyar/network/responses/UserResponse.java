package com.developer.smmmousavi.dandanyar.network.responses;

import com.developer.smmmousavi.dandanyar.model.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("status")
    @Expose
    private String mStatus;

    @SerializedName("data")
    @Expose
    private User mUser;

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }
}
