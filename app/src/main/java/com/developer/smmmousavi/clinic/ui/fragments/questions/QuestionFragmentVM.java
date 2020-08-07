package com.developer.smmmousavi.clinic.ui.fragments.questions;


import android.app.Application;

import com.developer.smmmousavi.clinic.base.viewmodel.BaseViewModel;

import javax.inject.Inject;

import androidx.annotation.NonNull;

public class QuestionFragmentVM extends BaseViewModel {

    @Inject
    public QuestionFragmentVM(@NonNull Application application) {
        super(application);
    }
}
