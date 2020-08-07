package com.developer.smmmousavi.clinic.ui.fragments.main;


import android.app.Application;

import com.developer.smmmousavi.clinic.base.viewmodel.BaseViewModel;

import javax.inject.Inject;

import androidx.annotation.NonNull;

public class MainDrawerFragmentVM extends BaseViewModel {

    @Inject
    public MainDrawerFragmentVM(@NonNull Application application) {
        super(application);
    }
}
