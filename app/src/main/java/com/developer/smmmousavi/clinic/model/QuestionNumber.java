package com.developer.smmmousavi.clinic.model;

import java.util.Objects;

public class QuestionNumber extends BaseModel {

    private long mQuestionId;
    private int mQuestionNum;
    private boolean mQuestionAnswer;
    private boolean mFirst;

    public int getQuestionNum() {
        return mQuestionNum;
    }

    public void setQuestionNum(int questionNum) {
        mQuestionNum = questionNum;
    }

    public boolean getQuestionAnswer() {
        return mQuestionAnswer;
    }

    public void setQuestionAnswer(boolean questionAnswer) {
        mQuestionAnswer = questionAnswer;
    }

    public long getQuestionId() {
        return mQuestionId;
    }

    public void setQuestionId(long questionId) {
        mQuestionId = questionId;
    }

    public boolean isFirst() {
        return mFirst;
    }

    public void setFirst(boolean first) {
        mFirst = first;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionNumber that = (QuestionNumber) o;
        return mQuestionId == that.mQuestionId &&
            mQuestionNum == that.mQuestionNum &&
            mFirst == that.mFirst &&
            Objects.equals(mQuestionAnswer, that.mQuestionAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mQuestionId, mQuestionNum, mQuestionAnswer, mFirst);
    }

    @Override
    public String toString() {
        return "QuestionNumber{" +
            "mQuestionId=" + mQuestionId +
            ", mQuestionNum=" + mQuestionNum +
            ", mQuestionAnswer='" + mQuestionAnswer + '\'' +
            ", mIsLast=" + mFirst +
            '}';
    }
}
