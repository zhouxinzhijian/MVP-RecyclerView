package com.cjj.loadmore;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cjj.R;

/**
 * @author cjj
 */
public class DefaultFootItem extends FootItem {
    private ProgressBar mProgressBar;
    private TextView mLoadingText;
    private TextView mEndTextView;
    private ImageView mEndIconView;
    private View mLoadingLayout;
    private View mEndLayout;

    @Override
    public View onCreateView(ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.rv_with_footer_loading, parent, false);
        mLoadingLayout = view.findViewById(R.id.rv_with_footer_loading_loading_layout);
        mEndLayout = view.findViewById(R.id.rv_with_footer_loading_end_layout);
        mProgressBar = (ProgressBar) view.findViewById(R.id.rv_with_footer_loading_progress);
        mLoadingText = (TextView) view.findViewById(R.id.rv_with_footer_loading_load);
        mEndTextView = (TextView) view.findViewById(R.id.rv_with_footer_loading_end);
        mEndIconView = (ImageView) view.findViewById(R.id.rv_with_footer_loading_end_icon);
        return view;
    }

    @Override
    public void onBindData(View view, int state) {
//        Log.i("DefaultFootItem", "state = " + state + " reslut: " + toString());
        if (state == RecyclerViewWithFooter.STATE_LOADING) {
            mLoadingLayout.setVisibility(View.VISIBLE);
            mEndLayout.setVisibility(View.GONE);
            if (TextUtils.isEmpty(loadingText)) {
                showProgressBar(view.getContext().getResources().getString(R.string.rv_with_footer_loading));
            } else {
                showProgressBar(loadingText);
            }
        } else if (state == RecyclerViewWithFooter.STATE_END) {
            mLoadingLayout.setVisibility(View.GONE);
            mEndLayout.setVisibility(View.VISIBLE);
            showEnd(endText, endIconRes);
        } else if (state == RecyclerViewWithFooter.STATE_PULL_TO_LOAD) {
            mLoadingLayout.setVisibility(View.GONE);
            mEndLayout.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(pullToLoadText)) {
                showPullToLoad(pullToLoadText);
            } else {
                showPullToLoad(loadingText);
            }
        }
    }

    public void showProgressBar(CharSequence load) {
        pullToLoadText = null;
        mProgressBar.setVisibility(View.VISIBLE);
        if (!TextUtils.isEmpty(load)) {
            mLoadingText.setText(load);
            mLoadingText.setVisibility(View.VISIBLE);
        } else {
            mLoadingText.setVisibility(View.GONE);
        }
    }

    public void showEnd(CharSequence end, @DrawableRes int resId) {
        if(resId != -1){
            mEndIconView.setImageResource(resId);
            mEndIconView.setVisibility(View.VISIBLE);
        }else{
            mEndIconView.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(end)) {
            mEndTextView.setText(end);
            mEndTextView.setVisibility(View.VISIBLE);
        }else{
            mEndTextView.setVisibility(View.GONE);
        }
    }

    public void showPullToLoad(CharSequence end) {
        mEndIconView.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(end)) {
            mEndTextView.setText(end);
            mEndTextView.setVisibility(View.VISIBLE);
        } else {
            mEndTextView.setVisibility(View.GONE);
        }
    }
}
