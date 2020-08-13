package com.developer.smmmousavi.clinic.network.bodies;

import com.google.gson.annotations.SerializedName;

public class UserSignInBody {

    @SerializedName("username")
    private final String username;
    @SerializedName("password")
    private final String password;


    public UserSignInBody(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
