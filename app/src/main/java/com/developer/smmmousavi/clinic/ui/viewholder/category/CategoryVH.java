package com.developer.smmmousavi.clinic.ui.viewholder.category;

import android.view.View;

import com.developer.smmmousavi.clinic.R;
import com.developer.smmmousavi.clinic.base.BaseViewHolder;
import com.developer.smmmousavi.clinic.model.Category;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import butterknife.BindView;

public class CategoryVH<T extends Category> extends BaseViewHolder<T> {

    @BindView(R.id.txtCategoryTitle)
    AppCompatTextView mTxtCategoryTitle;

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
    }

    @Override
    public void onClick(View view) {
        mItemClickListener.onItemClicked(mItem.getId());
    }
}
