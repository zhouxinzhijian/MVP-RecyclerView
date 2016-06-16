package com.bruce.mvp_recyclerview;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.bruce.mvp_recyclerview.toolbox.RuntimeUtil;

public class BruceApplication extends Application{
    private static final String TAG = "BruceApplication";

    public BruceApplication(){
        RuntimeUtil.initAppContext(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config=new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config,res.getDisplayMetrics());
        return res;
    }
}
