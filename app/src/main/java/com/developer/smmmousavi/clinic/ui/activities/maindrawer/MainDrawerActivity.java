package com.developer.smmmousavi.clinic.ui.activities.maindrawer;

import android.content.Context;
import android.content.Intent;

import com.developer.smmmousavi.clinic.ui.activities.basedrawer.BaseDrawerActivity;
import com.developer.smmmousavi.clinic.ui.fragments.base.BaseDaggerFragment;
import com.developer.smmmousavi.clinic.ui.fragments.survays.SurvaysFragment;

public class MainDrawerActivity extends BaseDrawerActivity {
    public static final String EXTRA_PARCELABLE_USER = "ExtraParcelableUser";


    public static Intent newIntent(Context origin, long userId) {
        Intent intent = new Intent(origin, MainDrawerActivity.class);
        intent.putExtra(EXTRA_PARCELABLE_USER, userId);
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