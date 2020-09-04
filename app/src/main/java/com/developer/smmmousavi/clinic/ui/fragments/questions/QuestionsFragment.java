package com.developer.smmmousavi.clinic.ui.fragments.questions;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.smmmousavi.clinic.R;
import com.developer.smmmousavi.clinic.factory.viewmodel.ViewModelProviderFactory;
import com.developer.smmmousavi.clinic.model.Question;
import com.developer.smmmousavi.clinic.network.bodies.PostQuestionBody;
import com.developer.smmmousavi.clinic.ui.activities.basedrawer.BaseDrawerActivity;
import com.developer.smmmousavi.clinic.ui.fragments.base.BaseDaggerFragment;
import com.developer.smmmousavi.clinic.ui.viewholder.questionnum.QuestionNumItemClickListener;
import com.developer.smmmousavi.clinic.util.Animations;
import com.developer.smmmousavi.clinic.util.SharedPrefUtils;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import javax.inject.Inject;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionsFragment extends BaseDaggerFragment implements QuestionNumItemClickListener {

    public static final String ARGS_CATEGORY_ID = "ArgsCateogryId";

    @BindView(R.id.txtStatusFlag)
    AppCompatTextView mTxtStatusFlag;
    @BindView(R.id.txtQuestionText)
    AppCompatTextView mTxtQuestionText;
    @BindView(R.id.cvQuestionContainer)
    MaterialCardView mCvQuestionContainer;
    @BindView(R.id.questionLoading)
    SpinKitView mQuestionLoading;
    @BindView(R.id.btnYesAnswer)
    MaterialButton mBtnYesAnswer;
    @BindView(R.id.btnNoAnswer)
    MaterialButton mBtnNoAnswer;

    private long mCategoryId;
    private QuestionsFragmentVM mViewModel;
    private Question mQuestion;
    private String mCategoryTitle = "";


    @Inject
    ViewModelProviderFactory mProviderFactory;

    public QuestionsFragment() {
        // Required empty public constructor
    }

    public static QuestionsFragment newInstance(long categoriesId) {
        QuestionsFragment fragment = new QuestionsFragment();
        Bundle args = new Bundle();
        args.putLong(ARGS_CATEGORY_ID, categoriesId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCategoryId = getArguments().getLong(ARGS_CATEGORY_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_questions, container, false);
        ButterKnife.bind(this, v);
        initViewModel();
        subscribeObserver();
        return v;
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this, mProviderFactory).get(QuestionsFragmentVM.class);
        mViewModel.executeGetFirstQuestion(mCategoryId);
    }

    private void subscribeObserver() {
        mViewModel.getFirstQuestionMLD().observe(this, listResource -> {
            //onChange
            if (listResource != null) {
                new Handler().postDelayed(() -> {
                    switch (listResource.status) {
                        case LOADING:
                            break;
                        case SUCCESS:
                            mQuestion = listResource.data;
                            Log.d(TAG, "subscribeObserverQ: userID " + SharedPrefUtils.getSignedInUserId());
                            Log.d(TAG, "subscribeObserverQ: questionId " + mQuestion.getId());
                            setFirstQuestion();
                            break;
                        case ERROR:
                            if (listResource.data != null) {
                                mQuestion = listResource.data;
                                Log.e(TAG, "subscribeObserverQ: can not refresh the cache.");
                                Log.e(TAG, "subscribeObserverQ: Error message: " + listResource.message);
                                Log.e(TAG, "subscribeObserverQ: status: ERROR, #recipes: " + listResource.data.toString());
                                setFirstQuestion();
                            } else {
                            }
                            break;
                    }
                }, 1000);
            }
        });

        mViewModel.getNextQuestionMLD().observe(this, questionResource -> {
            //onChange
            if (questionResource != null) {
                switch (questionResource.status) {
                    case LOADING:
                        break;
                    case SUCCESS:
                        mQuestion = questionResource.data;
                        Log.d(TAG, "subscribeObserver2: new question id" + mQuestion.getId());
                        setNextQuestion();
                        break;
                    case ERROR:
                        if (questionResource.data != null) {
                            mQuestion = questionResource.data;
                            Log.e(TAG, "subscribeObserver2: can not refresh the cache.");
                            Log.e(TAG, "subscribeObserver2: Error message: " + questionResource.message);
                            setNextQuestion();
                        } else {
                            Log.e(TAG, "subscribeObserver2: questionResource.data = null");
                        }
                        break;
                }
            }
        });
        mViewModel.getCategoryById(mCategoryId).observe(this, category -> {
            setToolbarTitle(category.getTitle());
        });
    }

    private void setNextQuestion() {
        Log.d(TAG, "setNextQuestion: status is: " + mQuestion.getStatus());
        setStatusFlag();
        Animations.setAnimation(R.anim.hint_in, mTxtQuestionText);
        mTxtQuestionText.setText(mQuestion.getText());
        if (mQuestion.getResFlaseId() == null)
            mBtnNoAnswer.setVisibility(View.GONE);
        if (mQuestion.getResTrueId() == null)
            mBtnNoAnswer.setVisibility(View.GONE);
    }

    private void setStatusFlag() {
        switch (mQuestion.getStatus()) {
            case 1:
                mTxtStatusFlag.setText(R.string.status_unurgent);
                mTxtStatusFlag.setBackgroundColor(getResources().getColor(R.color.primaryGreen));
                break;
            case 2:
                mTxtStatusFlag.setText(R.string.status_urgent);
                mTxtStatusFlag.setBackgroundColor(getResources().getColor(R.color.primaryOrange));
                break;
            case 3:
                mTxtStatusFlag.setText(R.string.status_super_urgent);
                mTxtStatusFlag.setBackgroundColor(getResources().getColor(R.color.primaryRed));
                break;
            case 4:
                mTxtStatusFlag.setText(R.string.status_self_care);
                mTxtStatusFlag.setBackgroundColor(getResources().getColor(R.color.primaryBlue));
                break;
        }
    }

    private void setFirstQuestion() {
        mTxtQuestionText.setText(mQuestion.getText());
        setStatusFlag();
        Animations.setAnimation(R.anim.fade_in, mCvQuestionContainer);
        Animations.setAnimation(R.anim.fade_out, mQuestionLoading);
        mQuestionLoading.setVisibility(View.GONE);
        mCvQuestionContainer.setVisibility(View.VISIBLE);
    }

    private void setToolbarTitle(String title) {
        if (!mCategoryTitle.equals(title)) {
            mCategoryTitle = title;
            ((BaseDrawerActivity) getActivity()).setToolbarTitle(mCategoryTitle);
        }
    }

    private PostQuestionBody getPostQuestionBody(boolean userAnswer) {
        return new PostQuestionBody(
            SharedPrefUtils.getSignedInUserId(),
            mQuestion.getId(),
            userAnswer);
    }

    @OnClick(R.id.btnYesAnswer)
    void setOnYesButtonClickListener() {
        PostQuestionBody body = getPostQuestionBody(true);
        Log.d(TAG, "setOnYesButtonClickListener: body -> " + body.toString());
        mViewModel.executePostQuestion(body);
    }

    @OnClick(R.id.btnNoAnswer)
    void setOnNoButtonClickListener() {
        PostQuestionBody body = getPostQuestionBody(false);
        Log.d(TAG, "setOnYesButtonClickListener: body -> " + body.toString());
        mViewModel.executePostQuestion(body);

    }

    @Override
    public void onItemClicked(long questionId, boolean userAnswer) {

    }
}