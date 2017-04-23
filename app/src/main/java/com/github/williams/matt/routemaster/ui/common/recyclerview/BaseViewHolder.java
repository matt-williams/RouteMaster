package com.github.williams.matt.routemaster.ui.common.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseViewHolder<T extends RecyclerViewItem> extends RecyclerView.ViewHolder {

    private final View mItemView;

    public BaseViewHolder(final View itemView) {
        super(itemView);
        mItemView = itemView;
    }

    public View getView() {
        return mItemView;
    }
}
