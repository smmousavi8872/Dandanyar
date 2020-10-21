package com.developer.smmmousavi.dandanyar.ui.fragments.signup.di;

import com.developer.smmmousavi.dandanyar.factory.viewmodel.di.ViewModelKey;
import com.developer.smmmousavi.dandanyar.ui.fragments.signup.SignUpFragmentVM;

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
