package com.developer.smmmousavi.clinic.network.responses;

import com.developer.smmmousavi.clinic.model.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserSignUpResponse {

    @SerializedName("status")
    @Expose
    private int mStatus;

    @SerializedName("data")
    @Expose
    private User mUser;
}
