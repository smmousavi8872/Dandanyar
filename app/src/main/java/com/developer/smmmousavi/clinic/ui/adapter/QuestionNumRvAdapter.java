package com.developer.smmmousavi.clinic.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.smmmousavi.clinic.R;
import com.developer.smmmousavi.clinic.base.recyclerview.BaseRvAdapter;
import com.developer.smmmousavi.clinic.model.Question;
import com.developer.smmmousavi.clinic.ui.viewholder.questionnum.QuestionNumItemClickListener;
import com.developer.smmmousavi.clinic.ui.viewholder.questionnum.QuestionNumVH;

import androidx.recyclerview.widget.RecyclerView;

public class QuestionNumRvAdapter<T extends Question> extends BaseRvAdapter<T> {

    public QuestionNumItemClickListener mItemClickListener;

    public void setItemClickListener(QuestionNumItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @Override
    protected RecyclerView.ViewHolder createHeaderViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_question_num, parent, false);
        QuestionNumVH<Question> questionNumVH = new QuestionNumVH<>(v);
        questionNumVH.setItemClickListener(mItemClickListener);
        return questionNumVH;
    }

    @Override
    protected RecyclerView.ViewHolder createItemViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    protected RecyclerView.ViewHolder createFooterViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    protected void bindHeaderViewHolder(RecyclerView.ViewHolder viewHolder) {

    }

    @Override
    protected void bindItemViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

    }

    @Override
    protected void bindFooterViewHolder(RecyclerView.ViewHolder viewHolder) {

    }

    @Override
    protected void displayLoadMoreFooter() {

    }

    @Override
    protected void displayErrorFooter() {

    }

    @Override
    protected boolean hasFooter() {
        return false;
    }

    @Override
    public void addFooter() {

    }

    @Override
    public int getItemViewType(int position) {
        if (position == mItemList.size()) {
            return FOOTER;
        } else if (position == 0) {
            return HEADER;
        } else {
            return ITEM;
        }
    }
}
