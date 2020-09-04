package com.developer.smmmousavi.clinic.repository;

import android.content.Context;
import android.util.Log;

import com.developer.smmmousavi.clinic.model.Category;
import com.developer.smmmousavi.clinic.model.Question;
import com.developer.smmmousavi.clinic.network.AppExecutors;
import com.developer.smmmousavi.clinic.network.bodies.PostQuestionBody;
import com.developer.smmmousavi.clinic.network.factory.SurvayRestApiFactory;
import com.developer.smmmousavi.clinic.network.responses.ApiResponse;
import com.developer.smmmousavi.clinic.network.responses.FirstQuestionResponse;
import com.developer.smmmousavi.clinic.network.responses.PostQuestionResponse;
import com.developer.smmmousavi.clinic.network.util.NetworkBoundResource;
import com.developer.smmmousavi.clinic.network.util.Resource;
import com.developer.smmmousavi.clinic.presistence.dao.CategoryDAO;
import com.developer.smmmousavi.clinic.presistence.dao.QuestionDAO;
import com.developer.smmmousavi.clinic.presistence.db.Database;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

public class QuestionRepository {

    private static final String TAG = "TAG";
    private static QuestionRepository sInstance;

    private QuestionDAO mQuestionDAO;
    private CategoryDAO mCategoryDAO;


    public static QuestionRepository getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new QuestionRepository(context);
            return sInstance;
        }
        return sInstance;
    }

    private QuestionRepository(Context context) {
        mQuestionDAO = Database.getInstance(context).getQuestionDao();

        mCategoryDAO = Database.getInstance(context).geCategoryDao();
    }

    public LiveData<Resource<Question>> getFirstCategoryQuestion(long categoryId) {
        return new NetworkBoundResource<Question, FirstQuestionResponse>(AppExecutors.getInstance()) {

            @Override
            protected void saveCallResult(@NonNull FirstQuestionResponse item) {
                if (item.getQuestion() != null) {
                    Question[] questionArr = new Question[1];
                    questionArr[0] = item.getQuestion();
                    int index = 0;
                    for (long rowId : mQuestionDAO.insertQuestions(questionArr)) {
                        // if category already exists.
                        if (rowId == -1) {
                            Log.d(TAG, "saveCallResult: CONFLICT... This recipe is already in the cache");
                            mQuestionDAO.updateQuestion(
                                questionArr[index].getId(),
                                questionArr[index].getCategoryId(),
                                questionArr[index].getText(),
                                questionArr[index].getResTrueId(),
                                questionArr[index].getResFlaseId(),
                                questionArr[index].isFirst(),
                                questionArr[index].isLast(),
                                questionArr[index].getStatus()
                            );
                        }
                        index++;
                    }
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable Question data) {
                // set the interval of request.
                return true;
            }

            @NonNull
            @Override
            protected LiveData<Question> loadFromDb() {
//                Log.d(TAG, "loadFromDb: categoryId = " + categoryId);
                return mQuestionDAO.getFrirstCategoryQuestion(categoryId);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<FirstQuestionResponse>> createCall() {
//                Log.d(TAG, "createCall: categoryId = " + categoryId);
                return SurvayRestApiFactory.create()
                    .getFristQuestion(categoryId);
            }
        }.getAsLiveData();
    }

    private long mQId = -1;
    private boolean mCallSaved = false;

    public LiveData<Resource<Question>> postUserQuestion(PostQuestionBody body) {
        return new NetworkBoundResource<Question, PostQuestionResponse>(AppExecutors.getInstance()) {
            @Override
            protected void saveCallResult(@NonNull PostQuestionResponse item) {
                Log.d(TAG, "postUserQuestion: saveCallResult");
                if (item.getNextQuestion() != null) {
                    mQuestionDAO.deleteAll();
                    mCallSaved = true;
                    mQId = item.getNextQuestion().getId();
                    Question[] questionArr = new Question[1];
                    questionArr[0] = item.getNextQuestion();
                    int index = 0;
                    for (long rowId : mQuestionDAO.insertQuestions(questionArr)) {
                        Log.d(TAG, "postUserQuestion: rowId -> " + rowId);
                        // if category already exists.
                        if (rowId == -1) {
                            mQuestionDAO.updateQuestion(
                                questionArr[index].getId(),
                                questionArr[index].getCategoryId(),
                                questionArr[index].getText(),
                                questionArr[index].getResTrueId(),
                                questionArr[index].getResFlaseId(),
                                questionArr[index].isFirst(),
                                questionArr[index].isLast(),
                                questionArr[index].getStatus()
                            );
                        }
                        index++;
                    }
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable Question data) {
                Log.d(TAG, "postUserQuestion: shouldFetch");
                // set the interval of request.
                return true;
            }

            @NonNull
            @Override
            protected LiveData<Question> loadFromDb() {
                Log.d(TAG, "postUserQuestion: loadFromDb");
                Log.d(TAG, "loadFromDb: questionId = " + mQId);
                return mQuestionDAO.getQuestionById(mQId);
            }

            private LiveData<Question> getNextQuestionOffline(PostQuestionBody body) {
                Question prevQ = mQuestionDAO.getQuestionById(body.getQuestionId()).getValue();
                long nextQuestionId;
                if (body.getUserAnswer()) {
                    nextQuestionId = Long.parseLong(prevQ.getResTrueId());
                } else {
                    nextQuestionId = Long.parseLong(prevQ.getResFlaseId());
                }
                return mQuestionDAO.getQuestionById(nextQuestionId);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<PostQuestionResponse>> createCall() {
                Log.d(TAG, "postUserQuestion: createCall");
                return SurvayRestApiFactory.create()
                    .postUserQuestion(body.getUserId(), body.getQuestionId(), body.getUserAnswer());
            }
        }.getAsLiveData();
    }

    public LiveData<Category> getCategoryById(long categoryId) {
        return mCategoryDAO.getCategoryById(categoryId);
    }

}
