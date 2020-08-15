package com.developer.smmmousavi.clinic.ui.activities.basedrawer.di;

import com.developer.smmmousavi.clinic.factory.viewmodel.di.ViewModelKey;
import com.developer.smmmousavi.clinic.ui.activities.basedrawer.BaseDrawerActivityVM;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class BaseDrawerActivityVMModule {

    @Binds
    @IntoMap
    @ViewModelKey(BaseDrawerActivityVM.class)
    public abstract ViewModel bindBaseDrawerActivityVM(BaseDrawerActivityVM baseDrawerActivityVM);
}
