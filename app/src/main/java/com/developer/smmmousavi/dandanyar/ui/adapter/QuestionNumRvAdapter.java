package com.developer.smmmousavi.dandanyar.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.smmmousavi.dandanyar.R;
import com.developer.smmmousavi.dandanyar.base.recyclerview.BaseRvAdapter;
import com.developer.smmmousavi.dandanyar.model.QuestionNumber;
import com.developer.smmmousavi.dandanyar.ui.viewholder.questionnum.QuestionNumItemClickListener;
import com.developer.smmmousavi.dandanyar.ui.viewholder.questionnum.QuestionNumVH;
import com.developer.smmmousavi.dandanyar.ui.viewholder.questionnum.di.FirstQuestionNumVH;

import androidx.recyclerview.widget.RecyclerView;

public class QuestionNumRvAdapter<T extends QuestionNumber> extends BaseRvAdapter<T> {

    private static final String TAG = "QuestionNumRvAdapter";

    public QuestionNumItemClickListener mItemClickListener;

    public void setItemClickListener(QuestionNumItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @Override
    protected RecyclerView.ViewHolder createHeaderViewHolder(ViewGroup parent) {
        Log.d(TAG, "createItemViewHolder: (HEADER) list size = " + mItemList.size());
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_question_num_first, parent, false);
        FirstQuestionNumVH<QuestionNumber> questionNumVH = new FirstQuestionNumVH<>(v);
        questionNumVH.setItemClickListener(mItemClickListener);
        return questionNumVH;
    }

    @Override
    protected RecyclerView.ViewHolder createItemViewHolder(ViewGroup parent) {
        Log.d(TAG, "createItemViewHolder: (ITEM) list size = " + mItemList.size());
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_question_num, parent, false);
        QuestionNumVH<QuestionNumber> questionNumVH = new QuestionNumVH<>(v);
        questionNumVH.setItemClickListener(mItemClickListener);
        return questionNumVH;
    }

    @Override
    protected RecyclerView.ViewHolder createFooterViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    protected void bindHeaderViewHolder(RecyclerView.ViewHolder viewHolder) {
        ((FirstQuestionNumVH<QuestionNumber>) viewHolder).onBind(mItemList.get(0));
    }

    @Override
    protected void bindItemViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ((QuestionNumVH<QuestionNumber>) viewHolder).onBind(mItemList.get(position));
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
        if (mItemList.get(position).isFirst()) {
            Log.d(TAG, "getItemViewType: HEADER, position = " + position);
            return HEADER;
        } else {
            Log.d(TAG, "getItemViewType: ITEM");
            return ITEM;
        }
    }
}
