package com.developer.smmmousavi.dandanyar.ui.activities.base.di;

import com.developer.smmmousavi.dandanyar.ui.activities.base.BaseDaggerCompatActivity;
import com.developer.smmmousavi.dandanyar.ui.activities.basedrawer.BaseDrawerActivity;
import com.developer.smmmousavi.dandanyar.ui.activities.basedrawer.di.BaseDrawerActivityModule;
import com.developer.smmmousavi.dandanyar.ui.activities.basedrawer.di.BaseDrawerActivityVMModule;
import com.developer.smmmousavi.dandanyar.ui.activities.maindrawer.MainDrawerActivity;
import com.developer.smmmousavi.dandanyar.ui.activities.maindrawer.di.MainActivityModule;
import com.developer.smmmousavi.dandanyar.ui.activities.maindrawer.di.MainActivityVMModule;
import com.developer.smmmousavi.dandanyar.ui.activities.signupsignin.signinsignup.SignInSignUpActivity;
import com.developer.smmmousavi.dandanyar.ui.activities.signupsignin.signinsignup.di.SignInSignUpActivityModule;
import com.developer.smmmousavi.dandanyar.ui.activities.singlefragment.SingleFragmentActivity;
import com.developer.smmmousavi.dandanyar.ui.activities.singlefragment.di.SingleFragmentActivityModule;
import com.developer.smmmousavi.dandanyar.ui.activities.splash.SplashActivity;
import com.developer.smmmousavi.dandanyar.ui.activities.splash.di.SplashActivityModule;

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

    @ContributesAndroidInjector(modules = {MainActivityModule.class, MainActivityVMModule.class})
    abstract MainDrawerActivity contributeMainActivity();

    @ContributesAndroidInjector(modules = {BaseDrawerActivityModule.class, BaseDrawerActivityVMModule.class})
    abstract BaseDrawerActivity contributeBaseDrawerModule();

    @ContributesAndroidInjector(modules = {SplashActivityModule.class})
    abstract SplashActivity contributeSplashActivity();
}