package com.developer.smmmousavi.clinic.ui.activities.splash;

import com.developer.smmmousavi.clinic.ui.activities.singlefragment.SingleFragmentActivity;
import com.developer.smmmousavi.clinic.ui.fragments.base.BaseDaggerFragment;
import com.developer.smmmousavi.clinic.ui.fragments.splash.SplashFragment;

public class SplashActivity extends SingleFragmentActivity {
    @Override
    public BaseDaggerFragment createFragment() {
        return SplashFragment.newInstance();
    }

    @Override
    public String getTag() {
        return SplashFragment.TAG;
    }
}