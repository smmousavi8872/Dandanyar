package com.developer.smmmousavi.dandanyar.ui.fragments.splash;

import android.app.Application;
import android.content.Intent;
import android.os.Handler;

import com.developer.smmmousavi.dandanyar.base.viewmodel.BaseViewModel;
import com.developer.smmmousavi.dandanyar.ui.activities.maindrawer.MainDrawerActivity;
import com.developer.smmmousavi.dandanyar.ui.activities.signupsignin.signinsignup.SignInSignUpActivity;
import com.developer.smmmousavi.dandanyar.ui.fragments.base.BaseDaggerFragment;
import com.developer.smmmousavi.dandanyar.util.SharedPrefUtils;

import javax.inject.Inject;

import androidx.annotation.NonNull;

public class SplashFragmentVM extends BaseViewModel {

    @Inject
    public SplashFragmentVM(@NonNull Application application) {
        super(application);
    }

    public void intentTo(BaseDaggerFragment origin) {
        new Handler().postDelayed(() -> {
             boolean signIn = SharedPrefUtils.getSignIn();
            if (signIn) {
                long userId = SharedPrefUtils.getSignedInUserId();
                Intent intent = MainDrawerActivity.newIntent(origin.getContext(), userId);
                origin.startActivity(intent);
            } else {
                Intent intent = SignInSignUpActivity.newIntent(origin.getContext());
                origin.startActivity(intent);
            }
            origin.getActivity().finish();
        }, 2000);
    }


}
