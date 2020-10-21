package com.developer.smmmousavi.dandanyar.ui.fragments.questions;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.developer.smmmousavi.dandanyar.R;
import com.developer.smmmousavi.dandanyar.factory.viewmodel.ViewModelProviderFactory;
import com.developer.smmmousavi.dandanyar.helper.RecyclerViewHelper;
import com.developer.smmmousavi.dandanyar.model.Question;
import com.developer.smmmousavi.dandanyar.model.QuestionNumber;
import com.developer.smmmousavi.dandanyar.network.bodies.PostQuestionBody;
import com.developer.smmmousavi.dandanyar.ui.activities.basedrawer.BaseDrawerActivity;
import com.developer.smmmousavi.dandanyar.ui.adapter.QuestionNumRvAdapter;
import com.developer.smmmousavi.dandanyar.ui.fragments.base.BaseDaggerFragment;
import com.developer.smmmousavi.dandanyar.ui.viewholder.questionnum.QuestionNumItemClickListener;
import com.developer.smmmousavi.dandanyar.util.Animations;
import com.developer.smmmousavi.dandanyar.util.SharedPrefUtils;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
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
    @BindView(R.id.btnStartOver)
    MaterialButton mBtnStartOver;
    @BindView(R.id.rvQuestionNum)
    RecyclerView mQuestionNumRv;
    @BindView(R.id.imgConnectionError)
    AppCompatImageView mConnectionError;

    private long mCategoryId;
    private QuestionsFragmentVM mViewModel;
    private Question mQuestion;
    private String mCategoryTitle = "";
    private List<QuestionNumber> mQuestionNumbers;
    private boolean mQuestionAnswer;


    @Inject
    ViewModelProviderFactory mProviderFactory;
    @Inject
    RecyclerViewHelper mRvHelper;
    @Inject
    QuestionNumRvAdapter<QuestionNumber> mQuestionNumRvAdapter;

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
        init();
        initViewModel();
        subscribeObserver();
        return v;
    }

    private void init() {
        mQuestionNumbers = new ArrayList<>();
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
                            setConnectionErrorVisiblie();
                            /*if (listResource.data != null) {
                                mQuestion = listResource.data;
                                Log.e(TAG, "subscribeObserverQ: can not refresh the cache.");
                                Log.e(TAG, "subscribeObserverQ: Error message: " + listResource.message);
                                Log.e(TAG, "subscribeObserverQ: status: ERROR, #recipes: " + listResource.data.toString());
                                setFirstQuestion();
                            } else {
                            }*/
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
                        setNextQuestion();
                        awaitUser(false);
                        break;
                    case ERROR:
                        setConnectionErrorVisiblie();
                        awaitUser(false);
                        /*if (questionResource.data != null) {
                            mQuestion = questionResource.data;
                            Log.e(TAG, "subscribeObserver2: can not refresh the cache.");
                            Log.e(TAG, "subscribeObserver2: Error message: " + questionResource.message);
                            setNextQuestion();
                        } else {
                            Log.e(TAG, "subscribeObserver2: questionResource.data = null");
                        }*/
                        break;
                }
            }
        });
        mViewModel.getCategoryMLD().observe(this, categoryResource -> {
            //onChange
            if (categoryResource != null) {
                switch (categoryResource.status) {
                    case LOADING:
                        break;
                    case SUCCESS:
                        Log.d(TAG, "titleSubscribeObserver: " + categoryResource.data.getTitle());
                        setToolbarTitle(categoryResource.data.getTitle());
                        break;
                    case ERROR:
                        break;
                }
            }
        });
        mViewModel.getCategoryById(mCategoryId).observe(this, category -> {
            if (mCategoryTitle.equals(""))
                setToolbarTitle(category.getTitle());
        });

    }

    private void setConnectionErrorVisiblie() {
        new Handler().postDelayed(() -> {
            Animations.setAnimation(R.anim.fade_out, mQuestionLoading);
            if (getView() != null)
                Snackbar.make(getView(), R.string.faild_to_connect_to_server, Snackbar.LENGTH_SHORT).show();
            mConnectionError.setVisibility(View.VISIBLE);
        }, 1000);
    }


    private void setAnswerButtonsVisibility() {
        if (mQuestion.getResTrueId() == null && mQuestion.getResFlaseId() == null) {
            mBtnNoAnswer.setVisibility(View.GONE);
            mBtnYesAnswer.setVisibility(View.GONE);
            mBtnStartOver.setVisibility(View.VISIBLE);
        } else if (mQuestion.getResFlaseId() == null && mQuestion.getResTrueId() != null) {
            mBtnNoAnswer.setVisibility(View.GONE);
            mBtnYesAnswer.setVisibility(View.VISIBLE);
        } else if (mQuestion.getResTrueId() == null && mQuestion.getResFlaseId() != null) {
            mBtnYesAnswer.setVisibility(View.GONE);
            mBtnNoAnswer.setVisibility(View.VISIBLE);
        } else {
            mBtnYesAnswer.setVisibility(View.VISIBLE);
            mBtnNoAnswer.setVisibility(View.VISIBLE);
        }
    }

    private void setStatusFlag() {
        switch (mQuestion.getStatus()) {
            case 1:
                mTxtStatusFlag.setBackgroundColor(getResources().getColor(R.color.pureWhite));
                break;
            case 2:
                mTxtStatusFlag.setText(R.string.status_unurgent);
                mTxtStatusFlag.setBackgroundColor(getResources().getColor(R.color.primaryGreen));
                break;
            case 3:
                mTxtStatusFlag.setText(R.string.status_urgent);
                mTxtStatusFlag.setBackgroundColor(getResources().getColor(R.color.primaryOrange));
                break;
            case 4:
                mTxtStatusFlag.setText(R.string.status_super_urgent);
                mTxtStatusFlag.setBackgroundColor(getResources().getColor(R.color.primaryRed));
                break;
            case 5:
                mTxtStatusFlag.setText(R.string.status_self_care);
                mTxtStatusFlag.setBackgroundColor(getResources().getColor(R.color.primaryBlue));
                break;
        }
    }

    private void setFirstQuestion() {
        mViewModel.executeGetCategoryById(mQuestion.getCategoryId());
        mTxtQuestionText.setText(mQuestion.getText());
        setStatusFlag();
        addNewQuestionNum(true, 1, -1, false);
        initQuestionNumRv(mQuestionNumbers);
        Animations.setAnimation(R.anim.fade_in, mCvQuestionContainer);
        Animations.setAnimation(R.anim.fade_out, mQuestionLoading);
        mQuestionLoading.setVisibility(View.GONE);
        mCvQuestionContainer.setVisibility(View.VISIBLE);
        setAnswerButtonsVisibility();
    }

    private void setNextQuestion() {
        mViewModel.executeGetCategoryById(mQuestion.getCategoryId());
        setStatusFlag();

        addNewQuestionNum(false, mQuestionNumbers.size() + 1, mQuestion.getId(), mQuestionAnswer);

        initQuestionNumRv(mQuestionNumbers);

        setAnswerButtonsVisibility();

        Animations.setAnimation(R.anim.hint_in, mTxtQuestionText);
        mTxtQuestionText.setText(mQuestion.getText());
    }

    private void awaitUser(boolean await) {
        if (await) {
            mBtnYesAnswer.setEnabled(false);
            mBtnNoAnswer.setEnabled(false);
            mQuestionLoading.setVisibility(View.VISIBLE);
            Animations.setAnimation(R.anim.fade_in, mQuestionLoading);

        } else {
            mBtnYesAnswer.setEnabled(true);
            mBtnNoAnswer.setEnabled(true);
            mQuestionLoading.setVisibility(View.GONE);
            Animations.setAnimation(R.anim.fade_out, mQuestionLoading);
        }
    }


    private void addNewQuestionNum(boolean first, int questionNum, long questionId, boolean answer) {
        QuestionNumber qn = new QuestionNumber();
        qn.setFirst(first);
        qn.setQuestionNum(questionNum);
        qn.setQuestionId(questionId);
        qn.setQuestionAnswer(answer);
        mQuestionNumbers.add(qn);
    }

    private void setToolbarTitle(String title) {
        if (!mCategoryTitle.equals(title)) {
            mCategoryTitle = title;
            String message = String.format("انتقال به مسیر %s", mCategoryTitle);
            showSnackbar(message);
            ((BaseDrawerActivity) getActivity()).setToolbarTitle(mCategoryTitle);
        }
    }

    private void showSnackbar(String message) {
        Snackbar snackbar = Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT);
        View snackView = snackbar.getView();
        snackView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        TextView tv = (TextView) snackView.findViewById(com.google.android.material.R.id.snackbar_text);
        tv.setTextSize(19);
        tv.setTextColor(getResources().getColor(R.color.pureWhite));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        else
            tv.setGravity(Gravity.CENTER_HORIZONTAL);
        snackbar.show();
    }

    private PostQuestionBody getPostQuestionBody(boolean userAnswer) {
        return new PostQuestionBody(
            SharedPrefUtils.getSignedInUserId(),
            mQuestion.getId(),
            userAnswer);
    }

    private void initQuestionNumRv(List<QuestionNumber> questionNumbers) {
        mQuestionNumRvAdapter.setItemClickListener(this);
        mQuestionNumRvAdapter.setItemList(questionNumbers);
        mRvHelper.buildRecyclerView(mRvHelper.getLinearLayoutManager(getContext(), RecyclerViewHelper.Orientation.HORIZONTAL, false),
            mQuestionNumRv, mQuestionNumRvAdapter);
        mQuestionNumRv.scrollToPosition(mQuestionNumRvAdapter.getItemCount() - 1);
    }

    @OnClick(R.id.btnYesAnswer)
    void setOnYesButtonClickListener() {
        awaitUser(true);
        mQuestionAnswer = true;
        PostQuestionBody body = getPostQuestionBody(true);
        Log.d(TAG, "setOnYesButtonClickListener: body -> " + body.toString());
        mViewModel.executePostQuestion(body);
    }

    @OnClick(R.id.btnNoAnswer)
    void setOnNoButtonClickListener() {
        awaitUser(true);
        mQuestionAnswer = false;
        PostQuestionBody body = getPostQuestionBody(false);
        Log.d(TAG, "setOnYesButtonClickListener: body -> " + body.toString());
        mViewModel.executePostQuestion(body);
    }

    @OnClick(R.id.btnStartOver)
    void setOnStartOverClickListener() {
        ((BaseDrawerActivity) getActivity()).replaceByQuestionsFragment(mCategoryId);
    }

    @Override
    public void onItemClicked(long questionId, boolean userAnswer) {
        //TODO: Requires getPreviousQuestion API
    }
}