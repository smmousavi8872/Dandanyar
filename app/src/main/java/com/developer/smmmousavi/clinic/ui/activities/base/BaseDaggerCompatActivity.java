package com.developer.smmmousavi.clinic.ui.activities.base;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.developer.smmmousavi.clinic.R;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import dagger.android.support.DaggerAppCompatActivity;

public class BaseDaggerCompatActivity extends DaggerAppCompatActivity {

    public FragmentManager mFm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFm = getSupportFragmentManager();
    }


    @Override
    public void setContentView(int layoutResID) {
        ConstraintLayout constraintLayout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_base_dagger_app_compat, null);
        FrameLayout frameLayout = constraintLayout.findViewById(R.id.acitivty_content_wrapper);
        getLayoutInflater().inflate(layoutResID, frameLayout, true);
        super.setContentView(constraintLayout);
    }
}
