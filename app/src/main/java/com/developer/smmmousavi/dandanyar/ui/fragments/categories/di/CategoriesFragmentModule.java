package com.developer.smmmousavi.dandanyar.ui.fragments.categories.di;

import com.developer.smmmousavi.dandanyar.helper.RecyclerViewHelper;
import com.developer.smmmousavi.dandanyar.model.Category;
import com.developer.smmmousavi.dandanyar.ui.adapter.CategoriesRvAdapter;

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
