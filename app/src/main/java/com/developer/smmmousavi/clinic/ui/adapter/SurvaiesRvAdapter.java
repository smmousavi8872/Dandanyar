package com.developer.smmmousavi.clinic.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.smmmousavi.clinic.R;
import com.developer.smmmousavi.clinic.base.recyclerview.BaseRvAdapter;
import com.developer.smmmousavi.clinic.model.Survay;
import com.developer.smmmousavi.clinic.ui.viewholder.SurvaiesVH;

import androidx.recyclerview.widget.RecyclerView;

public class SurvaiesRvAdapter<T extends Survay> extends BaseRvAdapter<T> {

    @Override
    protected RecyclerView.ViewHolder createHeaderViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_survay, parent, false);
        return new SurvaiesVH<T>(v);
    }

    @Override
    protected RecyclerView.ViewHolder createItemViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_survay, parent, false);
        return new SurvaiesVH<T>(v);
    }

    @Override
    protected RecyclerView.ViewHolder createFooterViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_survay, parent, false);
        return new SurvaiesVH<T>(v);
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
        if (position == 0)
            return HEADER;
        else
            return ITEM;
    }
}
