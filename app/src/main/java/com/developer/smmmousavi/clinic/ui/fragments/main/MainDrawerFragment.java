package com.developer.smmmousavi.clinic.ui.fragments.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.smmmousavi.clinic.R;
import com.developer.smmmousavi.clinic.ui.fragments.base.BaseDaggerFragment;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainDrawerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainDrawerFragment extends BaseDaggerFragment {

    public static final String TAG = "MainDrawerFragmentTAG";

    public MainDrawerFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MainDrawerFragment newInstance() {
        MainDrawerFragment fragment = new MainDrawerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // TODO: Recieve parameters here
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main_drawer, container, false);
        return v;
    }
}