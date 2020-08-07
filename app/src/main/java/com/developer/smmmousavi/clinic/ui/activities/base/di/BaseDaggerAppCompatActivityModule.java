package com.developer.smmmousavi.clinic.ui.activities.base.di;


import com.developer.smmmousavi.clinic.helper.RecyclerViewHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class BaseDaggerAppCompatActivityModule {

    @Provides
    public RecyclerViewHelper provideRecyclerViewHelper() {
        return new RecyclerViewHelper();
    }

}
