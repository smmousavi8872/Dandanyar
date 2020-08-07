package com.developer.smmmousavi.clinic.ui.fragments.splash.di;

import com.developer.smmmousavi.clinic.factory.viewmodel.di.ViewModelKey;
import com.developer.smmmousavi.clinic.ui.fragments.splash.SplashFragmentVM;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class SplashFragmentViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashFragmentVM.class)
    public abstract ViewModel bindSplashFragmentViewModel(SplashFragmentVM categoryListFragmentViewModel);
}
