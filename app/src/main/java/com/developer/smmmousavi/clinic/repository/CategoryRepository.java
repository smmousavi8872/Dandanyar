package com.developer.smmmousavi.clinic.repository;

import android.content.Context;
import android.util.Log;

import com.developer.smmmousavi.clinic.model.Category;
import com.developer.smmmousavi.clinic.network.AppExecutors;
import com.developer.smmmousavi.clinic.network.factory.SurvayRestApiFactory;
import com.developer.smmmousavi.clinic.network.responses.ApiResponse;
import com.developer.smmmousavi.clinic.network.responses.CategoriesResponse;
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

    public LiveData<Resource<List<Category>>> getCategories(final String query, final int pageNumber) {
        return new NetworkBoundResource<List<Category>, CategoriesResponse>(AppExecutors.getInstance()) {

            @Override
            protected void saveCallResult(@NonNull CategoriesResponse item) {

                if (item.getCategories() != null) {
                    //  recipe list will be null if the api key is expired
                    Category[] categoryArr = new Category[item.getCategories().size()];
                    int index = 0;
                    for (long rowId : mCategoryDAO.insertCategories(item.getCategories().toArray(categoryArr))) {
                        if (rowId == -1) {
                            Log.d(TAG, "saveCallResult: CONFLICT... This recipe is already in the cache");
                            // if the recipe already exists... I don't want to set the ingredients or timestamp b/c
                            // they will be erased
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
