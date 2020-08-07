package com.developer.smmmousavi.clinic.ui.fragments.main.di;

import com.developer.smmmousavi.clinic.helper.RecyclerViewHelper;
import com.developer.smmmousavi.clinic.model.Survay;
import com.developer.smmmousavi.clinic.ui.adapter.SurvaiesRvAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainFragmentModule {

    @Provides
    RecyclerViewHelper provideRecyclerViewHelper() {
        return new RecyclerViewHelper();
    }

    @Provides
    SurvaiesRvAdapter<Survay> provideSurvaiesRvAdapter() {
        return new SurvaiesRvAdapter<>();
    }
}
