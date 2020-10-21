package com.developer.smmmousavi.dandanyar.ui.fragments.categories;

import android.app.Application;

import com.developer.smmmousavi.dandanyar.base.viewmodel.BaseViewModel;
import com.developer.smmmousavi.dandanyar.model.Category;
import com.developer.smmmousavi.dandanyar.network.util.Resource;
import com.developer.smmmousavi.dandanyar.repository.CategoryRepository;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

public class CategoriesFragmentVM extends BaseViewModel {

    public static final String QUERY_EXHAUSTED = "No More Results";

    private CategoryRepository mRepository;
    private MediatorLiveData<Resource<List<Category>>> mCategoryMLD;
    private boolean mIsPerformingQuery;
    private boolean mCancelRequest;

    public MediatorLiveData<Resource<List<Category>>> getCategoryMLD() {
        return mCategoryMLD;
    }

    public boolean isPerformingQuery() {
        return mIsPerformingQuery;
    }

    @Inject
    public CategoriesFragmentVM(@NonNull Application application) {
        super(application);
        mRepository = CategoryRepository.getInstance(application);
        mCategoryMLD = new MediatorLiveData<>();
    }


    public void executeGetCategories() {
        mIsPerformingQuery = true;
        mCancelRequest = false;
        final LiveData<Resource<List<Category>>> repoSource = mRepository.getCategories();
        mCategoryMLD.addSource(repoSource, listResource -> {
            //onChange
            if (listResource != null) {
                mCategoryMLD.setValue(listResource);
                if (listResource.status == Resource.Status.SUCCESS) {
                    mIsPerformingQuery = false;
                    mCategoryMLD.removeSource(repoSource);
                } else if (listResource.status == Resource.Status.ERROR) {
                    mIsPerformingQuery = false;
                    mCategoryMLD.removeSource(repoSource);
                }
            } else {
                mCategoryMLD.removeSource(repoSource);
            }
        });
    }

}
