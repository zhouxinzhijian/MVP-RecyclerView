package com.bruce.mvp_recyclerview.ui.base.recycler;

import java.util.List;

/**
 * Created by Bruce on 2016/5/18.
 */
public interface IDataController<T> {
    void refreshData(List<T> data);
    void addMoreData(List<T> data);
    void removeDataFromIndex(int index);
    void clearData();
}
