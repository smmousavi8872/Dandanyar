package com.developer.smmmousavi.clinic.repository;

import android.content.Context;

public class CategoryRepository {
    private static final String TAG = "TAG";
    private static CategoryRepository sInstance;


    public static CategoryRepository getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new CategoryRepository(context);
            return sInstance;
        }
        return sInstance;
    }


    private CategoryRepository(Context context) {

    }

}
