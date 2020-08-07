package com.developer.smmmousavi.clinic.ui.activities.main;

import android.content.Context;
import android.content.Intent;

import com.developer.smmmousavi.clinic.R;
import com.developer.smmmousavi.clinic.ui.activities.drawer.BaseDrawerActivity;
import com.developer.smmmousavi.clinic.ui.fragments.main.MainDrawerFragment;

import androidx.fragment.app.Fragment;

public class MainDrawerActivity extends BaseDrawerActivity {

    public static Intent newIntent(Context origin) {
        Intent intent = new Intent(origin, MainDrawerActivity.class);
        return intent;
    }

    @Override
    public int getFragmentId() {
        return R.id.flDrawerContentFragmentContainer;
    }

    @Override
    public Fragment getFragmentObject() {
        return MainDrawerFragment.newInstance();
    }

    @Override
    public String getFragmentTag() {
        return MainDrawerFragment.TAG;
    }

    @Override
    public boolean isToolbarVisible() {
        return true;
    }
}