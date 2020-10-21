package com.developer.smmmousavi.dandanyar.ui.activities.basedrawer;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.developer.smmmousavi.dandanyar.R;
import com.developer.smmmousavi.dandanyar.ui.activities.base.BaseDaggerCompatActivity;
import com.developer.smmmousavi.dandanyar.ui.activities.basedrawer.callback.OnBackPressedListener;
import com.developer.smmmousavi.dandanyar.ui.activities.basedrawer.callback.OnContentFragmentInsertSet;
import com.developer.smmmousavi.dandanyar.ui.activities.basedrawer.callback.OnToolbarVisibilitySet;
import com.developer.smmmousavi.dandanyar.ui.activities.signupsignin.signinsignup.SignInSignUpActivity;
import com.developer.smmmousavi.dandanyar.ui.alertdialog.AlertDialogFragment;
import com.developer.smmmousavi.dandanyar.ui.alertdialog.OnDialogButtonClickListener;
import com.developer.smmmousavi.dandanyar.ui.fragments.base.BaseDaggerFragment;
import com.developer.smmmousavi.dandanyar.ui.fragments.categories.CategoriesFragment;
import com.developer.smmmousavi.dandanyar.ui.fragments.questions.QuestionsFragment;
import com.developer.smmmousavi.dandanyar.ui.fragments.surveys.SurveysFragment;
import com.developer.smmmousavi.dandanyar.util.Animations;
import com.developer.smmmousavi.dandanyar.util.SharedPrefUtils;
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
    implements NavigationView.OnNavigationItemSelectedListener, OnBackPressedListener, OnContentFragmentInsertSet, OnToolbarVisibilitySet {

    @IdRes
    private static final int sFragmentContainerId = R.id.flDrawerContentFragmentContainer;
    private static final String ALERT_DIALOG_FM_TAG = "AlertDialogFMTag";
    private static final String TAG = "BaseDrawerActivity";

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
    private AppCompatTextView mTxtExitAccount;
    private OnBackPressedListener mOnBackPressedListener;
    private BaseDaggerFragment mHostedFrgament;
    private AppCompatTextView mTxtNavBarUserName;


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

        initNavViewHeader("");

        initToolbar();
    }

    private void initToolbar() {
        if (!isToolbarVisible())
            mToolabrLayout.setVisibility(View.GONE);
        if (mHostedFrgament instanceof SurveysFragment) {
            mToolbarClose.setOnClickListener(view -> {
                showExitDialog();
            });
            mTxtToolbarTitle.setText(R.string.toolbar_dandanyar_title);
        } else if (mHostedFrgament instanceof CategoriesFragment) {
            mToolbarClose.setOnClickListener(view -> {
                replaceBySurvaysFragment();
            });
            mTxtToolbarTitle.setText(R.string.toolbar_oral_problems_title);
        } else if (mHostedFrgament instanceof QuestionsFragment) {
            mToolbarClose.setOnClickListener(view -> {
                replaceByCategoriesFragment();
            });
        }
        Animations.setAnimation(R.anim.hint_in, mTxtToolbarTitle);
    }

    public void setToolbarTitle(String title) {
        Animations.setAnimation(R.anim.hint_in, mTxtToolbarTitle);
        mTxtToolbarTitle.setText(title);
    }

    private void replaceBySurvaysFragment() {
        replaceContentFragment(SurveysFragment.newInstance(),
            SurveysFragment.TAG,
            R.anim.activity_left_to_right,
            R.anim.activity_left_to_right2,
            true);
    }

    /**
     * @HardCoded TODO: categoryId should recieve from server
     */
    private void replaceByCategoriesFragment() {
        replaceContentFragment(CategoriesFragment.newInstance(0),
            CategoriesFragment.TAG,
            R.anim.activity_right_to_left,
            R.anim.activity_right_to_left2,
            true);
    }

    public void replaceByQuestionsFragment(long categoryId) {
        new Handler().postDelayed(() -> {
            replaceContentFragment(QuestionsFragment.newInstance(categoryId),
                QuestionsFragment.TAG,
                R.anim.activity_left_to_right,
                R.anim.activity_left_to_right2,
                true);
        }, 200);
    }


    protected void initNavView() {
        mNavigationView.setNavigationItemSelectedListener(this);
        mNavigationView.getMenu()
            .getItem(0)
            .setChecked(true);
    }


    protected void initNavViewHeader(String userName) {
        // to set listener on views of header, first shoud find them
        // here is how to get NavigationView header
        View navHeader = mNavigationView.getHeaderView(0);
        LinearLayout userInfoContainer = navHeader.findViewById(R.id.llNavbarUserInfoContainer);
        RelativeLayout signInContainer = navHeader.findViewById(R.id.rlNavbarSigninContainer);
        if (SharedPrefUtils.getSignIn()) {
            signInContainer.setVisibility(View.GONE);
            userInfoContainer.setVisibility(View.VISIBLE);
            mTxtExitAccount = navHeader.findViewById(R.id.txtUserExitAccount);
            mTxtNavBarUserName = navHeader.findViewById(R.id.txtNavbarUsername);
            mTxtNavBarUserName.setText(userName);
            mTxtExitAccount.setOnClickListener(v -> {
                showExitAccountDialog();
            });

        } else {
            userInfoContainer.setVisibility(View.GONE);
            signInContainer.setVisibility(View.VISIBLE);
            mTxtSignupButton = navHeader.findViewById(R.id.txtNavbarSignUpButton);
            mTxtSignupButton.setOnClickListener(v -> {
                startActivity(SignInSignUpActivity.newIntent(this));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                new Handler().postDelayed(this::closeDrawer, 600);
            });
        }
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
        switch (item.getItemId()) {
            case R.id.navbarMenuHome:
                if (!(mHostedFrgament instanceof SurveysFragment))
                    replaceBySurvaysFragment();
                break;
            case R.id.navbarMenuCatList:
                if (!(mHostedFrgament instanceof CategoriesFragment))
                    replaceByCategoriesFragment();
                break;
        }
        closeDrawer();
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


    public void insertContentFragment(OnContentFragmentInsertSet contentSet) {
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

    private void showExitDialog() {
        String title = getString(R.string.exit);
        String message = getString(R.string.are_u_sure_exit);
        String positiveButtonText = getString(R.string.yes);
        String negativeButtonText = getString(R.string.cancel);
        AlertDialogFragment dialog = AlertDialogFragment.newInstance(title,
            message,
            positiveButtonText,
            negativeButtonText);

        dialog.setCancelable(false);
        dialog.setButtonClickListener(new OnDialogButtonClickListener() {
            @Override
            public void onPositiveButtonClick(View v) {
                dialog.dismiss();
                finish();
            }

            @Override
            public void onNegativeButtonClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show(mFm, ALERT_DIALOG_FM_TAG);
    }

    private void showExitAccountDialog() {
        String title = getString(R.string.exit_user_account);
        String message = getString(R.string.are_u_sure_exit_account);
        String positiveButtonText = getString(R.string.yes);
        String negativeButtonText = getString(R.string.cancel);
        AlertDialogFragment dialog = AlertDialogFragment.newInstance(title,
            message,
            positiveButtonText,
            negativeButtonText);

        dialog.setCancelable(false);
        dialog.setButtonClickListener(new OnDialogButtonClickListener() {
            @Override
            public void onPositiveButtonClick(View v) {
                startActivity(SignInSignUpActivity.newIntent(BaseDrawerActivity.this));
                SharedPrefUtils.setSignedIn(false);
                finish();
            }

            @Override
            public void onNegativeButtonClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show(mFm, ALERT_DIALOG_FM_TAG);
    }

    @OnClick(R.id.imgToolbarNavbarButton)
    void setNavBarListener() {
        mDrawerLayout.openDrawer(GravityCompat.END);
        setOnBackPressedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (mHostedFrgament instanceof SurveysFragment) {
            showExitDialog();
        } else if (mHostedFrgament instanceof CategoriesFragment) {
            replaceBySurvaysFragment();
        } else if (mHostedFrgament instanceof QuestionsFragment) {
            replaceByCategoriesFragment();
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

