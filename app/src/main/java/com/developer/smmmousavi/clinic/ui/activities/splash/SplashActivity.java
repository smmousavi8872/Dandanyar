package com.developer.smmmousavi.clinic.ui.activities.splash;

import android.os.Bundle;

import com.developer.smmmousavi.clinic.R;
import com.developer.smmmousavi.clinic.ui.activities.singlefragment.SingleFragmentActivity;
import com.developer.smmmousavi.clinic.ui.fragments.base.BaseDaggerFragment;
import com.developer.smmmousavi.clinic.ui.fragments.splash.SplashFragment;

public class SplashActivity extends SingleFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStateBarColor(R.color.pureWhite, R.color.colorAccent);
    }

    @Override
    public BaseDaggerFragment createFragment() {
        return SplashFragment.newInstance();
    }

    @Override
    public String getTag() {
        return SplashFragment.TAG;
    }
}