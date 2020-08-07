package com.developer.smmmousavi.clinic.ui.activities.singlefragment;


import com.developer.smmmousavi.clinic.ui.fragments.base.BaseDaggerFragment;

public interface SingleFragmentFactory {

   /*
    * Factory Method Design Pattern
    * Functionality: Factory methods which return products
    */

   BaseDaggerFragment createFragment();

   String getTag();
}
