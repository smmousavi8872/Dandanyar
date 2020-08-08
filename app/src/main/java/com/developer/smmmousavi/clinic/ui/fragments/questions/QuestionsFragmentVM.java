package com.developer.smmmousavi.clinic.ui.fragments.questions;


import android.app.Application;

import com.developer.smmmousavi.clinic.base.viewmodel.BaseViewModel;
import com.developer.smmmousavi.clinic.model.Question;
import com.developer.smmmousavi.clinic.network.util.Resource;
import com.developer.smmmousavi.clinic.repository.QuestionRepository;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

public class QuestionsFragmentVM extends BaseViewModel {

    private boolean mIsPerformingQuery;
    private boolean mCancelRequest;
    private QuestionRepository mRepository;
    private MediatorLiveData<Resource<Question>> mQuestionMLD;


    public boolean isPerformingQuery() {
        return mIsPerformingQuery;
    }

    public boolean isCancelRequest() {
        return mCancelRequest;
    }

    public MediatorLiveData<Resource<Question>> getQuestionMLD() {
        return mQuestionMLD;
    }

    @Inject
    public QuestionsFragmentVM(@NonNull Application application) {
        super(application);
        mRepository = QuestionRepository.getInstance(application);
        mQuestionMLD = new MediatorLiveData<>();
    }


    public void executeGetFirstQuestion(long categoryId) {
        mIsPerformingQuery = true;
        mCancelRequest = false;
        final LiveData<Resource<Question>> repoSource = mRepository.getFirstCategoryQuestion(categoryId);
        mQuestionMLD.addSource(repoSource, listResource -> {
            //onChange
            if (listResource != null) {
                mQuestionMLD.setValue(listResource);
                if (listResource.status == Resource.Status.SUCCESS) {
                    mIsPerformingQuery = false;
                    mQuestionMLD.removeSource(repoSource);
                } else if (listResource.status == Resource.Status.ERROR) {
                    mIsPerformingQuery = false;
                    mQuestionMLD.removeSource(repoSource);
                }
            } else {
                mQuestionMLD.removeSource(repoSource);
            }
        });
    }

}
