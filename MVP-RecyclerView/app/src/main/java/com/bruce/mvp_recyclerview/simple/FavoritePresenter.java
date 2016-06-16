package com.bruce.mvp_recyclerview.simple;


import android.os.SystemClock;

import com.bruce.mvp_recyclerview.data.Favorite;
import com.bruce.mvp_recyclerview.data.ListProtocol;
import com.bruce.mvp_recyclerview.ui.base.recycler.RecyclerMvpView;
import com.bruce.mvp_recyclerview.ui.base.recycler.RecyclerPresenter;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Bruce on 2016/4/25.
 */
public class FavoritePresenter extends RecyclerPresenter<RecyclerMvpView> {
    private final String TAG = FavoritePresenter.class.getSimpleName();
    private Subscription mSubscription;
    private String mFavoriteLastId = REFRESH_DEFAULT_REQUEST_PARAMETER;

    @Override
    public void detachView() {
        super.detachView();
        unsubscriber();
    }

    void unsubscriber() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) mSubscription.unsubscribe();
    }

    @Override
    public void initDataAndRefreshRecycler() {
        //TODO:you can load local data.
        refreshRecycler();//load network data
    }

    @Override
    public void refreshRecycler() {
        checkViewAttached();
        final LoadMoreStatus loadMoreStatus = new LoadMoreStatus();
        mSubscription = getFavorites(REFRESH_DEFAULT_REQUEST_PARAMETER, loadMoreStatus)
                .subscribe(new RefreshSubscriber<List<Favorite>>() {
                    @Override
                    protected LoadMoreStatus getLoadMoreStatus() {
                        return loadMoreStatus;
                    }
                    @Override
                    protected boolean onRefreshCompleted() {
                        return false;
                    }
                    @Override
                    protected boolean onRefreshError(Throwable e) {
                        return false;
                    }
                });
    }

    @Override
    public void loadMoreRecycler() {
        checkViewAttached();
        final LoadMoreStatus loadMoreStatus = new LoadMoreStatus();
        mSubscription = getFavorites(mFavoriteLastId, loadMoreStatus)
                .subscribe(new LoadMoreSubscriber<List<Favorite>>() {
                    @Override
                    protected LoadMoreStatus getLoadMoreStatus() {
                        return loadMoreStatus;
                    }
                });
    }

    private Observable<List<Favorite>> getFavorites(String favoriteLastId, final LoadMoreStatus loadMoreStatus){
        return
                //TODO:get your network data
                Observable.create(new Observable.OnSubscribe<ListProtocol<List<Favorite>>>() {
                    @Override
                    public void call(Subscriber<? super ListProtocol<List<Favorite>>> subscriber) {
                        ListProtocol<List<Favorite>> listListProtocol = new ListProtocol<>();
                        listListProtocol.more = ListProtocol.HAS_MORE; //or ListProtocol.NO_MORE
                        listListProtocol.list = new ArrayList<>();
                        //TODO:test
                        for(int i = 0; i< 20; i++){
                            Favorite favorite = new Favorite();
                            favorite.favoriteId = String.valueOf(i);
                            favorite.content = "My name is Bruce " + i;
                            listListProtocol.list.add(favorite);
                        }
                        subscriber.onNext(listListProtocol);
                        subscriber.onCompleted();
                    }
                })
                .doOnNext(new Action1<ListProtocol<List<Favorite>>>() {
                    @Override
                    public void call(ListProtocol<List<Favorite>> listListProtocol) {
                        SystemClock.sleep(2000);
                    }
                })
                //TODO:get your network data
                .flatMap(new Func1<ListProtocol<List<Favorite>>, Observable<Favorite>>() {
                    @Override
                    public Observable<Favorite> call(ListProtocol<List<Favorite>> listListProtocol) {
                        List<Favorite> favorites = listListProtocol.list;
                        if (favorites.size() > 0) {
                            mFavoriteLastId = favorites.get(favorites.size() - 1).favoriteId;
                        }else{
                            mFavoriteLastId = REFRESH_DEFAULT_REQUEST_PARAMETER;
                        }
                        loadMoreStatus.setIsEmpty(mFavoriteLastId);
                        loadMoreStatus.setHasMore(listListProtocol);
                        return Observable.from(favorites);
                    }
                })
                .doOnNext(new Action1<Favorite>() {
                    @Override
                    public void call(Favorite favorite) {
                        //TODO:do something
                    }
                })
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}