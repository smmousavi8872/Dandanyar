package com.developer.smmmousavi.dandanyar.ui.viewholder.questionnum;

import android.util.Log;
import android.view.View;

import com.developer.smmmousavi.dandanyar.R;
import com.developer.smmmousavi.dandanyar.base.BaseViewHolder;
import com.developer.smmmousavi.dandanyar.model.QuestionNumber;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import butterknife.BindView;

public class QuestionNumVH<T extends QuestionNumber> extends BaseViewHolder<T> {

    private static final String TAG = "QuestionNumVH";

    @BindView(R.id.txtQuestionNum)
    AppCompatTextView mTxtQuestionNum;
    @BindView(R.id.txtQuestionNumAnswer)
    AppCompatTextView mTxtQuestionNumAnswer;
    @BindView(R.id.txtQuestionNumDash)
    AppCompatTextView mTxtQuestionDash;

    private QuestionNumItemClickListener mItemClickListener;
    private View mItemView;
    private QuestionNumber mItem;

    public QuestionNumItemClickListener getItemClickListener() {
        return mItemClickListener;
    }

    public void setItemClickListener(QuestionNumItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public QuestionNumVH(@NonNull View itemView) {
        super(itemView);
        mItemView = itemView;
    }

    @Override
    public void onBind(T item) {
        mItem = item;
        mTxtQuestionNum.setText(String.valueOf(item.getQuestionNum()));
        Log.d(TAG, "onBind: isFirst = " + mItem.getQuestionAnswer());
        mTxtQuestionNumAnswer.setText(item.getQuestionAnswer() ? "بله" : "خیر");
    }

    @Override
    public void onClick(View view) {
        mItemClickListener.onItemClicked(mItem.getQuestionId(), mItem.getQuestionAnswer());
    }
}
