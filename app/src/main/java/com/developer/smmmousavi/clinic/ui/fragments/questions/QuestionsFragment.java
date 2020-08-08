package com.developer.smmmousavi.clinic.ui.fragments.questions;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.smmmousavi.clinic.R;
import com.developer.smmmousavi.clinic.factory.viewmodel.ViewModelProviderFactory;
import com.developer.smmmousavi.clinic.ui.fragments.base.BaseDaggerFragment;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionsFragment extends BaseDaggerFragment {

    public static final String ARGS_CATEGORY_ID = "ArgsCateogryId";

    @Inject
    ViewModelProviderFactory mProviderFactory;

    private long mCategoryId;
    private QuestionsFragmentVM mViewModel;

    public QuestionsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static QuestionsFragment newInstance(long categoriesId) {
        QuestionsFragment fragment = new QuestionsFragment();
        Bundle args = new Bundle();
        args.putLong(ARGS_CATEGORY_ID, categoriesId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCategoryId = getArguments().getLong(ARGS_CATEGORY_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_questions, container, false);
        ButterKnife.bind(this, v);
        initViewModel();
        subscirbeObserver();
        return v;
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this, mProviderFactory).get(QuestionsFragmentVM.class);
        mViewModel.executeGetFirstQuestion(mCategoryId);
    }

    private void subscirbeObserver() {
        mViewModel.getQuestionMLD().observe(this, listResource -> {
            //onChange
            if (listResource != null) {
                switch (listResource.status) {
                    case LOADING:
                        break;
                    case SUCCESS:
                        Log.d(TAG, "subscribeObserver: cache has been refreshed.");
                        Log.d(TAG, "subscribeObserver: status: SUCCESS, #categories: " + listResource.data.getText());
                        break;
                    case ERROR:
                        Log.e(TAG, "subscribeObserver: can not refresh the cache.");
                        Log.e(TAG, "subscribeObserver: Error message: " + listResource.message);
                        break;
                }
            }
        });
    }
}