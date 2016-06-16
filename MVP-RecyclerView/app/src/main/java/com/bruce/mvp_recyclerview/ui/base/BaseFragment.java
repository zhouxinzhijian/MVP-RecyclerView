package com.bruce.mvp_recyclerview.ui.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * BaseFragment
 * Created by Bruce on 2016/4/21.
 */
public abstract class BaseFragment extends Fragment {
    protected FragmentActivity mActivity;

    public BaseFragment(){
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof FragmentActivity){
            mActivity = (FragmentActivity) context;
        }
    }
}
