package com.developer.smmmousavi.dandanyar.ui.fragments.splash.di;

import com.developer.smmmousavi.dandanyar.factory.viewmodel.di.ViewModelKey;
import com.developer.smmmousavi.dandanyar.ui.fragments.splash.SplashFragmentVM;

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
