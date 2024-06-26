package com.developer.smmmousavi.dandanyar.ui.viewholder.category;

import android.view.View;

import com.developer.smmmousavi.dandanyar.R;
import com.developer.smmmousavi.dandanyar.base.BaseViewHolder;
import com.developer.smmmousavi.dandanyar.model.Category;
import com.google.android.material.button.MaterialButton;

import androidx.annotation.NonNull;
import butterknife.BindView;

public class CategoryVH<T extends Category> extends BaseViewHolder<T> {

    @BindView(R.id.btnCategory)
    MaterialButton mBtnCategoryTitle;

    private Category mItem;
    private CategoryItemClickListener mItemClickListener;

    public void setItemClickListener(CategoryItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public CategoryVH(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void onBind(T item) {
        mItem = item;
        mBtnCategoryTitle.setText(item.getTitle());
    }

    @Override
    public void onClick(View view) {
        mItemClickListener.onItemClicked(mItem.getId());
    }
}
