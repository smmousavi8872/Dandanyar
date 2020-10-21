package com.developer.smmmousavi.dandanyar.ui.fragments.base.di;


import com.developer.smmmousavi.dandanyar.helper.RecyclerViewHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class BaseDaggerFragmentModule {
    @Provides
    public RecyclerViewHelper provideRecyclerViewHelper() {
        return new RecyclerViewHelper();
    }

}
