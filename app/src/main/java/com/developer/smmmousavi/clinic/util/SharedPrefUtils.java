package com.developer.smmmousavi.clinic.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.developer.smmmousavi.clinic.application.BaseApplication;
import com.developer.smmmousavi.clinic.constants.Constants;


public class SharedPrefUtils {

    public static final String SHARED_PERF_NAME = "sharedPreferencesName";
    public static final String SHOPPING_BASKET_BADGE_COUNT = "shoppingBasketBadgeCount";

    private static SharedPreferences mSharedPref = getSharedPref();

    private static SharedPreferences getSharedPref() {
        return BaseApplication.getAppContext()
            .getSharedPreferences(SHARED_PERF_NAME, Context.MODE_PRIVATE);
    }

    public static void putInt(String key, int value) {
        mSharedPref.edit()
            .putInt(key, value)
            .apply();
    }

    public static int getInt(String key, int defValue) {
        return mSharedPref.getInt(key, defValue);
    }

    public static void putString(String key, String value) {
        mSharedPref.edit()
            .putString(key, value)
            .apply();
    }

    public static String getString(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static void setSignedIn(boolean signIn) {
        mSharedPref.edit()
            .putBoolean(Constants.SHARED_PREF_LOGIN_KEY, signIn)
            .apply();
    }

    public static boolean getSignIn() {
        return mSharedPref.getBoolean(Constants.SHARED_PREF_LOGIN_KEY, false);
    }

    public static void setSignedInUserId(long userId) {
        mSharedPref.edit()
            .putLong(Constants.SHARED_PREF_LOGED_IN_USER_ID_KEY, userId)
            .apply();
    }

    public static long getSignedInUserId() {
        return mSharedPref.getLong(Constants.SHARED_PREF_LOGED_IN_USER_ID_KEY, -1);
    }
}
