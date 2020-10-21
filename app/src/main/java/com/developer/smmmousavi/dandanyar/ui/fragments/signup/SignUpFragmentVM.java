package com.developer.smmmousavi.dandanyar.ui.fragments.signup;

import android.app.Application;

import com.developer.smmmousavi.dandanyar.base.viewmodel.BaseViewModel;
import com.developer.smmmousavi.dandanyar.model.User;
import com.developer.smmmousavi.dandanyar.network.bodies.UserSignUpBody;
import com.developer.smmmousavi.dandanyar.network.util.Resource;
import com.developer.smmmousavi.dandanyar.repository.UserRepository;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

public class SignUpFragmentVM extends BaseViewModel {

    private boolean mIsPerformingQuery;
    private boolean mCancelRequest;
    private UserRepository mRepository;
    private MediatorLiveData<Resource<User>> mUserMLD;

    public MediatorLiveData<Resource<User>> getUserMLD() {
        return mUserMLD;
    }

    @Inject
    public SignUpFragmentVM(@NonNull Application application) {
        super(application);
        mRepository = UserRepository.getInstance(application);
        mUserMLD = new MediatorLiveData<>();
    }


    public void execuateSingUpRequest(UserSignUpBody signUpBody) {
        mIsPerformingQuery = true;
        mCancelRequest = false;
        final LiveData<Resource<User>> repoSource = mRepository.signUpRequest(signUpBody);
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
    }
}
