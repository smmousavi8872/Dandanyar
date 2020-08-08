package com.developer.smmmousavi.clinic.ui.fragments.signinsignup.di;

import com.developer.smmmousavi.clinic.factory.viewmodel.di.ViewModelKey;
import com.developer.smmmousavi.clinic.ui.fragments.signinsignup.SingUpFragmentVM;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class SingUpFragmentVMModule {

    @Binds
    @IntoMap
    @ViewModelKey(SingUpFragmentVM.class)
    public abstract ViewModel bindSingUpFragmentVM(SingUpFragmentVM singUpFragmentVM);
}
