package com.developer.smmmousavi.dandanyar.network.responses;

import com.developer.smmmousavi.dandanyar.model.Question;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FirstQuestionResponse {
    @SerializedName("status")
    @Expose
    private String mStatus;

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
