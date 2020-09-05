package com.developer.smmmousavi.clinic.ui.fragments.signin;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.developer.smmmousavi.clinic.R;
import com.developer.smmmousavi.clinic.factory.viewmodel.ViewModelProviderFactory;
import com.developer.smmmousavi.clinic.model.User;
import com.developer.smmmousavi.clinic.network.bodies.UserSignInBody;
import com.developer.smmmousavi.clinic.network.util.Resource;
import com.developer.smmmousavi.clinic.ui.activities.maindrawer.MainDrawerActivity;
import com.developer.smmmousavi.clinic.ui.activities.signupsignin.signinsignup.SignInSignUpActivity;
import com.developer.smmmousavi.clinic.ui.fragments.base.BaseDaggerFragment;
import com.developer.smmmousavi.clinic.ui.fragments.signup.SignUpFragment;
import com.developer.smmmousavi.clinic.util.SharedPrefUtils;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;

import javax.inject.Inject;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends BaseDaggerFragment {

    public static final String TAG = "SignInFragmentTag";

    @BindView(R.id.edtSignInUserEmail)
    TextInputEditText mEdtUserEmail;
    @BindView(R.id.edtSignInUserpassword)
    TextInputEditText mEdtUserPasswrod;
    @BindView(R.id.txtForgotPassword)
    AppCompatTextView mTxtForgotPassword;
    @BindView(R.id.chkShowPassword)
    AppCompatCheckBox mChkShowPassword;
    @BindView(R.id.txtSignUpInClinic)
    AppCompatTextView mTxtSignUp;
    @BindView(R.id.cvSignIn)
    MaterialCardView mCvSignIn;
    @BindView(R.id.signInLoading)
    SpinKitView mSignInLoading;
    @BindView(R.id.imgSignInArrow)
    AppCompatImageView mImgSignInArrow;
    @BindView(R.id.llSingInBtnContainer)
    LinearLayout mSignInBtnContainer;

    private SignInFragmentVM mViewModel;
    private AppCompatEditText[] mEditTexts;

    @Inject
    ViewModelProviderFactory mProviderFactory;

    public static SignInFragment newInstance() {

        Bundle args = new Bundle();

        SignInFragment fragment = new SignInFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SignInFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sign_in, container, false);
        ButterKnife.bind(this, v);
        mEditTexts = new AppCompatEditText[]{mEdtUserEmail, mEdtUserPasswrod};
        initViewModel();
        return v;
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this, mProviderFactory).get(SignInFragmentVM.class);
    }

    private void subscribeObserver() {
        Log.d(TAG, "subscribeObserver: subscribeObserver");
        MediatorLiveData<Resource<User>> liveData = mViewModel.getUserMLD();
        liveData.observe(this, listResource -> {
            //onChange
            if (listResource != null) {
                switch (listResource.status) {
                    case LOADING:
                        break;
                    case SUCCESS:
                        new Handler().postDelayed(() -> {
                            Log.d(TAG, "subscribeObserver: cache has been refreshed.");
                            logInUser(listResource.data.getId(), listResource.data.getFirstName());
                            doneWating();
                        }, 1000);

                        break;
                    case ERROR:
                        Log.e(TAG, "subscribeObserver: can not refresh the cache.");
                        Log.e(TAG, "subscribeObserver: Error message: " + listResource.message);
                        new Handler().postDelayed(() -> {
                            Toast.makeText(getContext(), R.string.sign_in_faild +" error: " +listResource.message, Toast.LENGTH_LONG).show();
                            doneWating();
                            activateSignUpButton(true);
                        }, 1000);
                        break;
                }
            }
        });
    }

    private void logInUser(long userId, String userFirstName) {
        intentMainActivity(userId);
        SharedPrefUtils.setSignedIn(true);
        SharedPrefUtils.setSignedInUserId(userId);
        String welcome = String.format("%s  گرامی، خوش آمدید", userFirstName);
        Toast.makeText(getContext(), welcome, Toast.LENGTH_SHORT).show();
    }

    private void intentMainActivity(long signedInUser) {
        Intent intent = MainDrawerActivity.newIntent(getContext(), signedInUser);
        startActivity(intent);
        getActivity().finish();
    }

    private UserSignInBody getUserSignUpBody() {
        String userName = mEdtUserEmail.getText().toString();
        String passWord = mEdtUserPasswrod.getText().toString();
        return new UserSignInBody(userName, passWord);
    }

    private void awaitUser() {
        activateSignUpButton(false);
        mImgSignInArrow.setVisibility(View.GONE);
        mSignInLoading.setVisibility(View.VISIBLE);
        mSignInBtnContainer.setBackgroundColor(getResources().getColor(R.color.deactiveGray));
    }

    private void activateSignUpButton(boolean activate) {
        mCvSignIn.setActivated(activate);
        mCvSignIn.setClickable(activate);
        mCvSignIn.setFocusable(activate);
    }

    private void doneWating() {
        mImgSignInArrow.setVisibility(View.VISIBLE);
        mSignInLoading.setVisibility(View.GONE);
        mSignInBtnContainer.setBackgroundColor(getResources().getColor(R.color.colorAccent));
    }

    @OnClick(R.id.txtSignUpInClinic)
    public void setOnSignUpListenter(View v) {
        SignInSignUpActivity parentActivity = (SignInSignUpActivity) getActivity();
        parentActivity.repalceFramgen(SignUpFragment.newInstance(), R.string.sign_up_in);
    }

    @OnClick(R.id.chkShowPassword)
    public void setOnShowPasswordListener(View v) {
    }

    @OnClick(R.id.cvSignIn)
    void setOnSignUpClickListener() {
        for (AppCompatEditText editText : mEditTexts) {
            if (editText.getText().toString().isEmpty()) {
                editText.setError(getString(R.string.neccessary_fields));
                return;
            }
        }
        awaitUser();
        mViewModel.executeSignInRequest(getUserSignUpBody());
        subscribeObserver();
    }


}
