package com.developer.smmmousavi.clinic.ui.activities.signupsignin.signinsignup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.developer.smmmousavi.clinic.R;
import com.developer.smmmousavi.clinic.ui.activities.base.BaseDaggerCompatActivity;
import com.developer.smmmousavi.clinic.ui.fragments.base.BaseDaggerFragment;
import com.developer.smmmousavi.clinic.ui.fragments.signinsignup.SignInFragment;
import com.developer.smmmousavi.clinic.util.Animations;

import androidx.annotation.StringRes;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInSignUpActivity extends BaseDaggerCompatActivity {


    @BindView(R.id.txtToolbarTitle)
    AppCompatTextView mTxtTitle;
    @BindView(R.id.imgToolbarClose)
    AppCompatImageView mImgClose;

    private BaseDaggerFragment mAttachedFragment;

    public static Intent newIntent(Context orgin) {
        Intent intent = new Intent(orgin, SignInSignUpActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_sign_up);
        ButterKnife.bind(this);
        insertFragment(SignInFragment.newInstance());
    }


    public void insertFragment(BaseDaggerFragment fragment) {
        mAttachedFragment = fragment;
        getSupportFragmentManager().beginTransaction()
            .add(R.id.flSignFragmentContianer, fragment)
            .commit();
    }

    public void repalceFramgen(BaseDaggerFragment replacmentFragment, int title) {
        mAttachedFragment = replacmentFragment;
        getSupportFragmentManager().beginTransaction()
            .setCustomAnimations(R.anim.hint_in, R.anim.fade_out)
            .replace(R.id.flSignFragmentContianer, replacmentFragment, replacmentFragment.getTag())
            .commit();
        setTitle(title);
    }


    @OnClick(R.id.imgToolbarClose)
    public void setOnCloseListener() {
        finish();
    }

    public void setTitle(@StringRes int titleId) {
        String title = getResources().getString(titleId);
        Animations.setAnimation(R.anim.hint_in, mTxtTitle);
        mTxtTitle.setText(title);
    }

}
