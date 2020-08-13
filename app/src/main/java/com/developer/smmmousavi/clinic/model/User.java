package com.developer.smmmousavi.clinic.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User extends BaseModel {

    @SerializedName("id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    @Expose
    @NonNull
    private long mId;

    @SerializedName("firstName")
    @ColumnInfo(name ="firstName")
    @Expose
    private String mFirstName;

    @SerializedName("lastName")
    @ColumnInfo(name ="lastName")
    @Expose
    private String mLastName;

    @SerializedName("userName")
    @ColumnInfo(name ="userName")
    @Expose
    private String mUserName;

    @SerializedName("password")
    @ColumnInfo(name ="password")
    @Expose
    private String mPassword;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return mId == user.mId &&
            Objects.equals(mFirstName, user.mFirstName) &&
            Objects.equals(mLastName, user.mLastName) &&
            Objects.equals(mUserName, user.mUserName) &&
            Objects.equals(mPassword, user.mPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mId, mFirstName, mLastName, mUserName, mPassword);
    }

    @Override
    public String toString() {
        return "User{" +
            "mId=" + mId +
            ", mFirstName='" + mFirstName + '\'' +
            ", mLastName='" + mLastName + '\'' +
            ", mUserName='" + mUserName + '\'' +
            ", mPassword='" + mPassword + '\'' +
            '}';
    }

}
