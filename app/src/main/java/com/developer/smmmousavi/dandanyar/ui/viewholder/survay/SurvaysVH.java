package com.developer.smmmousavi.dandanyar.ui.viewholder.survay;

import android.view.View;

import com.developer.smmmousavi.dandanyar.R;
import com.developer.smmmousavi.dandanyar.base.BaseViewHolder;
import com.developer.smmmousavi.dandanyar.model.Survay;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import butterknife.BindView;

public class SurvaysVH<T extends Survay> extends BaseViewHolder<T> {

    @BindView(R.id.txtSurvayTitle)
    AppCompatTextView mTxtSurvayTitle;
    @BindView(R.id.txtSurvayDescription)
    AppCompatTextView mTxtSurvayDescription;
    @BindView(R.id.imgSurvayIcon)
    AppCompatImageView mImgSurvayIcon;


    private Survay mItem;
    private SurvayItemClickListener mItemClickListener;

    public void setItemClickListener(SurvayItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public SurvaysVH(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void onBind(T item) {
        mItem = item;
        mTxtSurvayTitle.setText(item.getTitle());
        mTxtSurvayDescription.setText(item.getDescription());
        //TODO: icon should recieve from server
    }

    @Override
    public void onClick(View view) {
        mItemClickListener.onItemClicked(mItem.getId());
    }
}
