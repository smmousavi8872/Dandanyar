package com.developer.smmmousavi.dandanyar.ui.fragments.questions.di;

import com.developer.smmmousavi.dandanyar.factory.viewmodel.di.ViewModelKey;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class QuestionsFragmentVMModule {

    @Binds
    @IntoMap
    @ViewModelKey(com.developer.smmmousavi.dandanyar.ui.fragments.questions.QuestionsFragmentVM.class)
    abstract ViewModel bindQuestionFragmenVM(com.developer.smmmousavi.dandanyar.ui.fragments.questions.QuestionsFragmentVM questionsFragmentVM);

}
