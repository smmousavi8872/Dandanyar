package com.developer.smmmousavi.clinic.ui.fragments.splash;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.smmmousavi.clinic.R;
import com.developer.smmmousavi.clinic.factory.viewmodel.ViewModelProviderFactory;
import com.developer.smmmousavi.clinic.ui.fragments.base.BaseDaggerFragment;
import com.developer.smmmousavi.clinic.util.AppUtils;

import javax.inject.Inject;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SplashFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SplashFragment extends BaseDaggerFragment {

    @BindView(R.id.txtVersion)
    AppCompatTextView mTxtVerison;

    @Inject
    ViewModelProviderFactory mProviderFactory;

    private SplashFragmentVM mViewModel;

    public SplashFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SplashFragment newInstance() {
        SplashFragment fragment = new SplashFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVM();
        if (getArguments() != null) {
            //TODO: recieve arguments here
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_splash, container, false);
        ButterKnife.bind(this, v);

        initView();

        mViewModel.intentTo(this);

        return v;
    }


    private void initView() {
        mTxtVerison.setText("version " + AppUtils.getVersionName(getContext()));
    }

    private void initVM() {
        mViewModel = ViewModelProviders.of(this, mProviderFactory).get(SplashFragmentVM.class);
    }
}