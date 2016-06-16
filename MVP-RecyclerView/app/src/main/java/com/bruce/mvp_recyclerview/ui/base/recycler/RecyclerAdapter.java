package com.bruce.mvp_recyclerview.ui.base.recycler;

import android.app.Activity;

import java.util.Collections;
import java.util.List;

/**
 * Created by Bruce on 2016/5/17.
 */
public abstract class RecyclerAdapter<T> extends BaseAdapter<RecyclerViewHolder<T>, T>{
    public RecyclerAdapter(Activity activity){
        super(activity);
    }

    private List<T> mList = Collections.emptyList();

    @Override
    public void refreshData(List<T> data){
        mList = data;
        notifyDataSetChanged();
    }

    @Override
    public void addMoreData(List<T> data){
        mList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void removeDataFromIndex(int index){
        mList.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public void clearData() {
        mList.clear();
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder<T> holder, int position) {
        holder.bindViewData(mList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public T getItem(int position){
        if(mList != null && position < mList.size()){
            return mList.get(position);
        }
        return null;
    }
}
