package com.developer.smmmousavi.clinic.ui.fragments.signinsignup;

import android.app.Application;

import com.developer.smmmousavi.clinic.base.viewmodel.BaseViewModel;
import com.developer.smmmousavi.clinic.network.bodies.UserSignInBody;

import javax.inject.Inject;

import androidx.annotation.NonNull;

public class SingInFragmentVM extends BaseViewModel {

    @Inject
    public SingInFragmentVM(@NonNull Application application) {
        super(application);
    }

    public void executeSingInRequest(UserSignInBody signInBody) {

    }
}