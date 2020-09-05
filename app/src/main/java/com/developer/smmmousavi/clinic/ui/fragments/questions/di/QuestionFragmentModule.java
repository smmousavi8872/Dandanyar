package com.developer.smmmousavi.clinic.ui.fragments.questions.di;

import com.developer.smmmousavi.clinic.helper.RecyclerViewHelper;
import com.developer.smmmousavi.clinic.model.QuestionNumber;
import com.developer.smmmousavi.clinic.ui.adapter.QuestionNumRvAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class QuestionFragmentModule {

    @Provides
    RecyclerViewHelper provideRecyclerViewHelper() {
        return new RecyclerViewHelper();
    }

    @Provides
    QuestionNumRvAdapter<QuestionNumber> provideSurvaiesRvAdapter() {
        return new QuestionNumRvAdapter<>();
    }
}
