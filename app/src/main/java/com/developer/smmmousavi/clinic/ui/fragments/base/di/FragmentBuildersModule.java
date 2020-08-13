package com.developer.smmmousavi.clinic.ui.fragments.base.di;


import com.developer.smmmousavi.clinic.ui.fragments.base.BaseDaggerFragment;
import com.developer.smmmousavi.clinic.ui.fragments.categories.CategoriesFragment;
import com.developer.smmmousavi.clinic.ui.fragments.categories.di.CategoriesFragmentModule;
import com.developer.smmmousavi.clinic.ui.fragments.categories.di.CategoriesFragmentVMModule;
import com.developer.smmmousavi.clinic.ui.fragments.questions.QuestionsFragment;
import com.developer.smmmousavi.clinic.ui.fragments.questions.di.QuestionFragmentModule;
import com.developer.smmmousavi.clinic.ui.fragments.questions.di.QuestionsFragmentVMModule;
import com.developer.smmmousavi.clinic.ui.fragments.signin.SignInFragment;
import com.developer.smmmousavi.clinic.ui.fragments.signup.SignUpFragment;
import com.developer.smmmousavi.clinic.ui.fragments.signin.di.SignInFragmentModule;
import com.developer.smmmousavi.clinic.ui.fragments.signup.di.SignUpFragmentModule;
import com.developer.smmmousavi.clinic.ui.fragments.signup.di.SignUpFragmentVMModule;
import com.developer.smmmousavi.clinic.ui.fragments.signin.di.SignInFragmentVMModule;
import com.developer.smmmousavi.clinic.ui.fragments.splash.SplashFragment;
import com.developer.smmmousavi.clinic.ui.fragments.splash.di.SplashFragmentModule;
import com.developer.smmmousavi.clinic.ui.fragments.splash.di.SplashFragmentViewModelModule;
import com.developer.smmmousavi.clinic.ui.fragments.survays.SurvaysFragment;
import com.developer.smmmousavi.clinic.ui.fragments.survays.di.SurvaysFragmentModule;
import com.developer.smmmousavi.clinic.ui.fragments.survays.di.SurvaysFragmentVMModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector(modules = {BaseDaggerFragmentModule.class})
    abstract BaseDaggerFragment contributeBaseDaggerFragment();

    @ContributesAndroidInjector(modules = {SignInFragmentModule.class, SignInFragmentVMModule.class})
    abstract SignInFragment contributeSignInFragment();

    @ContributesAndroidInjector(modules = {SignUpFragmentModule.class, SignUpFragmentVMModule.class})
    abstract SignUpFragment contributeSignUpFragment();

    @ContributesAndroidInjector(modules = {SurvaysFragmentModule.class, SurvaysFragmentVMModule.class})
    abstract SurvaysFragment contributeSurvaysFragment();

    @ContributesAndroidInjector(modules = {SplashFragmentModule.class, SplashFragmentViewModelModule.class})
    abstract SplashFragment contributeSplashFragment();

    @ContributesAndroidInjector(modules = {CategoriesFragmentModule.class, CategoriesFragmentVMModule.class})
    abstract CategoriesFragment contributeCategoriesFragmentModule();

    @ContributesAndroidInjector(modules = {QuestionFragmentModule.class, QuestionsFragmentVMModule.class})
    abstract QuestionsFragment contributeQuestionFragment();


}
