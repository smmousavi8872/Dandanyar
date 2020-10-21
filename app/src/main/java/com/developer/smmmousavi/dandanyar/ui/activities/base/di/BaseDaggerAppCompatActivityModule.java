package com.developer.smmmousavi.dandanyar.ui.activities.base.di;


import com.developer.smmmousavi.dandanyar.helper.RecyclerViewHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class BaseDaggerAppCompatActivityModule {

    @Provides
    public RecyclerViewHelper provideRecyclerViewHelper() {
        return new RecyclerViewHelper();
    }

}
