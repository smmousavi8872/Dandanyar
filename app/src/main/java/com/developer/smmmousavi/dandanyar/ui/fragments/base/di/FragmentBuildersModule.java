package com.developer.smmmousavi.dandanyar.ui.fragments.base.di;


import com.developer.smmmousavi.dandanyar.ui.fragments.base.BaseDaggerFragment;
import com.developer.smmmousavi.dandanyar.ui.fragments.categories.CategoriesFragment;
import com.developer.smmmousavi.dandanyar.ui.fragments.categories.di.CategoriesFragmentModule;
import com.developer.smmmousavi.dandanyar.ui.fragments.categories.di.CategoriesFragmentVMModule;
import com.developer.smmmousavi.dandanyar.ui.fragments.questions.QuestionsFragment;
import com.developer.smmmousavi.dandanyar.ui.fragments.questions.di.QuestionFragmentModule;
import com.developer.smmmousavi.dandanyar.ui.fragments.questions.di.QuestionsFragmentVMModule;
import com.developer.smmmousavi.dandanyar.ui.fragments.signin.SignInFragment;
import com.developer.smmmousavi.dandanyar.ui.fragments.signin.di.SignInFragmentModule;
import com.developer.smmmousavi.dandanyar.ui.fragments.signin.di.SignInFragmentVMModule;
import com.developer.smmmousavi.dandanyar.ui.fragments.signup.SignUpFragment;
import com.developer.smmmousavi.dandanyar.ui.fragments.signup.di.SignUpFragmentModule;
import com.developer.smmmousavi.dandanyar.ui.fragments.signup.di.SignUpFragmentVMModule;
import com.developer.smmmousavi.dandanyar.ui.fragments.splash.SplashFragment;
import com.developer.smmmousavi.dandanyar.ui.fragments.splash.di.SplashFragmentModule;
import com.developer.smmmousavi.dandanyar.ui.fragments.splash.di.SplashFragmentViewModelModule;
import com.developer.smmmousavi.dandanyar.ui.fragments.surveys.SurveysFragment;
import com.developer.smmmousavi.dandanyar.ui.fragments.surveys.di.SurvaysFragmentModule;
import com.developer.smmmousavi.dandanyar.ui.fragments.surveys.di.SurvaysFragmentVMModule;
import com.developer.smmmousavi.dandanyar.ui.viewholder.questionnum.di.QuestionNumVHModule;
import com.developer.smmmousavi.dandanyar.ui.viewholder.questionnum.di.QuestionNumVHVMModule;

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
    abstract SurveysFragment contributeSurvaysFragment();

    @ContributesAndroidInjector(modules = {SplashFragmentModule.class, SplashFragmentViewModelModule.class})
    abstract SplashFragment contributeSplashFragment();

    @ContributesAndroidInjector(modules = {CategoriesFragmentModule.class, CategoriesFragmentVMModule.class})
    abstract CategoriesFragment contributeCategoriesFragmentModule();

    @ContributesAndroidInjector(modules = {
        QuestionFragmentModule.class,
        QuestionsFragmentVMModule.class,
        QuestionNumVHVMModule.class,
        QuestionNumVHModule.class
    })
    abstract QuestionsFragment contributeQuestionFragment();


}
