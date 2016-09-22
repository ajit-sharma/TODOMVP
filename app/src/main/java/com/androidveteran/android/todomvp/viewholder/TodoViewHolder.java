package com.androidveteran.android.todomvp.viewholder;

import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.androidveteran.android.todomvp.R;
import com.androidveteran.android.todomvp.base.BaseViewHolder;

import butterknife.BindView;

/**
 * Created by chint on 9/23/2016.
 */
public class TodoViewHolder extends BaseViewHolder {

    @BindView(R.id.cb_list_item)
    AppCompatCheckBox mAppCompatCheckBox;

    @BindView(R.id.tv_list_item)
    AppCompatTextView mAppCompatTextView;

    @BindView(R.id.iv_list_item)
    AppCompatImageView mAppCompatImageView;

    public TodoViewHolder(View itemView) {
        super(itemView);
    }

    public AppCompatCheckBox getmAppCompatCheckBox() {
        return mAppCompatCheckBox;
    }

    public AppCompatTextView getmAppCompatTextView() {
        return mAppCompatTextView;
    }

    public AppCompatImageView getmAppCompatImageView() {
        return mAppCompatImageView;
    }
}
