package com.developer.smmmousavi.clinic.network.bodies;

public class PostQuestionBody {

    private final long userId;
    private final long questionId;
    private final boolean userAnswer;

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

    @Override
    public String toString() {
        return "PostQuestionBody{" +
            "userId=" + userId +
            ", questionId=" + questionId +
            ", userAnswer=" + userAnswer +
            '}';
    }
}
