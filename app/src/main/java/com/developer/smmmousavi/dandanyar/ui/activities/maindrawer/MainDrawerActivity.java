package com.developer.smmmousavi.dandanyar.ui.activities.maindrawer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.developer.smmmousavi.dandanyar.factory.viewmodel.ViewModelProviderFactory;
import com.developer.smmmousavi.dandanyar.ui.activities.basedrawer.BaseDrawerActivity;
import com.developer.smmmousavi.dandanyar.ui.fragments.base.BaseDaggerFragment;
import com.developer.smmmousavi.dandanyar.ui.fragments.surveys.SurveysFragment;

import javax.inject.Inject;

import androidx.lifecycle.ViewModelProviders;

public class MainDrawerActivity extends BaseDrawerActivity {
    public static final String EXTRA_USER_ID = "ExtraUserId";
    private static final String TAG = "MainDrawerActivity";
    private MainDrawerActivityVM mViewModel;
    BaseDaggerFragment mFragmentObject = SurveysFragment.newInstance();;

    public void setFragmentObject(BaseDaggerFragment fragmentObject) {
        mFragmentObject = fragmentObject;
    }

    @Inject
    ViewModelProviderFactory mProviderFactory;


    public static Intent newIntent(Context origin, long userId) {
        Intent intent = new Intent(origin, MainDrawerActivity.class);
        intent.putExtra(EXTRA_USER_ID, userId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();

        subscribeObserver();
    }

    @Override
    public BaseDaggerFragment getFragmentObject() {
        return mFragmentObject;
    }
    @Override
    public String getFragmentTag() {
        return SurveysFragment.TAG;
    }

    @Override
    public boolean isToolbarVisible() {
        return true;
    }

    private void initViewModel() {
        long userId = getIntent().getLongExtra(EXTRA_USER_ID, -1);
        mViewModel = ViewModelProviders.of(this, mProviderFactory).get(MainDrawerActivityVM.class);
        mViewModel.executeGetUserById(userId);
    }

    private void subscribeObserver() {
        mViewModel.getUserMLD().observe(this, userResource -> {
            if (userResource.data != null) {
                String fullName = userResource.data.getFirstName() + " " + userResource.data.getLastName();
                initNavViewHeader(fullName);
            }
        });
    }
}