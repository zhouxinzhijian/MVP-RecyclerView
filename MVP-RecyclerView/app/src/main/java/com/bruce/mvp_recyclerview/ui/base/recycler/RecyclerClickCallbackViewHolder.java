package com.bruce.mvp_recyclerview.ui.base.recycler;

import android.app.Activity;
import android.view.View;

/**
 * Created by Bruce on 2016/5/9.
 * 所有继承至该类的子类，如果是内部类，都应该是static的
 */
public abstract class RecyclerClickCallbackViewHolder<T, L extends RecyclerClickListener<?>> extends RecyclerViewHolder<T>{
    private L[] mClickListeners;
    @SafeVarargs
    public RecyclerClickCallbackViewHolder(View view, L... clickListeners) {
        this(null, view, clickListeners);
    }

    @SafeVarargs
    public RecyclerClickCallbackViewHolder(Activity activity, View view, L... clickListeners) {
        super(activity, view);
        mClickListeners = clickListeners;
    }

    public L getClickListener() {
        return getClickListener(0);
    }

    public L getClickListener(int index) {
        if(mClickListeners != null && mClickListeners.length > index){
            return mClickListeners[index];
        }
        return null;
    }
}