package com.developer.smmmousavi.clinic.ui.fragments.main.di;

import com.developer.smmmousavi.clinic.factory.viewmodel.di.ViewModelKey;
import com.developer.smmmousavi.clinic.ui.fragments.main.MainFragmentVM;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainFragmentVMModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentVM.class)
    public abstract ViewModel bindCategoryListFragmentViewModel(MainFragmentVM categoryListFragmentViewModel);
}
