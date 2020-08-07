package com.developer.smmmousavi.clinic.model;

public class Survay extends BaseModel {

    private long mId;
    private String mTitle;
    private String mDescription;
    private String mIconUrl;

    public Survay(String title, String description, String iconUrl) {
        mTitle = title;
        mDescription = description;
        mIconUrl = iconUrl;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getIconUrl() {
        return mIconUrl;
    }

    public void setIconUrl(String iconUrl) {
        mIconUrl = iconUrl;
    }
}
