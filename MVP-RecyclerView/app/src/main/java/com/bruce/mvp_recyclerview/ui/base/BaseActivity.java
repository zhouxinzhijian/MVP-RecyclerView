package com.bruce.mvp_recyclerview.ui.base;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * BaseActivity<br>
 * Created by Bruce on 2016/4/21.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private final String TAG = BaseActivity.class.getSimpleName();

//    protected void setStatusBar() {
//        StatusBarUtil.setTranslucent(this);
//    }

    protected void addFragment(@NonNull int containerViewId, @NonNull Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    protected boolean addFragmentIfNotExist(@NonNull int containerViewId, @NonNull Fragment fragment){
        Fragment existFragment = getSupportFragmentManager().findFragmentById(containerViewId);
        if(existFragment != null){
            return false;
        }
        addFragment(containerViewId, fragment);
        return true;
    }

    protected boolean isExistFragment(@NonNull int containerViewId){
        return getSupportFragmentManager().findFragmentById(containerViewId) != null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().post(new ActivityDestoryEvent());
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config=new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config,res.getDisplayMetrics() );
        return res;
    }
}
