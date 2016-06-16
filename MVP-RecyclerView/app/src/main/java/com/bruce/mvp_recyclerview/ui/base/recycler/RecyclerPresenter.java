package com.bruce.mvp_recyclerview.ui.base.recycler;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.bruce.mvp_recyclerview.data.ListProtocol;
import com.bruce.mvp_recyclerview.net.HttpSubscriber;
import com.bruce.mvp_recyclerview.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract recycler controller
 * Created by Bruce on 2016/5/16.
 */
public abstract class RecyclerPresenter<V extends RecyclerMvpView> extends BasePresenter<V> {
    protected static final String REFRESH_DEFAULT_REQUEST_PARAMETER = "0";
    /**
     * 初始化数据并刷新
     */
    public abstract void initDataAndRefreshRecycler();

    /**
     * 刷新数据
     */
    public abstract void refreshRecycler();

    /**
     * 加载更多
     */
    public abstract void loadMoreRecycler();


    protected final <E extends Enum<E>, T> DataBinder<E, T> covertorData(E viewType, T data){
        return new DataBinder<>(viewType, data);
    }

    @Deprecated
    protected final <E extends Enum<E>, T> List<DataBinder<E, T>> covertorListData(E viewType, List<T> lists){
        List<DataBinder<E, T>> dataBinders = new ArrayList<>();
        for(T t : lists){
            dataBinders.add(new DataBinder<>(viewType, t));
        }
        return dataBinders;
    }

    @Deprecated
    protected final <E extends Enum<E>, T> ListProtocol<List<DataBinder<E, T>>> covertorListProtocol(E viewType, ListProtocol<List<T>> listProtocal){
        ListProtocol<List<DataBinder<E, T>>> binderListProtocal = new ListProtocol<>();
        binderListProtocal.more = listProtocal.more;
        binderListProtocal.list = covertorListData(viewType, listProtocal.list);
        return binderListProtocal;
    }

    protected abstract class RefreshSubscriber<T extends List<?>> extends HttpSubscriber<T> {
        private LoadMoreStatus mLoadMoreStatus;

        protected abstract @NonNull LoadMoreStatus getLoadMoreStatus();
        protected abstract boolean onRefreshCompleted();
        protected abstract boolean onRefreshError(Throwable e);
        protected boolean onRefreshNext(T t){
            getMvpView().setAdapterList(t);
            return false;
        }

        public boolean hasMore() {
            return mLoadMoreStatus != null && mLoadMoreStatus.mHasMore;
        }

        public boolean isEmpty() {
            return mLoadMoreStatus != null && mLoadMoreStatus.mIsEmpty;
        }

        private void setLoadMoreStatus(){
            if(mLoadMoreStatus == null){
                mLoadMoreStatus = getLoadMoreStatus();
            }
        }

        @Override
        public void onCompleted() {
            setLoadMoreStatus();
            if(onRefreshCompleted()){
                return;
            }
            if(isEmpty()){
                getMvpView().showListsEmpty();
            }
            getMvpView().refreshComplte();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            setLoadMoreStatus();
            if(onRefreshError(e)){
                return;
            }
            if(isEmpty()){
                getMvpView().showError();
            }else{
                getMvpView().loadMoreEnable();//加载失败，并且列表有数据，默认按照有更多处理
            }
            getMvpView().refreshComplte();
        }

        @Override
        public void onNext(T t) {
            setLoadMoreStatus();
            if(onRefreshNext(t)){
                return;
            }
            if(hasMore()){
                getMvpView().loadMoreEnable();
            }else{
                getMvpView().loadMoreDisable();
            }
        }
    }

    protected abstract class LoadMoreSubscriber<T extends List<?>> extends HttpSubscriber<T> {
        private LoadMoreStatus mLoadMoreStatus;

        protected abstract @NonNull LoadMoreStatus getLoadMoreStatus();
        protected boolean onLoadMoreCompleted(){return false;}
        protected boolean onLoadMoreError(Throwable e){return false;}
        protected boolean onLoadMoreNext(T t){
            getMvpView().addAdapterList(t);
            return false;
        }

        public boolean hasMore() {
            return mLoadMoreStatus != null && mLoadMoreStatus.mHasMore;
        }

        private void setLoadMoreStatus(){
            if(mLoadMoreStatus == null){
                mLoadMoreStatus = getLoadMoreStatus();
            }
        }

        @Override
        public void onCompleted() {
            setLoadMoreStatus();
            if(onLoadMoreCompleted()){
                //do something
            }
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            setLoadMoreStatus();
            if(onLoadMoreError(e)){
                return;
            }
            getMvpView().loadMoreError();
        }

        @Override
        public void onNext(T t) {
            setLoadMoreStatus();
            if(onLoadMoreNext(t)){
                return;
            }
            if(hasMore()){
                getMvpView().loadMoreEnable();
            }else{
                getMvpView().loadMoreDisable();
            }
        }
    }

    public static class LoadMoreStatus{
        private boolean mHasMore;
        private boolean mIsEmpty = true; //默认是null

        public LoadMoreStatus(){}

        public LoadMoreStatus(boolean mHasMore, boolean mIsEmpty) {
            this.mHasMore = mHasMore;
            this.mIsEmpty = mIsEmpty;
        }

        public void setHasMore(boolean hasMore) {
            this.mHasMore = hasMore;
        }

        public void setHasMore(ListProtocol listProtocol) {
            this.mHasMore = ListProtocol.hasMore(listProtocol.more);
        }

        public void setIsEmpty(boolean isEmpty) {
            this.mIsEmpty = isEmpty;
        }

        public void setIsEmpty(String lastItemMarker) {
            this.mIsEmpty = TextUtils.isEmpty(lastItemMarker) || REFRESH_DEFAULT_REQUEST_PARAMETER.equals(lastItemMarker);
        }
    }
}
