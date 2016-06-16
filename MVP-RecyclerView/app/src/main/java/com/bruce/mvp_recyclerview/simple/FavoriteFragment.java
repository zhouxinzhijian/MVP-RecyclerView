package com.bruce.mvp_recyclerview.simple;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bruce.mvp_recyclerview.R;
import com.bruce.mvp_recyclerview.data.Favorite;
import com.bruce.mvp_recyclerview.ui.base.recycler.RecyclerAdapter;
import com.bruce.mvp_recyclerview.ui.base.recycler.RecyclerFragment;
import com.bruce.mvp_recyclerview.ui.base.recycler.RecyclerViewHolder;

/**
 * Created by Bruce on 2016/5/10.
 * 收藏列表
 */
public class FavoriteFragment extends RecyclerFragment<FavoritePresenter, FavoriteFragment.FavoriteListAdapter> implements View.OnClickListener {
    private final String TAG = FavoriteFragment.class.getSimpleName();


    @Override
    public FavoritePresenter createPresenterDelegate() {
        return new FavoritePresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setNavigationOnClickListener(this);

        setAdapter(new FavoriteListAdapter(getActivity()));

        loadData();
    }

    @Override
    public void onClick(View v) {
        getActivity().finish();
    }

    public static class FavoriteListAdapter extends RecyclerAdapter<Favorite> {

        public FavoriteListAdapter(Activity activity) {
            super(activity);
        }

        @Override
        public RecyclerViewHolder<Favorite> onCreateViewHolder(ViewGroup parent, int viewType) {
            View convertView = View.inflate(mActivity, R.layout.item_favorite, null);
            return new FrvoriteViewHolder(convertView);
        }

        public static class FrvoriteViewHolder extends RecyclerViewHolder<Favorite> {
            private final TextView mContent;

            public FrvoriteViewHolder(View view) {
                super(view);
                mContent = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public void bindData(final Favorite favorite, int position) {
                mContent.setText(favorite.content);
            }

            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.content:
                        //TODO:
                        break;
                }
            }
        }
    }
}
