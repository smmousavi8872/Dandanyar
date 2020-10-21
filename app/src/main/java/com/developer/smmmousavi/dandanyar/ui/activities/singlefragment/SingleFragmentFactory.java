package com.developer.smmmousavi.dandanyar.ui.activities.singlefragment;


import com.developer.smmmousavi.dandanyar.ui.fragments.base.BaseDaggerFragment;

public interface SingleFragmentFactory {

   /*
    * Factory Method Design Pattern
    * Functionality: Factory methods which return products
    */

   BaseDaggerFragment createFragment();

   String getTag();
}
