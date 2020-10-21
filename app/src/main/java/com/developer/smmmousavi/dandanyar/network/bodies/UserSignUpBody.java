package com.developer.smmmousavi.dandanyar.network.bodies;

public class UserSignUpBody {

    private final String firstName;
    private final String lastName;
    private final String userName;
    private final String password;

    public UserSignUpBody(String firstName, String lastName, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
