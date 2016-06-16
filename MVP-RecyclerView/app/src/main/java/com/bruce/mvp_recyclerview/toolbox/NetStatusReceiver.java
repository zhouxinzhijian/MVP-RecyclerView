package com.bruce.mvp_recyclerview.toolbox;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;

/**
 * @author Bruce
 */
public class NetStatusReceiver extends BaseReceiver {

    private static NetworkStatus sNetworkStatus;
    private static BehaviorSubject<NetworkStatus> sBehaviorSubject;

    public static void initNetStatus(){
        if(sNetworkStatus != null){
            return;
        }
        sBehaviorSubject = BehaviorSubject.create();
        updateStatus(RuntimeUtil.getAppContext());
    }

    @Override
    protected void onSyncReceive(Context context, Intent intent) {
        updateStatus(context);
    }

    public static void updateStatus(Context context){
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
            if(activeNetInfo != null && activeNetInfo.isAvailable()) {
                if(activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI){
                    sNetworkStatus = NetworkStatus.WIFI;
                }else if(activeNetInfo.getType() ==  ConnectivityManager.TYPE_MOBILE){
                    sNetworkStatus = NetworkStatus.MOBILE;
                }else{
                    sNetworkStatus = NetworkStatus.UNKNOWN;
                }
            } else {
                sNetworkStatus = NetworkStatus.DISABLE;
            }
            sBehaviorSubject.onNext(sNetworkStatus);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static NetworkStatus getCurrentNetworkStatus(){
        return sNetworkStatus;
    }

    public static Subscription subscribeNetworkStateChange(Action1<NetworkStatus> action1){
        return sBehaviorSubject.subscribe(action1);
    }

    public enum NetworkStatus {
        WIFI, MOBILE, DISABLE, UNKNOWN
    }
}
