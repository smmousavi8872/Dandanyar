package com.developer.smmmousavi.clinic.ui.fragments.signinsignup.di;

import com.developer.smmmousavi.clinic.factory.viewmodel.di.ViewModelKey;
import com.developer.smmmousavi.clinic.ui.fragments.signinsignup.SingInFragmentVM;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class SignInFragmentVMModule {

    @Binds
    @IntoMap
    @ViewModelKey(SingInFragmentVM.class)
    public abstract ViewModel bindSingUpFragmentVM(SingInFragmentVM singUpFragmentVM);
}
