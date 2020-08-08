package com.developer.smmmousavi.clinic.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class User extends BaseModel {

    @SerializedName("id")
    @Expose
    private long mId;

    @SerializedName("firstName")
    @Expose
    private String mFirstName;

    @SerializedName("lastName")
    @Expose
    private String mLastName;

    @SerializedName("userName")
    @Expose
    private String mUserName;

    @SerializedName("password")
    @Expose
    private String mPassword;

    @SerializedName("userQuestions")
    @Expose
    private List<Question> mUserQuestions;


    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public List<Question> getUserQuestions() {
        return mUserQuestions;
    }

    public void setUserQuestions(List<Question> userQuestions) {
        mUserQuestions = userQuestions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return mId == user.mId &&
            Objects.equals(mFirstName, user.mFirstName) &&
            Objects.equals(mLastName, user.mLastName) &&
            Objects.equals(mPassword, user.mPassword) &&
            Objects.equals(mUserQuestions, user.mUserQuestions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mId, mFirstName, mLastName, mPassword, mUserQuestions);
    }
}
