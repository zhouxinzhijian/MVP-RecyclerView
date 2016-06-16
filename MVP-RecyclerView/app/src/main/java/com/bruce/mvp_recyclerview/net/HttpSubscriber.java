package com.bruce.mvp_recyclerview.net;


import android.text.TextUtils;

import com.bruce.mvp_recyclerview.toolbox.ExceptionHelper;
import com.bruce.mvp_recyclerview.toolbox.ToastUtil;

import rx.Subscriber;

/**
 * Created by Bruce on 2016/4/27.
 * 处理异常的subscriber
 */
public abstract class HttpSubscriber<T> extends Subscriber<T> {
    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        String errorTip = new ExceptionHelper(e).getErrorTip();
        if(!TextUtils.isEmpty(errorTip)){
            ToastUtil.toastShort(errorTip);
        }
    }

    @Override
    public void onCompleted() {}

    @Override
    public void onNext(T t) {}
}
