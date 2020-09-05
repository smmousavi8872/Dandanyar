package com.developer.smmmousavi.clinic.repository;

import android.content.Context;
import android.util.Log;

import com.developer.smmmousavi.clinic.model.Category;
import com.developer.smmmousavi.clinic.network.AppExecutors;
import com.developer.smmmousavi.clinic.network.factory.SurvayRestApiFactory;
import com.developer.smmmousavi.clinic.network.responses.ApiResponse;
import com.developer.smmmousavi.clinic.network.responses.CategoriesResponse;
import com.developer.smmmousavi.clinic.network.responses.CategoryByIdResponse;
import com.developer.smmmousavi.clinic.network.util.NetworkBoundResource;
import com.developer.smmmousavi.clinic.network.util.Resource;
import com.developer.smmmousavi.clinic.presistence.dao.CategoryDAO;
import com.developer.smmmousavi.clinic.presistence.db.Database;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

public class CategoryRepository {
    private static final String TAG = "TAG";
    private static CategoryRepository sInstance;

    private CategoryDAO mCategoryDAO;


    public static CategoryRepository getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new CategoryRepository(context);
            return sInstance;
        }
        return sInstance;
    }

    private CategoryRepository(Context context) {
        mCategoryDAO = Database.getInstance(context).geCategoryDao();
    }

    public LiveData<Resource<Category>> getCategoryById(long categoryId) {
        return new NetworkBoundResource<Category, CategoryByIdResponse>(AppExecutors.getInstance()) {

            @Override
            protected void saveCallResult(@NonNull CategoryByIdResponse item) {
                if (item.getCategory() != null) {
                    mCategoryDAO.insertCategory(item.getCategory());
                    // if category already exists.
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable Category data) {
                // set the interval of request.
                return true;
            }

            @NonNull
            @Override
            protected LiveData<Category> loadFromDb() {
                return mCategoryDAO.getCategoryById(categoryId);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<CategoryByIdResponse>> createCall() {
                return SurvayRestApiFactory.create()
                    .getCategoryById(categoryId);
            }
        }.getAsLiveData();
    }


    public LiveData<Resource<List<Category>>> getCategories() {
        return new NetworkBoundResource<List<Category>, CategoriesResponse>(AppExecutors.getInstance()) {

            @Override
            protected void saveCallResult(@NonNull CategoriesResponse item) {
                if (item.getCategories() != null) {
                    mCategoryDAO.deleteAll();
                    Category[] categoryArr = new Category[item.getCategories().size()];
                    int index = 0;
                    for (long rowId : mCategoryDAO.insertCategories(item.getCategories().toArray(categoryArr))) {
                        // if category already exists.
                        if (rowId == -1) {
                            Log.d(TAG, "saveCallResult: CONFLICT... This recipe is already in the cache");
                            mCategoryDAO.updateCategory(
                                categoryArr[index].getId(),
                                categoryArr[index].getTitle()
                            );
                        }
                        index++;
                    }
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Category> data) {
                // set the interval of request.
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<Category>> loadFromDb() {
                return mCategoryDAO.getAllCategories();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<CategoriesResponse>> createCall() {
                return SurvayRestApiFactory.create()
                    .getCategories();
            }
        }.getAsLiveData();
    }


}
