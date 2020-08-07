package com.developer.smmmousavi.clinic.ui.fragments.survays;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.smmmousavi.clinic.R;
import com.developer.smmmousavi.clinic.factory.viewmodel.ViewModelProviderFactory;
import com.developer.smmmousavi.clinic.helper.RecyclerViewHelper;
import com.developer.smmmousavi.clinic.model.Survay;
import com.developer.smmmousavi.clinic.ui.activities.main.MainDrawerActivity;
import com.developer.smmmousavi.clinic.ui.adapter.SurvaiesRvAdapter;
import com.developer.smmmousavi.clinic.ui.fragments.base.BaseDaggerFragment;
import com.developer.smmmousavi.clinic.ui.fragments.categories.CategoriesFragment;
import com.developer.smmmousavi.clinic.ui.viewholder.survay.SurvayItemClickListener;

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
 * Use the {@link SurvaysFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SurvaysFragment extends BaseDaggerFragment implements SurvayItemClickListener {

    public static final String TAG = "MainDrawerFragmentTAG";

    @BindView(R.id.rvSurvaies)
    RecyclerView mRvSurvays;

    @Inject
    RecyclerViewHelper mRvHelper;
    @Inject
    SurvaiesRvAdapter<Survay> mSurvaysRvAdapter;
    @Inject
    ViewModelProviderFactory mProviderFactory;

    private SurvaysFragmentVM mViewModel;

    public SurvaysFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SurvaysFragment newInstance() {
        SurvaysFragment fragment = new SurvaysFragment();
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
        View v = inflater.inflate(R.layout.fragment_survays, container, false);
        ButterKnife.bind(this, v);
        initViewModle();
        subscribeObserver();
        return v;
    }

    private void initViewModle() {
        mViewModel = ViewModelProviders.of(this, mProviderFactory).get(SurvaysFragmentVM.class);
    }

    private void subscribeObserver() {
        mViewModel.getSurvays().observe(this, this::initSurvaysRv);
    }

    private void initSurvaysRv(List<Survay> survays) {
        LinearLayoutManager mLM = mRvHelper.getLinearLayoutManager(getContext(), RecyclerViewHelper.Orientation.VERTICAL, false);
        mSurvaysRvAdapter.setItemList(survays);
        mSurvaysRvAdapter.setSurvayItemClickListener(this);
        mRvSurvays = mRvHelper.buildRecyclerView(mLM, mRvSurvays, mSurvaysRvAdapter);
    }

    @Override
    public void onItemClicked(long survayId) {
        ((MainDrawerActivity) getActivity()).replaceContentFragment(CategoriesFragment.newInstance(survayId),
            CategoriesFragment.TAG,
            R.anim.activity_left_to_right,
            R.anim.activity_left_to_right2,
            true);
    }
}