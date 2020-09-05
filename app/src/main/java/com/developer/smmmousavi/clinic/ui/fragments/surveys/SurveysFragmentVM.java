package com.developer.smmmousavi.clinic.ui.fragments.surveys;


import android.app.Application;

import com.developer.smmmousavi.clinic.base.viewmodel.BaseViewModel;
import com.developer.smmmousavi.clinic.model.Survay;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class SurveysFragmentVM extends BaseViewModel {

    @Inject
    public SurveysFragmentVM(@NonNull Application application) {
        super(application);
    }

    /**
     * @HardCoded TODO: should recieve survay from server
     */
    public LiveData<List<Survay>> getSurvays() {
        List<Survay> survays = new ArrayList<>();
        Survay survay = new Survay("پرسشنامه", "مشکلات دهان و دندان", "");
        survays.add(survay);
        return new MutableLiveData<>(survays);
    }
}
