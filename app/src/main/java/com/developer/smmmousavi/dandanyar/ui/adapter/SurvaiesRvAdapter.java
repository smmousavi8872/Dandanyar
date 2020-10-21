package com.developer.smmmousavi.dandanyar.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.smmmousavi.dandanyar.R;
import com.developer.smmmousavi.dandanyar.base.recyclerview.BaseRvAdapter;
import com.developer.smmmousavi.dandanyar.model.Survay;
import com.developer.smmmousavi.dandanyar.ui.viewholder.survay.SurvayItemClickListener;
import com.developer.smmmousavi.dandanyar.ui.viewholder.survay.SurvaysVH;

import androidx.recyclerview.widget.RecyclerView;

public class SurvaiesRvAdapter<T extends Survay> extends BaseRvAdapter<T> {

    private SurvayItemClickListener mItemClickListener;

    public void setSurvayItemClickListener(SurvayItemClickListener listener) {
        mItemClickListener = listener;
    }

    @Override
    protected RecyclerView.ViewHolder createHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    protected RecyclerView.ViewHolder createItemViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_survay, parent, false);
        SurvaysVH<T> viewHolder = new SurvaysVH<>(v);
        viewHolder.setItemClickListener(mItemClickListener);
        return viewHolder;
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
        ((SurvaysVH<T>) viewHolder).onBind(mItemList.get(position));

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
        return ITEM;
    }
}
