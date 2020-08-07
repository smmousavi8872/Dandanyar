package com.developer.smmmousavi.clinic.network.bodies;

public class PostUserQuestionBody {

    private final String userId;
    private final String questionId;
    private final String userAnswer;


    public PostUserQuestionBody(String userId, String questionId, String userAnswer) {
        this.userId = userId;
        this.questionId = questionId;
        this.userAnswer = userAnswer;
    }

}
