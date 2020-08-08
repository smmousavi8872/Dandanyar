package com.developer.smmmousavi.clinic.network.responses;

import com.developer.smmmousavi.clinic.model.Question;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FirstCategoryQuestionResponse {
    @SerializedName("status")
    @Expose
    private int mStatus;

    @SerializedName("data")
    @Expose
    private Question mQuestion;


    public Question getQuestion() {
        return mQuestion;
    }

    public void setQuestion(Question question) {
        mQuestion = question;
    }
}
