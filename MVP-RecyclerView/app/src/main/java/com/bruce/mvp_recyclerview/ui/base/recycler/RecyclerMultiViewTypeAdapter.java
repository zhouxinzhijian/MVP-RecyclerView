package com.bruce.mvp_recyclerview.ui.base.recycler;

import android.app.Activity;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

/**
 * Created by Bruce on 2016/5/17.
 */
public abstract class RecyclerMultiViewTypeAdapter<E extends Enum<E>, D extends DataBinder<E, ?>> extends BaseAdapter<RecyclerViewHolder<?>, D>{
    public RecyclerMultiViewTypeAdapter(Activity activity){
        super(activity);
    }

    private List<D> mList = Collections.emptyList();

    public void insertDataToIndex(int index, D data){
        mList.add(index, data);
        notifyItemInserted(index);
    }

    public void removeDataFromIndex(int index){
        mList.remove(index);
        notifyItemRemoved(index);
    }

    public void refreshData(List<D> data){
        mList = data;
        notifyDataSetChanged();
    }
    public void addMoreData(List<D> data){
        mList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void clearData() {
        mList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).viewType.ordinal();
    }

    @Override
    public RecyclerViewHolder<?> onCreateViewHolder(ViewGroup parent, int viewType) {
        return createViewHolder(parent, getEnumFromViewType(viewType));
    }

    public abstract RecyclerViewHolder<?> createViewHolder(ViewGroup parent, E viewType);

    public abstract E getEnumFromViewType(int ordinal);

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.bindViewData(mList.get(position).data, position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public D getItem(int position){
        if(mList != null && position < mList.size()){
            return mList.get(position);
        }
        return null;
    }
}
