package com.bruce.mvp_recyclerview.toolbox;

import android.app.Activity;
import android.content.Context;
import android.os.Build;

/**
 * Created by Bruce on 2016/5/31.
 */
public class ContentWrapper {
    public static Context activity(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if(activity == null || activity.isDestroyed() || activity.isFinishing()){
                return RuntimeUtil.getAppContext();
            }
        } else if(activity == null || activity.isFinishing()){
            return RuntimeUtil.getAppContext();
        }
        return activity;
    }

    public static Context context(Context context){
        if(context instanceof Activity){
            return activity((Activity)context);
        }
        if(context == null){
            return RuntimeUtil.getAppContext();
        }
        return context;
    }
}
