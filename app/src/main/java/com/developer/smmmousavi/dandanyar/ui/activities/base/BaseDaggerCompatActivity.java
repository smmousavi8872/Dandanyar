package com.developer.smmmousavi.dandanyar.ui.activities.base;

import android.os.Build;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.developer.smmmousavi.dandanyar.R;

import androidx.annotation.ColorRes;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import dagger.android.support.DaggerAppCompatActivity;

public class BaseDaggerCompatActivity extends DaggerAppCompatActivity {

    public FragmentManager mFm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFm = getSupportFragmentManager();

        setStateBarColor(R.color.pureBlack, R.color.secondBackground);
    }


    protected void setStateBarColor(@ColorRes int textColor, @ColorRes int backgroundColor) {
        if (Build.VERSION.SDK_INT >= 21) {
            // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow()
                .setNavigationBarColor(ContextCompat.getColor(this, textColor));
            //status bar or the time bar at the top
            getWindow()
                .setStatusBarColor(ContextCompat.getColor(this, backgroundColor));
        }
    }


    @Override
    public void setContentView(int layoutResID) {
        ConstraintLayout constraintLayout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_base_dagger_app_compat, null);
        FrameLayout frameLayout = constraintLayout.findViewById(R.id.acitivty_content_wrapper);
        getLayoutInflater().inflate(layoutResID, frameLayout, true);
        super.setContentView(constraintLayout);
    }
}
