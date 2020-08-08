package com.developer.smmmousavi.clinic.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.smmmousavi.clinic.R;
import com.developer.smmmousavi.clinic.base.recyclerview.BaseRvAdapter;
import com.developer.smmmousavi.clinic.model.Category;
import com.developer.smmmousavi.clinic.ui.viewholder.category.CategoryItemClickListener;
import com.developer.smmmousavi.clinic.ui.viewholder.category.CategoryVH;

import androidx.recyclerview.widget.RecyclerView;

public class CategoriesRvAdapter<T extends Category> extends BaseRvAdapter<T> {

    private CategoryItemClickListener mItemClickListener;

    public void setItemClickListener(CategoryItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @Override
    protected RecyclerView.ViewHolder createHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    protected RecyclerView.ViewHolder createItemViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_categories, parent, false);
        CategoryVH<T> viewHolder = new CategoryVH<>(v);
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
        ((CategoryVH<T>) viewHolder).onBind(mItemList.get(position));
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
