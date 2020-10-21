package com.developer.smmmousavi.dandanyar.network.responses;

import com.developer.smmmousavi.dandanyar.model.Category;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryByIdResponse {

    @SerializedName("status")
    @Expose
    private String mStatus;

    @SerializedName("data")
    @Expose
    private Category mCategory;


    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public Category getCategory() {
        return mCategory;
    }

    public void setCategory(Category category) {
        mCategory = category;
    }
}
