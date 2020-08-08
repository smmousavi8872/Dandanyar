package com.developer.smmmousavi.clinic.application;


import android.content.Context;

import com.developer.smmmousavi.clinic.application.di.DaggerApplicationComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class BaseApplication extends DaggerApplication {

    private static volatile Context sApplicationContext;

    public static Context getAppContext() {
        return sApplicationContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            sApplicationContext = getApplicationContext();
        } catch (Throwable ignore) {

        }
        if (sApplicationContext == null) {
            sApplicationContext = getApplicationContext();
        }
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerApplicationComponent.builder().application(this).build();
    }


}
