package com.developer.smmmousavi.clinic.ui.fragments.surveys.di;

import com.developer.smmmousavi.clinic.factory.viewmodel.di.ViewModelKey;
import com.developer.smmmousavi.clinic.ui.fragments.surveys.SurveysFragmentVM;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class SurvaysFragmentVMModule {

    @Binds
    @IntoMap
    @ViewModelKey(SurveysFragmentVM.class)
    public abstract ViewModel bindMainFragmentVM(SurveysFragmentVM surveysFragmentVM);
}
