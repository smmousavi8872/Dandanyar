package com.developer.smmmousavi.dandanyar.ui.viewholder.questionnum.di;

import com.developer.smmmousavi.dandanyar.factory.viewmodel.di.ViewModelKey;
import com.developer.smmmousavi.dandanyar.ui.viewholder.questionnum.QuestionNumVHVM;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class QuestionNumVHVMModule {

    @Binds
    @IntoMap
    @ViewModelKey(QuestionNumVHVM.class)
    public abstract ViewModel bindQuestionVHVM(QuestionNumVHVM questionNumVHVM);

}
