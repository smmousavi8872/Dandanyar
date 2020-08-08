package com.developer.smmmousavi.clinic.ui.fragments.signinsignup;

import android.app.Application;

import com.developer.smmmousavi.clinic.base.viewmodel.BaseViewModel;

import javax.inject.Inject;

import androidx.annotation.NonNull;

public class SingUpFragmentVM extends BaseViewModel {

    @Inject
    public SingUpFragmentVM(@NonNull Application application) {
        super(application);
    }
}
