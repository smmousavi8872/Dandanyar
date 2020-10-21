package com.developer.smmmousavi.dandanyar.ui.fragments.signin;

import android.app.Application;

import com.developer.smmmousavi.dandanyar.base.viewmodel.BaseViewModel;
import com.developer.smmmousavi.dandanyar.model.User;
import com.developer.smmmousavi.dandanyar.network.bodies.UserSignInBody;
import com.developer.smmmousavi.dandanyar.network.util.Resource;
import com.developer.smmmousavi.dandanyar.repository.UserRepository;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

public class SignInFragmentVM extends BaseViewModel {
    private static final String TAG = "SignInFragmentVM";

    private boolean mIsPerformingQuery;
    private boolean mCancelRequest;
    private UserRepository mRepository;
    private MediatorLiveData<Resource<User>> mUserMLD;

    public MediatorLiveData<Resource<User>> getUserMLD() {
        return mUserMLD;
    }

    @Inject
    public SignInFragmentVM(@NonNull Application application) {
        super(application);
        mRepository = UserRepository.getInstance(application);
        mUserMLD = new MediatorLiveData<>();
    }


    public void executeSignInRequest(UserSignInBody body) {
        mIsPerformingQuery = true;
        mCancelRequest = false;
        final LiveData<Resource<User>> repoSource = mRepository.signInRequest(body);
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
