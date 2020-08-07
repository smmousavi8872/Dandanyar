package com.developer.smmmousavi.clinic.network.bodies;

public class UserSignInBody {

    private final String username;
    private final String password;


    public UserSignInBody(String username, String password) {
        this.username = username;
        this.password = password;
    }


}
