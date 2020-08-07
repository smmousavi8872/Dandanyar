package com.developer.smmmousavi.clinic.ui.fragments.splash;

import android.app.Application;
import android.content.Intent;
import android.os.Handler;

import com.developer.smmmousavi.clinic.base.viewmodel.BaseViewModel;
import com.developer.smmmousavi.clinic.ui.activities.main.MainDrawerActivity;
import com.developer.smmmousavi.clinic.ui.fragments.base.BaseDaggerFragment;

import javax.inject.Inject;

import androidx.annotation.NonNull;

public class SplashFragmentVM extends BaseViewModel {

    @Inject
    public SplashFragmentVM(@NonNull Application application) {
        super(application);
    }

    public void intentTo(BaseDaggerFragment origin) {
        new Handler().postDelayed(() -> {
            Intent intent = MainDrawerActivity.newIntent(origin.getContext());
            origin.startActivity(intent);
            origin.getActivity().finish();
        }, 3000);
    }


}
