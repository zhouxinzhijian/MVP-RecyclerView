package com.bruce.mvp_recyclerview.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Bruce on 2016/5/10.
 * 收藏
 */
public class Favorite extends BaseBean implements Parcelable{
    public String favoriteId;
    public String content;
    //TODO: other

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.favoriteId);
        dest.writeString(this.content);
    }

    public Favorite() {
    }

    protected Favorite(Parcel in) {
        this.favoriteId = in.readString();
        this.content = in.readString();
    }

    public static final Creator<Favorite> CREATOR = new Creator<Favorite>() {
        @Override
        public Favorite createFromParcel(Parcel source) {
            return new Favorite(source);
        }

        @Override
        public Favorite[] newArray(int size) {
            return new Favorite[size];
        }
    };
}
