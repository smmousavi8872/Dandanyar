package com.developer.smmmousavi.dandanyar.ui.fragments.questions.di;

import com.developer.smmmousavi.dandanyar.factory.viewmodel.di.ViewModelKey;
import com.developer.smmmousavi.dandanyar.ui.fragments.questions.QuestionsFragmentVM;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class QuestionsFragmentVMModule {

    @Binds
    @IntoMap
    @ViewModelKey(QuestionsFragmentVM.class)
    abstract ViewModel bindQuestionFragmenVM(QuestionsFragmentVM questionsFragmentVM);

}