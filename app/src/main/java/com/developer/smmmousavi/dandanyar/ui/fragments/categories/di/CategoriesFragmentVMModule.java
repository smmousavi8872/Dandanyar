package com.developer.smmmousavi.dandanyar.ui.fragments.categories.di;

import com.developer.smmmousavi.dandanyar.factory.viewmodel.di.ViewModelKey;
import com.developer.smmmousavi.dandanyar.ui.fragments.categories.CategoriesFragmentVM;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class CategoriesFragmentVMModule  {

    @Binds
    @IntoMap
    @ViewModelKey(CategoriesFragmentVM.class)
    public abstract ViewModel bindCategoriesFragmentVM(CategoriesFragmentVM categoriesFragmentVM);


}
