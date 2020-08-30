package com.developer.smmmousavi.clinic.network.bodies;

public class PostQuestionBody {

    private long userId;
    private long questionId;
    private boolean userAnswer;


    public PostQuestionBody(long userId, long questionId, boolean userAnswer) {
        this.userId = userId;
        this.questionId = questionId;
        this.userAnswer = userAnswer;
    }

    public long getUserId() {
        return userId;
    }

    public long getQuestionId() {
        return questionId;
    }

    public boolean getUserAnswer() {
        return userAnswer;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public void setUserAnswer(boolean userAnswer) {
        this.userAnswer = userAnswer;
    }

    @Override
    public String toString() {
        return "PostQuestionBody{" +
            "userId=" + userId +
            ", questionId=" + questionId +
            ", userAnswer=" + userAnswer +
            '}';
    }
}
