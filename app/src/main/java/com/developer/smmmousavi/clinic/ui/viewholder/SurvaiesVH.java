package com.developer.smmmousavi.clinic.ui.viewholder;

import android.view.View;

import com.developer.smmmousavi.clinic.R;
import com.developer.smmmousavi.clinic.base.BaseViewHolder;
import com.developer.smmmousavi.clinic.model.Survay;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import butterknife.BindView;

public class SurvaiesVH<T extends Survay> extends BaseViewHolder<T> {

    @BindView(R.id.txtSurvayTitle)
    AppCompatTextView mTxtSurvayTitle;
    @BindView(R.id.txtSurvayDescription)
    AppCompatTextView mTxtSurvayDescription;
    @BindView(R.id.imgSurvayIcon)
    AppCompatImageView mImgSurvayIcon;

    public SurvaiesVH(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void onBind(T item) {
        mTxtSurvayTitle.setText(item.getTitle());
        mTxtSurvayDescription.setText(item.getDescription());
        //TODO: icon should recieve from server
    }

    @Override
    public void onClick(View view) {


    }
}
