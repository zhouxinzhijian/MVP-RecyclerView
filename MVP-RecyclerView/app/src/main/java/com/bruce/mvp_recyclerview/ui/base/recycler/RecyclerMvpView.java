package com.bruce.mvp_recyclerview.ui.base.recycler;

import com.bruce.mvp_recyclerview.ui.base.MvpView;

import java.util.List;

/**
 * Created by Bruce on 2016/4/25.
 */
public interface RecyclerMvpView extends MvpView {
    /**显示刷新progress*/
    void showRefreshIndicator();

    /**刷新完成*/
    void refreshComplte();

    /**可以加载更多*/
    void loadMoreEnable();

    /**不能加载更多*/
    void loadMoreDisable();

    /**加载更多失败*/
    void loadMoreError();

    /**刷新列表数据*/
    void setAdapterList(List<?> list);

    /**加载更多列表数据*/
    void addAdapterList(List<?> list);

    void clearAdapterList();

    /**没有数据*/
    void showListsEmpty();

    /**刷新失败*/
    void showError();
}
