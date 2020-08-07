package com.developer.smmmousavi.clinic.ui.fragments.survays.di;

import com.developer.smmmousavi.clinic.factory.viewmodel.di.ViewModelKey;
import com.developer.smmmousavi.clinic.ui.fragments.survays.SurvaysFragmentVM;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class SurvaysFragmentVMModule {

    @Binds
    @IntoMap
    @ViewModelKey(SurvaysFragmentVM.class)
    public abstract ViewModel bindMainFragmentVM(SurvaysFragmentVM survaysFragmentVM);
}
