package com.developer.smmmousavi.clinic.ui.activities.maindrawer.di;


import com.developer.smmmousavi.clinic.factory.viewmodel.di.ViewModelKey;
import com.developer.smmmousavi.clinic.ui.activities.maindrawer.MainDrawerActivityVM;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainActivityVMModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainDrawerActivityVM.class)
    public abstract ViewModel bindMainDrawerActivityVM(MainDrawerActivityVM baseDrawerActivityVM);
}
