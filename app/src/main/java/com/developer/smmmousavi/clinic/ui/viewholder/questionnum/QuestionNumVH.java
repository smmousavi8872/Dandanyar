package com.developer.smmmousavi.clinic.ui.viewholder.questionnum;

import android.view.View;

import com.developer.smmmousavi.clinic.base.BaseViewHolder;
import com.developer.smmmousavi.clinic.model.Question;

import androidx.annotation.NonNull;

public class QuestionNumVH<T extends Question> extends BaseViewHolder<T> {

    private QuestionNumItemClickListener mItemClickListener;
    private View mItemView;
    private Question mItem;

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
    }

    @Override
    public void onClick(View view) {

    }
}
