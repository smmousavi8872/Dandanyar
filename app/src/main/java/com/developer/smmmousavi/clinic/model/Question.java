package com.developer.smmmousavi.clinic.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "questions")
public class Question extends BaseModel {

    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    @NonNull
    private String mId;

    @SerializedName("text")
    @ColumnInfo(name = "text")
    private String mText;

    @SerializedName("res_True_Id")
    @ColumnInfo(name = "res_True_Id")
    private String mResTrueId;

    @SerializedName("res_False_Id")
    @ColumnInfo(name = "res_False_Id")
    private String mResFlaseId;

    @SerializedName("categoryId")
    @ColumnInfo(name = "categoryId")
    private String mCategoryId;

    @SerializedName("isFirst")
    @ColumnInfo(name = "isFirst")
    private boolean isFirst;

    @SerializedName("isLast")
    @ColumnInfo(name = "isLast")
    private boolean isLast;


    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public String getResTrueId() {
        return mResTrueId;
    }

    public void setResTrueId(String resTrueId) {
        mResTrueId = resTrueId;
    }

    public String getResFlaseId() {
        return mResFlaseId;
    }

    public void setResFlaseId(String resFlaseId) {
        mResFlaseId = resFlaseId;
    }

    public String getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(String categoryId) {
        mCategoryId = categoryId;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }


    @Override
    public String toString() {
        return "Question{" +
            "mId='" + mId + '\'' +
            ", mText='" + mText + '\'' +
            ", mResTrueId='" + mResTrueId + '\'' +
            ", mResFlaseId='" + mResFlaseId + '\'' +
            ", mCategoryId='" + mCategoryId + '\'' +
            ", isFirst=" + isFirst +
            ", isLast=" + isLast +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return isFirst == question.isFirst &&
            isLast == question.isLast &&
            mId.equals(question.mId) &&
            Objects.equals(mText, question.mText) &&
            Objects.equals(mResTrueId, question.mResTrueId) &&
            Objects.equals(mResFlaseId, question.mResFlaseId) &&
            Objects.equals(mCategoryId, question.mCategoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mId, mText, mResTrueId, mResFlaseId, mCategoryId, isFirst, isLast);
    }
}
