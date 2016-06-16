package com.bruce.mvp_recyclerview.data;

/**
 * BaseBean
 * Created by Bruce on 16/5/12.
 */
public abstract class BaseBean {
    public int _id; //数据库id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseBean baseBean = (BaseBean) o;

        return _id == baseBean._id;

    }

    @Override
    public int hashCode() {
        return _id;
    }
}
