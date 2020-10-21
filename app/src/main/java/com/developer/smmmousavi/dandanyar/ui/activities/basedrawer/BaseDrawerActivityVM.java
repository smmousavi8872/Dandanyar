package com.developer.smmmousavi.dandanyar.ui.activities.basedrawer;

import android.app.Application;

import com.developer.smmmousavi.dandanyar.base.viewmodel.BaseViewModel;

import javax.inject.Inject;

import androidx.annotation.NonNull;

public class BaseDrawerActivityVM extends BaseViewModel {


    @Inject
    public BaseDrawerActivityVM(@NonNull Application application) {
        super(application);
    }
}
