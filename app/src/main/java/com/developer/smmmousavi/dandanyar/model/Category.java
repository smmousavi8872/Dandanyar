package com.developer.smmmousavi.dandanyar.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories")
public class Category extends BaseModel {

    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    @NonNull
    private long mId;


    @SerializedName("title")
    @ColumnInfo(name = "title")
    private String mTitle;

    public Category(long id, String title) {
        mId = id;
        mTitle = title;
    }

    public long getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return mId == category.mId &&
            Objects.equals(mTitle, category.mTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mId, mTitle);
    }
}
