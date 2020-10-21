package com.developer.smmmousavi.dandanyar.ui.activities.maindrawer;

import android.app.Application;

import com.developer.smmmousavi.dandanyar.base.viewmodel.BaseViewModel;
import com.developer.smmmousavi.dandanyar.model.User;
import com.developer.smmmousavi.dandanyar.network.util.Resource;
import com.developer.smmmousavi.dandanyar.repository.UserRepository;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

public class MainDrawerActivityVM extends BaseViewModel {

    private final UserRepository mRepository;
    private final MediatorLiveData<Resource<User>> mUserMLD;
    private boolean mCancelRequest;
    private boolean mIsPerformingQuery;


    @Inject
    public MainDrawerActivityVM(@NonNull Application application) {
        super(application);
        mRepository = UserRepository.getInstance(application);
        mUserMLD = new MediatorLiveData<>();
    }


    public MediatorLiveData<Resource<User>>getUserMLD() {
        return mUserMLD;
    }

    public boolean isCancelRequest() {
        return mCancelRequest;
    }

    public void setCancelRequest(boolean cancelRequest) {
        mCancelRequest = cancelRequest;
    }

    public boolean isPerformingQuery() {
        return mIsPerformingQuery;
    }

    public void setPerformingQuery(boolean performingQuery) {
        mIsPerformingQuery = performingQuery;
    }


    public void executeGetUserById(long userId) {
        mIsPerformingQuery = true;
        mCancelRequest = false;
        final LiveData<Resource<User>> repoSource = mRepository.getUserById(userId);
        if (repoSource != null) {
            mUserMLD.addSource(repoSource, listResource -> {
                //onChange
                if (listResource != null) {
                    mUserMLD.setValue(listResource);
                    if (listResource.status == Resource.Status.SUCCESS) {
                        mIsPerformingQuery = false;
                        mUserMLD.removeSource(repoSource);
                    } else if (listResource.status == Resource.Status.ERROR) {
                        mIsPerformingQuery = false;
                        mUserMLD.removeSource(repoSource);
                    }
                } else {
                    mUserMLD.removeSource(repoSource);
                }
            });
        } else {
            mIsPerformingQuery = false;
            mUserMLD.removeSource(repoSource);
        }
    }

}
