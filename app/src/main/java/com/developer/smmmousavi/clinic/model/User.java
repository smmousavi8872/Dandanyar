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

    @SerializedName("password")
    @Expose
    private String mPassword;

    @SerializedName("userQuestions")
    @Expose
    private List<Question> mUserQuestions;

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
