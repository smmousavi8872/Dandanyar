package com.developer.smmmousavi.clinic.ui.activities.drawer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.developer.smmmousavi.clinic.R;
import com.developer.smmmousavi.clinic.ui.activities.base.BaseDaggerCompatActivity;
import com.developer.smmmousavi.clinic.ui.activities.signupsignin.signinsignup.SignInSignUpActivity;
import com.developer.smmmousavi.clinic.ui.fragments.base.BaseDaggerFragment;
import com.developer.smmmousavi.clinic.ui.fragments.categories.CategoriesFragment;
import com.developer.smmmousavi.clinic.ui.fragments.questions.QuestionsFragment;
import com.developer.smmmousavi.clinic.ui.fragments.survays.SurvaysFragment;
import com.developer.smmmousavi.clinic.util.Animations;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.AnimRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public abstract class BaseDrawerActivity extends BaseDaggerCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener, OnBackPressedListener, SetOnContentFragmentInsert, SetOnToolbarProperties {

    @IdRes
    private static final int sFragmentContainerId = R.id.flDrawerContentFragmentContainer;

    @BindView(R.id.navbarView)
    NavigationView mNavigationView;
    @BindView(R.id.dlMainFragmentDrawer)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.includeToolbar)
    AppBarLayout mToolabrLayout;
    @BindView(R.id.imgToolbarClose)
    AppCompatImageView mToolbarClose;
    @BindView(R.id.txtToolbarTitle)
    AppCompatTextView mTxtToolbarTitle;

    private AppCompatTextView mTxtSignupButton;
    private OnBackPressedListener mOnBackPressedListener;
    private BaseDaggerFragment mHostedFrgament;

    public NavigationView getNavigationView() {
        return mNavigationView;
    }

    public void setOnBackPressedListener(OnBackPressedListener listener) {
        mOnBackPressedListener = listener;
    }

    @Override
    protected void onResume() {
        super.onResume();
        uncheckNavBar();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_drawer);
        ButterKnife.bind(this);

        insertContentFragment(this);

        initNavView();

        initToolbar();
    }


    private void initToolbar() {
        if (!isToolbarVisible())
            mToolabrLayout.setVisibility(View.GONE);
        if (mHostedFrgament instanceof SurvaysFragment) {
            mToolbarClose.setOnClickListener(view -> {
                finish();
            });
            mTxtToolbarTitle.setText(R.string.toolbar_clinic_title);
        } else if (mHostedFrgament instanceof CategoriesFragment) {
            mToolbarClose.setOnClickListener(view -> {
                replaceBySurvaysFragment();
            });
            mTxtToolbarTitle.setText(R.string.toolbar_dentistry_title);
        } else if (mHostedFrgament instanceof QuestionsFragment) {
            mToolbarClose.setOnClickListener(view -> {
                replaceByCategories();
            });
            mTxtToolbarTitle.setText(R.string.toolbar_questions_title);
        }
        Animations.setAnimation(R.anim.hint_in, mTxtToolbarTitle);
    }

    private void replaceBySurvaysFragment() {
        replaceContentFragment(SurvaysFragment.newInstance(),
            SurvaysFragment.TAG,
            R.anim.activity_right_to_left,
            R.anim.activity_right_to_left2,
            true);
    }

    /**
     * @HardCoded
     * TODO: categoryId should recieve from server
     */
    private void replaceByCategories() {
        replaceContentFragment(CategoriesFragment.newInstance(0),
            CategoriesFragment.TAG,
            R.anim.activity_right_to_left,
            R.anim.activity_right_to_left2,
            true);
    }


    private void initNavView() {
        mNavigationView.setNavigationItemSelectedListener(this);
        mNavigationView.getMenu()
            .getItem(0)
            .setChecked(true);

        // to set listener on views of header, first shoud find them
        // here is how to get NavigationView header
        View navHeader = mNavigationView.getHeaderView(0);
        mTxtSignupButton = navHeader.findViewById(R.id.txtNavbarSignUpButton);
        mTxtSignupButton.setOnClickListener(v -> {
            startActivity(SignInSignUpActivity.newIntent(this));
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            new Handler().postDelayed(this::closeDrawer, 600);
        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        item.setChecked(true);
        setNavigationItemAction(item);

        //new Handler().postDelayed(this::closeDrawer, 150);
        return true;
    }

    private void setNavigationItemAction(@NonNull MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            /*case R.id.navbarMenuHome:
                if (!(getFragmentObject() instanceof HomeDrawerFragment)) {
                    intent = HomeDrawerActivity.newIntent(this);
                    // to finish all previous activities
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                } else
                    intent = null;
                break;
            case R.id.navbarMenuCatList:
                intent = CategoryListActivity.newIntent(this, 0);
                break;
            case R.id.navbarMenuShoppingBasket:
                intent = ShoppingBasketActivity.newIntent(this);
                break;
            case R.id.navbarMenuSpecialOffer:
                intent = SpecialOffersActivity.newIntent(this, OfferType.SPECIAL_OFFER);
                break;
            case R.id.navbarMenuMostSold:
                intent = SpecialOffersActivity.newIntent(this, OfferType.MOST_SOLD);
                break;
            case R.id.navbarMenuMostSeen:
                intent = SpecialOffersActivity.newIntent(this, OfferType.MOST_SEEN);
                break;
            case R.id.navbarMenuNewest:
                intent = SpecialOffersActivity.newIntent(this, OfferType.NEWEST);
                break;
            case R.id.navbarMenuSettings:
                intent = SettingsActivity.newIntent(this);
                break;
            case R.id.navbarMap:
                intent = MapActivity.newIntent(this);
                break;
            case R.id.navbarMenuAboutUs:
                intent = AboutUsActivity.newIntent(this);
                break;
            default:
                intent = HomeDrawerActivity.newIntent(this);
                break;*/
        }
        closeDrawer();
        if (intent != null)
            startActivity(intent);
        new Handler().postDelayed(() -> {
        }, 200);
    }

    public void closeDrawer() {
        mDrawerLayout.closeDrawer(GravityCompat.END);
    }

    public boolean isDrawerOpen() {
        return mDrawerLayout.isDrawerOpen(GravityCompat.END);
    }

    public void uncheckNavBar() {
        int itemCount = mNavigationView.getMenu().size();
        for (int i = 0; i < itemCount; i++) {
            if (mNavigationView.getMenu().getItem(i).isChecked()) {
                mNavigationView.getMenu().getItem(i).setChecked(false);
            }
        }
    }


    public void insertContentFragment(SetOnContentFragmentInsert contentSet) {
        mHostedFrgament = contentSet.getFragmentObject();
        String fragmentTag = contentSet.getFragmentTag();

        Fragment foundFragment = mFm.findFragmentById(sFragmentContainerId);

        if (foundFragment == null) {
            mFm.beginTransaction()
                .add(sFragmentContainerId, mHostedFrgament, fragmentTag)
                .commit();
        }
    }

    public void replaceContentFragment(BaseDaggerFragment newFragment,
                                       String tag,
                                       @AnimRes int enterAnimId,
                                       @AnimRes int exitAnimId,
                                       boolean popPrevious) {
        mHostedFrgament = newFragment;
        newFragment.replaceFragment(sFragmentContainerId,
            mFm,
            newFragment,
            tag,
            enterAnimId,
            exitAnimId,
            popPrevious);
        initToolbar();
    }

    @OnClick(R.id.imgToolbarNavbarButton)
    void setNavBarListener() {
        mDrawerLayout.openDrawer(GravityCompat.END);
        setOnBackPressedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (mHostedFrgament instanceof SurvaysFragment) {
            super.onBackPressed();
        } else if (mHostedFrgament instanceof CategoriesFragment) {
            replaceBySurvaysFragment();
        } else if(mHostedFrgament instanceof QuestionsFragment) {
            replaceByCategories();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_navbar, menu);
        return true;
    }


    @Override
    public void onBack() {
        closeDrawer();

    }
}

