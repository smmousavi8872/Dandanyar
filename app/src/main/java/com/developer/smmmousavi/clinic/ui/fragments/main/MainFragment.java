package com.developer.smmmousavi.clinic.ui.fragments.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.smmmousavi.clinic.R;
import com.developer.smmmousavi.clinic.factory.viewmodel.ViewModelProviderFactory;
import com.developer.smmmousavi.clinic.helper.RecyclerViewHelper;
import com.developer.smmmousavi.clinic.model.Survay;
import com.developer.smmmousavi.clinic.ui.adapter.SurvaiesRvAdapter;
import com.developer.smmmousavi.clinic.ui.fragments.base.BaseDaggerFragment;

import java.util.List;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends BaseDaggerFragment {

    public static final String TAG = "MainDrawerFragmentTAG";

    @BindView(R.id.rvSurvaies)
    RecyclerView mRvSurvays;

    @Inject
    RecyclerViewHelper mRvHelper;
    @Inject
    SurvaiesRvAdapter<Survay> mSurvaysRvAdapter;
    @Inject
    ViewModelProviderFactory mProviderFactory;

    private MainFragmentVM mViewModel;

    public MainFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
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
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, v);
        initViewModle();
        subscribeObserver();
        return v;
    }

    private void initViewModle() {
        mViewModel = ViewModelProviders.of(this, mProviderFactory).get(MainFragmentVM.class);
    }

    private void subscribeObserver() {
        mViewModel.getSurvays().observe(this, this::initSurvaysRv);
    }

    private void initSurvaysRv(List<Survay> survays) {
        LinearLayoutManager mLM = mRvHelper.getLinearLayoutManager(getContext(), RecyclerViewHelper.Orientation.VERTICAL, false);
        mSurvaysRvAdapter.setItemList(survays);
        mRvSurvays = mRvHelper.buildRecyclerView(mLM, mRvSurvays, mSurvaysRvAdapter);
    }
}