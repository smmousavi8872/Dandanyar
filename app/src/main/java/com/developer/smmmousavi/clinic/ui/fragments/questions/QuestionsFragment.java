package com.developer.smmmousavi.clinic.ui.fragments.questions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.smmmousavi.clinic.R;
import com.developer.smmmousavi.clinic.ui.fragments.base.BaseDaggerFragment;

import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionsFragment extends BaseDaggerFragment {

    public static final String ARGS_CATEGORY_ID = "ArgsCateogryId";

    private long mCategoryId;

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
        return v;
    }
}