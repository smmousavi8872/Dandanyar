package com.developer.smmmousavi.clinic.ui.fragments.main;


import android.app.Application;

import com.developer.smmmousavi.clinic.base.viewmodel.BaseViewModel;
import com.developer.smmmousavi.clinic.model.Survay;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainFragmentVM extends BaseViewModel {

    @Inject
    public MainFragmentVM(@NonNull Application application) {
        super(application);
    }

    /**
    *@HardCoded
    */
    public LiveData<List<Survay>> getSurvays() {
        List<Survay> survays = new ArrayList<>();
        Survay survay = new Survay("دندانپزشکی", "");
        survays.add(survay);
        return new MutableLiveData<>(survays);
    }
}
