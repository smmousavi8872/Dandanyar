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
    private final long mId;

    @SerializedName("title")
    @ColumnInfo(name = "title")
    private final String mTitle;

    @SerializedName("orderCategory")
    @ColumnInfo(name = "category_order")
    private int mOrder;

    public Category(long id, String title, int order) {
        mId = id;
        mTitle = title;
        mOrder = order;
    }

    public long getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public int getOrder() {
        return mOrder;
    }

    public void setOrder(int order) {
        mOrder = order;
    }

    @Override
    public String toString() {
        return "Category{" +
            "mId=" + mId +
            ", mTitle='" + mTitle + '\'' +
            ", mOrder=" + mOrder +
            '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return mId == category.mId &&
            mOrder == category.mOrder &&
            Objects.equals(mTitle, category.mTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mId, mTitle, mOrder);
    }
}
