package com.cjj.loadmore;

import android.view.View;
import android.view.ViewGroup;

/**
 * Footer item
 *
 * @author cjj on 2016/1/30.
 */
public abstract class FootItem {

    public CharSequence loadingText;
    public CharSequence pullToLoadText;
    public CharSequence endText;
    public int endIconRes = -1;

    public abstract View onCreateView(ViewGroup parent);

    public abstract void onBindData(View view, int state);

    @Override
    public String toString() {
        return "FootItem{" +
                "loadingText=" + loadingText +
                ", pullToLoadText=" + pullToLoadText +
                ", endText=" + endText +
                ", endIconRes=" + endIconRes +
                '}';
    }
}
