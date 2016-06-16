package com.bruce.mvp_recyclerview.data;

import android.support.annotation.StringDef;
import com.google.gson.annotations.SerializedName;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Bruce on 2016/4/25.
 */
public class ListProtocol<T> {
    public static final String HAS_MORE = "1";
    public static final String NO_MORE = "0";

    @StringDef({HAS_MORE, NO_MORE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MoreType{}

    public @MoreType String more;
    @SerializedName(value="contents", alternate={"comments", "favorites"})//your json key
    public T list;

    @Override
    public String toString() {
        return "ListProtocol{" +
                "more='" + more + '\'' +
                ", list=" + list +
                '}';
    }

    public static boolean hasMore(String state){
        return "1".equals(state);
    }
}
