package com.bruce.mvp_recyclerview.ui.base.recycler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.bruce.mvp_recyclerview.R;
import com.bruce.mvp_recyclerview.toolbox.NetStatusReceiver;
import com.bruce.mvp_recyclerview.ui.base.BaseFragment;
import com.cjj.loadmore.OnLoadMoreListener;
import com.cjj.loadmore.RecyclerViewWithFooter;

import java.util.List;

/**
 * RecyclerFragment
 * Created by Bruce on 2016/5/16.
 */
public abstract class RecyclerFragment<P extends RecyclerPresenter, A extends BaseAdapter> extends BaseFragment implements RecyclerMvpView {
    protected P mRecyclerPresenter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    protected RecyclerViewWithFooter mRecyclerView;
    protected A mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecyclerPresenter = createPresenterDelegate();
        mRecyclerPresenter.attachView(this);
    }

    public abstract P createPresenterDelegate();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        mRecyclerView = (RecyclerViewWithFooter) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRecyclerPresenter.refreshRecycler();
            }
        });

        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mRecyclerPresenter.loadMoreRecycler();
            }
        });
    }

    public void setAdapter(A adapter){
        mAdapter = adapter;
        mRecyclerView.setAdapter(mAdapter);
    }

    public void loadData(){
        if (!mSwipeRefreshLayout.isRefreshing()) {
            showRefreshIndicator();
            mRecyclerPresenter.initDataAndRefreshRecycler();
        }
    }

    @Override
    public void showRefreshIndicator() {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void refreshComplte() {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void loadMoreEnable() {
        mRecyclerView.setLoading();
    }

    @Override
    public void loadMoreDisable() {
        mRecyclerView.setEnd(getString(R.string.list_no_more_txt));
    }

    @Override
    public void loadMoreError() {
        if(NetStatusReceiver.getCurrentNetworkStatus() == NetStatusReceiver.NetworkStatus.DISABLE){
            mRecyclerView.setPullToLoad(getString(R.string.list_load_more_fail_no_network));
        }else{
            mRecyclerView.setPullToLoad(getString(R.string.list_load_more_fail));
        }
    }

    @Override
    public void setAdapterList(List<?> list) {
        mAdapter.refreshData(list);
    }

    @Override
    public void addAdapterList(List<?> list) {
        mAdapter.addMoreData(list);
    }

    @Override
    public void clearAdapterList() {
        mAdapter.clearData();
    }

    @Override
    public void showListsEmpty() {
        if(mAdapter.getItemCount() == 0){
            if(NetStatusReceiver.getCurrentNetworkStatus() == NetStatusReceiver.NetworkStatus.DISABLE){
                mRecyclerView.setEmpty(getString(R.string.msg_no_network_refresh_retry_prompt), R.drawable.icon_no_network);
            }else{
                showListsEmptyNetworkEnable();
            }
        }
    }

    /**
     * 有网络情况下的提示文案
     */
    protected void showListsEmptyNetworkEnable(){
        mRecyclerView.setEmpty(getString(R.string.list_is_empty_text_prompt), R.drawable.icon_list_empty_prompt);
    }

    @Override
    public void showError() {
        if(mAdapter.getItemCount() == 0){
            if(NetStatusReceiver.getCurrentNetworkStatus() == NetStatusReceiver.NetworkStatus.DISABLE){
                mRecyclerView.setEmpty(getString(R.string.msg_no_network_refresh_retry_prompt), R.drawable.icon_no_network);
            }else{
                mRecyclerView.setEmpty(getString(R.string.list_load_fail), R.drawable.icon_list_empty_prompt);
            }
        }
    }
}
