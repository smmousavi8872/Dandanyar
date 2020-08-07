package com.developer.smmmousavi.clinic.ui.activities.categories;

import android.content.Context;
import android.content.Intent;

import com.developer.smmmousavi.clinic.ui.activities.singlefragment.SingleFragmentActivity;
import com.developer.smmmousavi.clinic.ui.fragments.base.BaseDaggerFragment;
import com.developer.smmmousavi.clinic.ui.fragments.categories.CategoriesFragment;

public class CategoriesActivity extends SingleFragmentActivity {

    public static final String EXTRA_SURVAY_ID = "ExtraSurvayId";

    public static Intent newIntent(Context origin, long survayId) {
        Intent intent = new Intent(origin, CategoriesActivity.class);
        intent.putExtra(EXTRA_SURVAY_ID, survayId);
        return intent;
    }

    @Override
    public BaseDaggerFragment createFragment() {
        long survayId = getIntent().getExtras().getLong(EXTRA_SURVAY_ID);
        return CategoriesFragment.newInstance(survayId);
    }

    @Override
    public String getTag() {
        return CategoriesFragment.TAG;
    }
}