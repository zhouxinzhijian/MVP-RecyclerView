package com.bruce.mvp_recyclerview.ui.base.recycler;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bruce.mvp_recyclerview.R;
import com.bruce.mvp_recyclerview.toolbox.ContentWrapper;


/**
 * Created by Bruce on 2016/5/9.
 * 所有继承至该类的子类，如果是内部类都应该是static的
 */
public abstract class RecyclerViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener{
    private Activity mActivity;
    protected T mItemData;
    protected int mPosition;

    public RecyclerViewHolder(View view) {
        this(null, view);
    }

    public RecyclerViewHolder(Activity activity, View view){
        super(view);
        mActivity = activity;
    }

    protected @NonNull Context getContext(){
        return ContentWrapper.activity(mActivity);
    }

    protected @Nullable Activity getActivity(){
        return mActivity;
    }

    final void bindViewData(T t, int position){
        mItemData = t;
        mPosition = position;
        bindData(t, position);
    }

    public abstract void bindData(T t, int position);

    @Override
    public void onClick(View v) {}

    protected final <O> void setObjectTag(O itemBean, View... views){
        for(View view : views){
            setObjectTag(view, itemBean);
        }
    }
    protected final void setPositionTag(int position, View... views){
        for(View view : views){
            setPositionTag(view, position);
        }
    }
    protected final <O> void setObjectTag(View view, O itemBean){
        view.setTag(R.id.tag_item_object, itemBean);
    }
    protected final void setPositionTag(View view, int position){
        view.setTag(R.id.tag_item_position, position);
    }

    protected final <O> O getObjectByTag(View view){
        return (O)view.getTag(R.id.tag_item_object);
    }
    protected final int getPositionByTag(View view){
        return (int) view.getTag(R.id.tag_item_position);
    }
}