package com.soros.androidstudynotes;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hsl on 2016/12/5.
 */

abstract public class BaseViewHolder<T> extends RecyclerView.ViewHolder{

    public interface OnRecyclerItemClick {
        public void onItemClick(View view, int position);
    }

    public interface OnRecyclerItemLongClick {
        public boolean onItemLongClick(View view, int position);
    }

    private OnRecyclerItemClick mRecyclerItemClick;
    private Context mContext;

    public BaseViewHolder(@NonNull Context context, LayoutInflater layoutInflater, @LayoutRes int resId, ViewGroup viewGroup, OnRecyclerItemClick itemClick) {
        super(layoutInflater.inflate(resId, viewGroup, false));
        this.mContext = context;
        mRecyclerItemClick = itemClick;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mRecyclerItemClick == null || getAdapterPosition() == RecyclerView.NO_POSITION) {
                    return;
                }
                mRecyclerItemClick.onItemClick(view, getAdapterPosition());
            }
        });
    }

    abstract public void bindData(T t, int position);
}
