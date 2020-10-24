package com.developer.smmmousavi.dandanyar.ui.fragments.categories;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.smmmousavi.dandanyar.R;
import com.developer.smmmousavi.dandanyar.factory.viewmodel.ViewModelProviderFactory;
import com.developer.smmmousavi.dandanyar.helper.RecyclerViewHelper;
import com.developer.smmmousavi.dandanyar.model.Category;
import com.developer.smmmousavi.dandanyar.ui.activities.basedrawer.BaseDrawerActivity;
import com.developer.smmmousavi.dandanyar.ui.adapter.CategoriesRvAdapter;
import com.developer.smmmousavi.dandanyar.ui.fragments.base.BaseDaggerFragment;
import com.developer.smmmousavi.dandanyar.ui.viewholder.category.CategoryItemClickListener;
import com.developer.smmmousavi.dandanyar.util.Animations;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoriesFragment extends BaseDaggerFragment implements CategoryItemClickListener {

    public static final String TAG = "CategoriesFragmentTag";
    public static final String ARGS_SURVAY_ID = "ArgsSurvayId";


    @BindView(R.id.rvCategories)
    RecyclerView mRvCategories;
    @BindView(R.id.categoryLoading)
    SpinKitView mLoadingView;
    @BindView(R.id.txtCategoryLoadingContent)
    AppCompatTextView mTxtLoadingContent;

    @Inject
    RecyclerViewHelper mRvHelper;
    @Inject
    CategoriesRvAdapter<Category> mCategoriesRvAdapter;
    @Inject
    ViewModelProviderFactory mProviderFactory;

    private CategoriesFragmentVM mViewModel;
    private long mSurvayId;

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
            mSurvayId = getArguments().getLong(ARGS_SURVAY_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_categories, container, false);
        ButterKnife.bind(this, v);
        initViewModel();

        subscribeObserver();

        return v;
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this, mProviderFactory).get(CategoriesFragmentVM.class);
    }

    private void initCategoriesRv(List<Category> categories) {
        LinearLayoutManager mLM = mRvHelper.getLinearLayoutManager(getContext(), RecyclerViewHelper.Orientation.VERTICAL, false);
        mCategoriesRvAdapter.setItemList(categories);
        mCategoriesRvAdapter.setItemClickListener(this);
        mRvCategories = mRvHelper.buildRecyclerView(mLM, mRvCategories, mCategoriesRvAdapter);
    }

    private void subscribeObserver() {
        mViewModel.executeGetCategories();
        mViewModel.getCategoryMLD().observe(this, listResource -> {
            //onChange
            if (listResource != null) {
                switch (listResource.status) {
                    case LOADING:
                        break;
                    case SUCCESS:
                        Log.d(TAG, "subscribeObserverC: cache has been refreshed.");
                        Log.d(TAG, "subscribeObserverC: status: SUCCESS, #categories: " + listResource.data.size());
                        new Handler().postDelayed(() -> {
                            initCategoriesRv(listResource.data);
                            Animations.setAnimation(Animations.FADE_OUT, mLoadingView, mTxtLoadingContent);
                        }, 1000);
                        break;
                    case ERROR:
                        mLoadingView.setVisibility(View.GONE);
                        mTxtLoadingContent.setVisibility(View.GONE);
                        initCategoriesRv(listResource.data);
                        Log.e(TAG, "subscribeObserverC: can not refresh the cache.");
                        Log.e(TAG, "subscribeObserverC: Error message: " + listResource.message);
                        Log.e(TAG, "subscribeObserverC: status: ERROR, #recipes: " + listResource.data.size());
                        break;
                }
            }
        });
    }

    @Override
    public void onItemClicked(long categoryId) {
        ((BaseDrawerActivity) getActivity()).replaceByQuestionsFragment(categoryId);
    }
}