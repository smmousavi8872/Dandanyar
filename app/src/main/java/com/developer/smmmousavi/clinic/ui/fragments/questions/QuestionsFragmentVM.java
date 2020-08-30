package com.developer.smmmousavi.clinic.ui.fragments.questions;


import android.app.Application;

import com.developer.smmmousavi.clinic.base.viewmodel.BaseViewModel;
import com.developer.smmmousavi.clinic.model.Category;
import com.developer.smmmousavi.clinic.model.Question;
import com.developer.smmmousavi.clinic.network.bodies.PostQuestionBody;
import com.developer.smmmousavi.clinic.network.util.Resource;
import com.developer.smmmousavi.clinic.repository.QuestionRepository;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

public class QuestionsFragmentVM extends BaseViewModel {

    private static final String TAG = "QuestionsFragmentVM";

    private boolean mFirstQuestionPerformingQuery;
    private boolean mFirstQuestionRequestCanceled;
    private boolean mNextQuestionPerformingQuery;
    private boolean mNextQuestionRequestCanceled;
    private QuestionRepository mRepository;
    private MediatorLiveData<Resource<Question>> mFirstQuestionMLD;
    private MediatorLiveData<Resource<Question>> mNextQuestionMLD;


    public boolean isFirstQuestionPerformingQuery() {
        return mFirstQuestionPerformingQuery;
    }

    public boolean isFirstQuestionRequestCanceled() {
        return mFirstQuestionRequestCanceled;
    }

    public MediatorLiveData<Resource<Question>> getFirstQuestionMLD() {
        return mFirstQuestionMLD;
    }

    public MediatorLiveData<Resource<Question>> getNextQuestionMLD() {
        return mNextQuestionMLD;
    }

    @Inject
    public QuestionsFragmentVM(@NonNull Application application) {
        super(application);
        mRepository = QuestionRepository.getInstance(application);
        mFirstQuestionMLD = new MediatorLiveData<>();
        mNextQuestionMLD = new MediatorLiveData<>();
    }


    public void executeGetFirstQuestion(long categoryId) {
        mFirstQuestionPerformingQuery = true;
        mFirstQuestionRequestCanceled = false;
        final LiveData<Resource<Question>> repoSource = mRepository.getFirstCategoryQuestion(categoryId);
        mFirstQuestionMLD.addSource(repoSource, listResource -> {
            //onChange
            if (listResource != null) {
                mFirstQuestionMLD.setValue(listResource);
                if (listResource.status == Resource.Status.SUCCESS) {
                    mFirstQuestionPerformingQuery = false;
                    mFirstQuestionMLD.removeSource(repoSource);
                } else if (listResource.status == Resource.Status.ERROR) {
                    mFirstQuestionPerformingQuery = false;
                    mFirstQuestionMLD.removeSource(repoSource);
                }
            } else {
                mFirstQuestionMLD.removeSource(repoSource);
            }
        });
    }

    public void executePostQuestion(PostQuestionBody body) {
        mNextQuestionPerformingQuery = true;
        mNextQuestionRequestCanceled = false;
        final LiveData<Resource<Question>> repoSource = mRepository.postUserQuestion(body);
        mNextQuestionMLD.addSource(repoSource, listResource -> {
            //onChange
            if (listResource != null) {
                mNextQuestionMLD.setValue(listResource);
                if (listResource.status == Resource.Status.SUCCESS) {
                    mNextQuestionPerformingQuery = false;
                    mNextQuestionMLD.removeSource(repoSource);
                } else if (listResource.status == Resource.Status.ERROR) {
                    mNextQuestionPerformingQuery = false;
                    mNextQuestionMLD.removeSource(repoSource);
                }
            } else {
                mNextQuestionMLD.removeSource(repoSource);
            }
        });
    }

    public LiveData<Category> getCategoryById(long categoryId) {
        return mRepository.getCategoryById(categoryId);
    }

}
