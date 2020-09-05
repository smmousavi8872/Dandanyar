package com.developer.smmmousavi.clinic.ui.viewholder.questionnum.di;

import android.view.View;

import com.developer.smmmousavi.clinic.base.BaseViewHolder;
import com.developer.smmmousavi.clinic.model.QuestionNumber;
import com.developer.smmmousavi.clinic.ui.viewholder.questionnum.QuestionNumItemClickListener;

import androidx.annotation.NonNull;

public class FirstQuestionNumVH<T extends QuestionNumber> extends BaseViewHolder<T> {


    private QuestionNumItemClickListener mItemClickListener;
    private View mItemView;
    private QuestionNumber mItem;

    public QuestionNumItemClickListener getItemClickListener() {
        return mItemClickListener;
    }

    public void setItemClickListener(QuestionNumItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public FirstQuestionNumVH(@NonNull View itemView) {
        super(itemView);
        mItemView = itemView;
    }

    @Override
    public void onBind(T item) {
        mItem = item;
    }

    @Override
    public void onClick(View view) {
        mItemClickListener.onItemClicked(mItem.getQuestionId(), mItem.getQuestionAnswer());
    }
}
