package com.developer.smmmousavi.clinic.ui.activities.main;

import android.content.Context;
import android.content.Intent;

import com.developer.smmmousavi.clinic.ui.activities.drawer.BaseDrawerActivity;
import com.developer.smmmousavi.clinic.ui.fragments.base.BaseDaggerFragment;
import com.developer.smmmousavi.clinic.ui.fragments.survays.SurvaysFragment;

public class MainDrawerActivity extends BaseDrawerActivity {

    public static Intent newIntent(Context origin) {
        Intent intent = new Intent(origin, MainDrawerActivity.class);
        return intent;
    }

    @Override
    public BaseDaggerFragment getFragmentObject() {
        return SurvaysFragment.newInstance();
    }

    @Override
    public String getFragmentTag() {
        return SurvaysFragment.TAG;
    }

    @Override
    public boolean isToolbarVisible() {
        return true;
    }


}