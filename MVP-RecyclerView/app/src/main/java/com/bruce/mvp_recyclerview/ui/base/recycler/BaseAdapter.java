package com.bruce.mvp_recyclerview.ui.base.recycler;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Bruce on 2016/5/18.
 */
public abstract class BaseAdapter<VH extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter<VH> implements IDataController<T>{
    protected Activity mActivity;

    public void setActivity(Activity activity){
        this.mActivity = activity;
    }
    public BaseAdapter(Activity activity){
        this.mActivity = activity;
    }
    abstract T getItem(int position);
}
