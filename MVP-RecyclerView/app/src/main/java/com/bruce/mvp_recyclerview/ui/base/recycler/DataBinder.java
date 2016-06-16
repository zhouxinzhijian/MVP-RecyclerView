package com.bruce.mvp_recyclerview.ui.base.recycler;

/**
 * Created by Bruce on 2016/5/9.
 */
public class DataBinder<E extends Enum<E>, T>{
    public E viewType;
    public T data;
    public DataBinder(E viewType, T data){
        this.viewType = viewType;
        this.data = data;
    }
}