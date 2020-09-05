package com.developer.smmmousavi.clinic.ui.fragments.surveys.di;

import com.developer.smmmousavi.clinic.helper.RecyclerViewHelper;
import com.developer.smmmousavi.clinic.model.Survay;
import com.developer.smmmousavi.clinic.ui.adapter.SurvaiesRvAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class SurvaysFragmentModule {

    @Provides
    RecyclerViewHelper provideRecyclerViewHelper() {
        return new RecyclerViewHelper();
    }

    @Provides
    SurvaiesRvAdapter<Survay> provideSurvaiesRvAdapter() {
        return new SurvaiesRvAdapter<>();
    }
}
