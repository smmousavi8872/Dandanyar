package com.developer.smmmousavi.clinic.ui.fragments.questions.di;

import com.developer.smmmousavi.clinic.helper.RecyclerViewHelper;
import com.developer.smmmousavi.clinic.model.Category;
import com.developer.smmmousavi.clinic.ui.adapter.CategoriesRvAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class QuestionFragmentModule {

    @Provides
    RecyclerViewHelper provideRecyclerViewHelper() {
        return new RecyclerViewHelper();
    }

    @Provides
    CategoriesRvAdapter<Category> provideSurvaiesRvAdapter() {
        return new CategoriesRvAdapter<>();
    }
}
