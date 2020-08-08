package com.developer.smmmousavi.clinic.ui.fragments.signinsignup.di;

import com.developer.smmmousavi.clinic.factory.viewmodel.di.ViewModelKey;
import com.developer.smmmousavi.clinic.ui.fragments.signinsignup.SignUpFragmentVM;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class SignUpFragmentVMModule {

    @Binds
    @IntoMap
    @ViewModelKey(SignUpFragmentVM.class)
    public abstract ViewModel bindSingUpFragmentVM(SignUpFragmentVM signUpFragmentVM);
}