package com.developer.smmmousavi.clinic.ui.fragments.categories;

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
 * Use the {@link CategoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoriesFragment extends BaseDaggerFragment {

    public static final String TAG = "CategoriesFragmentTag";
    public static final String ARGS_SURVAY_ID = "ArgsSurvayId";

    public CategoriesFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CategoriesFragment newInstance(long survayId) {
        CategoriesFragment fragment = new CategoriesFragment();
        Bundle args = new Bundle();
        args.putLong(ARGS_SURVAY_ID, survayId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_categories, container, false);
        ButterKnife.bind(this, v);
        return v;
    }
}