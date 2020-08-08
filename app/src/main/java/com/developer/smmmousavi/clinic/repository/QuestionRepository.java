package com.developer.smmmousavi.clinic.repository;

import android.content.Context;
import android.util.Log;

import com.developer.smmmousavi.clinic.model.Question;
import com.developer.smmmousavi.clinic.network.AppExecutors;
import com.developer.smmmousavi.clinic.network.factory.SurvayRestApiFactory;
import com.developer.smmmousavi.clinic.network.responses.ApiResponse;
import com.developer.smmmousavi.clinic.network.responses.FirstCategoryQuestionResponse;
import com.developer.smmmousavi.clinic.network.util.NetworkBoundResource;
import com.developer.smmmousavi.clinic.network.util.Resource;
import com.developer.smmmousavi.clinic.presistence.dao.QuestionDAO;
import com.developer.smmmousavi.clinic.presistence.db.Database;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

public class QuestionRepository {

    private static final String TAG = "TAG";
    private static QuestionRepository sInstance;

    private QuestionDAO mQuestionDAO;


    public static QuestionRepository getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new QuestionRepository(context);
            return sInstance;
        }
        return sInstance;
    }

    private QuestionRepository(Context context) {
        mQuestionDAO = Database.getInstance(context).getQuestionDao();
    }

    public LiveData<Resource<Question>> getFirstCategoryQuestion(long categoryId) {
        return new NetworkBoundResource<Question, FirstCategoryQuestionResponse>(AppExecutors.getInstance()) {

            @Override
            protected void saveCallResult(@NonNull FirstCategoryQuestionResponse item) {
                if (item.getQuestion() != null) {
                    Question[] questionArr = new Question[1];
                    questionArr[0] =item.getQuestion();
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
                                questionArr[index].isLast()
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
                Log.d(TAG, "loadFromDb: categoryId = " + categoryId);
                return mQuestionDAO.getFrirstCategoryQuestion(categoryId);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<FirstCategoryQuestionResponse>> createCall() {
                Log.d(TAG, "createCall: categoryId = " + categoryId);
                return SurvayRestApiFactory.create()
                    .getFristCategoryQuestion(categoryId);
            }
        }.getAsLiveData();

    }

}
