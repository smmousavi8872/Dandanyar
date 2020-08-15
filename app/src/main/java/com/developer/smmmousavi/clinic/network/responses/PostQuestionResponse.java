package com.developer.smmmousavi.clinic.network.responses;

import com.developer.smmmousavi.clinic.model.Question;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostQuestionResponse {

    @SerializedName("status")
    @Expose
    private String mStatus;

    @SerializedName("data")
    @Expose
    private Question mNextQuestion;

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public Question getNextQuestion() {
        return mNextQuestion;
    }

    public void setNextQuestion(Question nextQuestion) {
        mNextQuestion = nextQuestion;
    }
}
