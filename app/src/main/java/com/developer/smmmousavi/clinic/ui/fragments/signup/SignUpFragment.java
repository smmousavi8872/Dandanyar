package com.developer.smmmousavi.clinic.ui.fragments.signup;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.developer.smmmousavi.clinic.R;
import com.developer.smmmousavi.clinic.factory.viewmodel.ViewModelProviderFactory;
import com.developer.smmmousavi.clinic.network.bodies.UserSignUpBody;
import com.developer.smmmousavi.clinic.ui.activities.maindrawer.MainDrawerActivity;
import com.developer.smmmousavi.clinic.ui.fragments.base.BaseDaggerFragment;
import com.developer.smmmousavi.clinic.util.SharedPrefUtils;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.card.MaterialCardView;

import javax.inject.Inject;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends BaseDaggerFragment {

    public static final String TAG = "SignUpFragmentTag";

    @BindView(R.id.edtSignUpFirstName)
    AppCompatEditText mEdtFristName;
    @BindView(R.id.edtSignUpLastName)
    AppCompatEditText mEdtLastName;
    @BindView(R.id.edtSignUpUserEmail)
    AppCompatEditText mEdtUserEmail;
    @BindView(R.id.edtSignUpUserPassword)
    AppCompatEditText mEdtUserPassword;
    @BindView(R.id.chkReceiveMagazine)
    AppCompatCheckBox mChkRecieveMagazine;
    @BindView(R.id.cvSignUp)
    MaterialCardView mCvSignUp;
    @BindView(R.id.signUpLoading)
    SpinKitView mSignUpLoading;
    @BindView(R.id.imgSignUpArrow)
    AppCompatImageView mImgDoneSignUp;
    @BindView(R.id.llSingUpBtnContainer)
    LinearLayout mSignUpBtnContainer;

    private SignUpFragmentVM mViewModel;
    private AppCompatEditText[] mEditTexts;

    @Inject
    ViewModelProviderFactory mProviderFactory;

    public static SignUpFragment newInstance() {

        Bundle args = new Bundle();

        SignUpFragment fragment = new SignUpFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, v);
        mEdtUserPassword.setTransformationMethod(new PasswordTransformationMethod());
        mEditTexts = new AppCompatEditText[]{mEdtFristName, mEdtLastName, mEdtUserEmail, mEdtUserPassword};
        initViewModel();

        return v;
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this, mProviderFactory).get(SignUpFragmentVM.class);
    }

    private void subscribeObserver() {
        mViewModel.getUserMLD().observe(this, listResource -> {
            //onChange
            if (listResource != null) {
                switch (listResource.status) {
                    case LOADING:
                        break;
                    case SUCCESS:
                        new Handler().postDelayed(() -> {
                            Log.d(TAG, "subscribeObserver: cache has been refreshed.");
                            logInUser(listResource.data.getId());

                            doneWating();
                        }, 2000);

                        break;
                    case ERROR:
                        Log.e(TAG, "subscribeObserver: can not refresh the cache.");
                        Log.e(TAG, "subscribeObserver: Error message: " + listResource.message);
                        new Handler().postDelayed(() -> {
                            Toast.makeText(getContext(), R.string.sign_up_failed, Toast.LENGTH_SHORT).show();
                            doneWating();

                            activateSignUpButton(true);
                        }, 2000);
                        break;
                }
            }
        });
    }

    private void logInUser(long userId) {
        Toast.makeText(getContext(), R.string.sign_up_successful, Toast.LENGTH_SHORT).show();
        SharedPrefUtils.setSignedIn(true);
        SharedPrefUtils.setSignedInUserId(userId);
        intentMainActivity(userId);
    }

    private void intentMainActivity(long userId) {
        Intent intent = MainDrawerActivity.newIntent(getContext(), userId);
        startActivity(intent);
        getActivity().finish();
    }

    private UserSignUpBody getUserSignUpBody() {
        String firstName = mEdtFristName.getText().toString();
        String lastName = mEdtLastName.getText().toString();
        String userName = mEdtUserEmail.getText().toString();
        String passWord = mEdtUserPassword.getText().toString();
        return new UserSignUpBody(firstName, lastName, userName, passWord);
    }

    private void awaitUser() {
        activateSignUpButton(false);
        mImgDoneSignUp.setVisibility(View.GONE);
        mSignUpLoading.setVisibility(View.VISIBLE);
        mSignUpBtnContainer.setBackgroundColor(getResources().getColor(R.color.deactiveGray));
    }

    private void activateSignUpButton(boolean activate) {
        mCvSignUp.setActivated(activate);
        mCvSignUp.setClickable(activate);
        mCvSignUp.setFocusable(activate);
    }

    private void doneWating() {
        mImgDoneSignUp.setVisibility(View.VISIBLE);
        mSignUpLoading.setVisibility(View.GONE);
        mSignUpBtnContainer.setBackgroundColor(getResources().getColor(R.color.colorAccent));
    }

    @OnClick(R.id.cvSignUp)
    void setOnSignUpClickListener() {
        for (AppCompatEditText editText : mEditTexts) {
            if (editText.getText().toString().isEmpty()) {
                editText.setError(getString(R.string.neccessary_fields));
                return;
            }
        }
        awaitUser();
        mViewModel.execuateSingUpRequest(getUserSignUpBody());
        subscribeObserver();
    }

}
