package com.developer.smmmousavi.clinic.ui.fragments.base.di;


import com.developer.smmmousavi.clinic.ui.fragments.base.BaseDaggerFragment;
import com.developer.smmmousavi.clinic.ui.fragments.main.MainFragment;
import com.developer.smmmousavi.clinic.ui.fragments.main.di.MainFragmentModule;
import com.developer.smmmousavi.clinic.ui.fragments.main.di.MainFragmentVMModule;
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

    @ContributesAndroidInjector(modules = {MainFragmentModule.class, MainFragmentVMModule.class})
    abstract MainFragment contributeMainDrawerFragment();

    @ContributesAndroidInjector(modules = {SplashFragmentModule.class, SplashFragmentViewModelModule.class})
    abstract SplashFragment contributeSplashFragment();


}
