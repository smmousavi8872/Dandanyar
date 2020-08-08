package com.developer.smmmousavi.clinic.sharepref;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class StringSharedPref {

    private static final String TAG = "TAG";
    private static StringSharedPref sInstance;
    private SharedPreferences mPreferences;


    public static StringSharedPref getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new StringSharedPref(context);
            return sInstance;
        }
        return sInstance;
    }

    private StringSharedPref(Context context) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void writeStringInSP(String inputLaybel, String input ) {
        mPreferences.edit().putString(inputLaybel, input).apply();
    }

    public String readStringFromSP(String inputLaybel) {
        return mPreferences.getString(inputLaybel, "stringNotFound");
    }

}
