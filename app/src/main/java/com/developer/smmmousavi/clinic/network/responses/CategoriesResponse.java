package com.developer.smmmousavi.clinic.network.responses;

import com.developer.smmmousavi.clinic.model.Category;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoriesResponse {
    @SerializedName("status")
    @Expose
    private int mStatus;

    @SerializedName("data")
    @Expose
    private List<Category> mCategories;


    public int getStatus() {
        return mStatus;
    }


    public List<Category> getCategories() {
        return mCategories;
    }


    @Override
    public String toString() {
        return "CategoriesResponse{" +
            "mStatus=" + mStatus +
            ", mUser=" + mCategories +
            '}';
    }
}
