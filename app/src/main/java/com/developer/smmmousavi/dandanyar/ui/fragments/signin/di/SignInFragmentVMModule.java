package com.developer.smmmousavi.dandanyar.ui.fragments.signin.di;

import com.developer.smmmousavi.dandanyar.factory.viewmodel.di.ViewModelKey;
import com.developer.smmmousavi.dandanyar.ui.fragments.signin.SignInFragmentVM;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class SignInFragmentVMModule {

    @Binds
    @IntoMap
    @ViewModelKey(SignInFragmentVM.class)
    public abstract ViewModel bindSingUpFragmentVM(SignInFragmentVM singUpFragmentVM);
}
