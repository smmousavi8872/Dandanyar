package com.developer.smmmousavi.clinic.ui.fragments.categories.di;

import com.developer.smmmousavi.clinic.helper.RecyclerViewHelper;
import com.developer.smmmousavi.clinic.model.Category;
import com.developer.smmmousavi.clinic.ui.adapter.CategoriesRvAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class CategoriesFragmentModule{
    @Provides
    RecyclerViewHelper provideRecyclerViewHelper() {
        return new RecyclerViewHelper();
    }

    @Provides
    CategoriesRvAdapter<Category> provideSurvaiesRvAdapter() {
        return new CategoriesRvAdapter<>();
    }
}
