package com.developer.smmmousavi.clinic.ui.activities.base.di;

import com.developer.smmmousavi.clinic.ui.activities.base.BaseDaggerCompatActivity;
import com.developer.smmmousavi.clinic.ui.activities.drawer.BaseDrawerActivity;
import com.developer.smmmousavi.clinic.ui.activities.drawer.di.BaseDrawerActivityModule;
import com.developer.smmmousavi.clinic.ui.activities.main.MainDrawerActivity;
import com.developer.smmmousavi.clinic.ui.activities.main.di.MainActivityModule;
import com.developer.smmmousavi.clinic.ui.activities.signupsignin.signinsignup.SignInSignUpActivity;
import com.developer.smmmousavi.clinic.ui.activities.signupsignin.signinsignup.di.SignInSignUpActivityModule;
import com.developer.smmmousavi.clinic.ui.activities.singlefragment.SingleFragmentActivity;
import com.developer.smmmousavi.clinic.ui.activities.singlefragment.di.SingleFragmentActivityModule;
import com.developer.smmmousavi.clinic.ui.activities.splash.SplashActivity;
import com.developer.smmmousavi.clinic.ui.activities.splash.di.SplashActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = {BaseDaggerAppCompatActivityModule.class})
    abstract BaseDaggerCompatActivity contributeBaseDaggerAppCompatActivity();

    @ContributesAndroidInjector(modules = {SingleFragmentActivityModule.class})
    abstract SingleFragmentActivity contributeSingleFragmentActivity();

    @ContributesAndroidInjector(modules = {SignInSignUpActivityModule.class})
    abstract SignInSignUpActivity contributeSignInSignUpActivity();

    @ContributesAndroidInjector(modules = {MainActivityModule.class})
    abstract MainDrawerActivity contributeMainActivity();

    @ContributesAndroidInjector(modules = {BaseDrawerActivityModule.class})
    abstract BaseDrawerActivity contributeBaseDrawerModule();

    @ContributesAndroidInjector(modules = {SplashActivityModule.class})
    abstract SplashActivity contributeSplashActivity();
}