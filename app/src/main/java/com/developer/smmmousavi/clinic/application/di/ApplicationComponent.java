package com.developer.smmmousavi.clinic.application.di;

import android.app.Application;

import com.developer.smmmousavi.clinic.application.BaseApplication;
import com.developer.smmmousavi.clinic.factory.viewmodel.di.ViewModelFactoryModule;
import com.developer.smmmousavi.clinic.ui.activities.base.di.ActivityBuildersModule;
import com.developer.smmmousavi.clinic.ui.fragments.base.di.FragmentBuildersModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
    AndroidSupportInjectionModule.class,
    BaseApplicationModule.class,
    ActivityBuildersModule.class,
    FragmentBuildersModule.class,
    ViewModelFactoryModule.class
})

public interface ApplicationComponent extends AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }
}
