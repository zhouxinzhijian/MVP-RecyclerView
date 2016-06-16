package com.bruce.mvp_recyclerview.data;

/**
 * ErrorMsg
 * Created by Bruce on 2016/4/27.
 */
public class ErrorMsg extends BaseBean {
    public String code;
    public String msg;
    public String tip;

    @Override
    public String toString() {
        return "ErrorMsg{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", tip='" + tip + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ErrorMsg errorMsg = (ErrorMsg) o;

        return (code != null ? code.equals(errorMsg.code) : errorMsg.code == null)
                && (msg != null ? msg.equals(errorMsg.msg) : errorMsg.msg == null)
                && (tip != null ? tip.equals(errorMsg.tip) : errorMsg.tip == null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (msg != null ? msg.hashCode() : 0);
        result = 31 * result + (tip != null ? tip.hashCode() : 0);
        return result;
    }
}
