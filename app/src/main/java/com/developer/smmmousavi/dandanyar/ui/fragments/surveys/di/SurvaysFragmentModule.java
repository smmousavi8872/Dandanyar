package com.developer.smmmousavi.dandanyar.ui.fragments.surveys.di;

import com.developer.smmmousavi.dandanyar.helper.RecyclerViewHelper;
import com.developer.smmmousavi.dandanyar.model.Survay;
import com.developer.smmmousavi.dandanyar.ui.adapter.SurvaiesRvAdapter;

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
