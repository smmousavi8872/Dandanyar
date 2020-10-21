package com.developer.smmmousavi.dandanyar.ui.fragments.questions.di;

import com.developer.smmmousavi.dandanyar.helper.RecyclerViewHelper;
import com.developer.smmmousavi.dandanyar.model.QuestionNumber;
import com.developer.smmmousavi.dandanyar.ui.adapter.QuestionNumRvAdapter;

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
