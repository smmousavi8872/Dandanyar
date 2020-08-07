package com.developer.smmmousavi.clinic.ui.fragments.base;


import android.os.Bundle;

import androidx.annotation.AnimRes;
import androidx.annotation.AnimatorRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import dagger.android.support.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseDaggerFragment extends DaggerFragment {

    public static final String TAG = "BaseDaggerFragment";

    private FragmentManager mFm;

    public BaseDaggerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFm = getFragmentManager();
    }

    public void replaceFragment(@IdRes int containerId,
                                FragmentManager fm,
                                @NonNull Fragment newFragment,
                                @NonNull String newTag,
                                @AnimatorRes @AnimRes int enterAnimId,
                                @AnimatorRes @AnimRes int exitAnimId,
                                boolean popPrevious) {

        Fragment foundFragment = fm.findFragmentByTag(newTag);
        if (popPrevious) {
            int frgCount = fm.getBackStackEntryCount();
            if (frgCount > 0)
                fm.popBackStack();
        }

        if (foundFragment == null)
            fm.beginTransaction()
                .setCustomAnimations(enterAnimId, exitAnimId)
                .replace(containerId, newFragment, newTag)
                .addToBackStack(newTag)
                .commit();
        else
            fm.beginTransaction()
                .setCustomAnimations(enterAnimId, exitAnimId)
                .replace(containerId, newFragment, newTag)
                .commit();
    }

    public void removeFragment(@NonNull Fragment fragment) {
        mFm.beginTransaction()
            .remove(fragment)
            .commit();
    }


    public Fragment findFragmentByTag(String fragmentTag) {
        return mFm.findFragmentByTag(fragmentTag);
    }

}
