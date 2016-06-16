package com.bruce.mvp_recyclerview.simple;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.bruce.mvp_recyclerview.R;
import com.bruce.mvp_recyclerview.ui.base.BaseActivity;

/**
 * Created by Bruce on 2016/5/10.
 */
public class FavoriteActivity extends BaseActivity {
    public static void startFavoriteActivity(Activity activity){
        activity.startActivity(new Intent(activity, FavoriteActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
    }
}
