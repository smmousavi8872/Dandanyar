package com.developer.smmmousavi.clinic.ui.fragments.base.di;


import com.developer.smmmousavi.clinic.ui.fragments.base.BaseDaggerFragment;
import com.developer.smmmousavi.clinic.ui.fragments.main.MainDrawerFragment;
import com.developer.smmmousavi.clinic.ui.fragments.main.di.MainDrawerFragmentModule;
import com.developer.smmmousavi.clinic.ui.fragments.main.di.MainDrawerFragmentVMModule;
import com.developer.smmmousavi.clinic.ui.fragments.signinsignup.SignInFragment;
import com.developer.smmmousavi.clinic.ui.fragments.signinsignup.SignUpFragment;
import com.developer.smmmousavi.clinic.ui.fragments.signinsignup.di.SignInFragmentModule;
import com.developer.smmmousavi.clinic.ui.fragments.signinsignup.di.SignUpFragmentModule;
import com.developer.smmmousavi.clinic.ui.fragments.splash.SplashFragment;
import com.developer.smmmousavi.clinic.ui.fragments.splash.di.SplashFragmentModule;
import com.developer.smmmousavi.clinic.ui.fragments.splash.di.SplashFragmentViewModelModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector(modules = {BaseDaggerFragmentModule.class})
    abstract BaseDaggerFragment contributeBaseDaggerFragment();

    @ContributesAndroidInjector(modules = {SignInFragmentModule.class})
    abstract SignInFragment contributeSingInFragment();

    @ContributesAndroidInjector(modules = {SignUpFragmentModule.class})
    abstract SignUpFragment contributeSignUpFragment();

    @ContributesAndroidInjector(modules = {MainDrawerFragmentModule.class, MainDrawerFragmentVMModule.class})
    abstract MainDrawerFragment contributeMainDrawerFragment();

    @ContributesAndroidInjector(modules = {SplashFragmentModule.class, SplashFragmentViewModelModule.class})
    abstract SplashFragment contributeSplashFragment();


}
