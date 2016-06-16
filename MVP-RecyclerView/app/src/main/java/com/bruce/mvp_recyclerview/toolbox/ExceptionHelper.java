package com.bruce.mvp_recyclerview.toolbox;


import android.support.annotation.IntDef;

import com.bruce.mvp_recyclerview.R;
import com.bruce.mvp_recyclerview.data.ErrorMsg;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * helper class to retrieve error message<br>
 * Created by Bruce on 5/13/16.
 */
public class ExceptionHelper {
    public static final int NOT_HTTP_ERROR = -1;
    //对应HTTP的状态码
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int REQUEST_TIMEOUT = 408;
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final int BAD_GATEWAY = 502;
    public static final int SERVICE_UNAVAILABLE = 503;
    public static final int GATEWAY_TIMEOUT = 504;

    @IntDef({NOT_HTTP_ERROR, UNAUTHORIZED, FORBIDDEN, NOT_FOUND, REQUEST_TIMEOUT, INTERNAL_SERVER_ERROR, BAD_GATEWAY, SERVICE_UNAVAILABLE, GATEWAY_TIMEOUT})
    public @interface HttpError{}

    private Throwable mException;

    public ExceptionHelper(Throwable exception) {
        mException = exception;
    }

    public String getErrorTip() {
        Throwable e = mException;
        String errMsg = null;
        if (e instanceof HttpException) {             //HTTP错误
            errMsg = getHttpError((HttpException) e);
        } else if (e instanceof UnknownHostException){
            errMsg = RuntimeUtil.getAppContext().getString(R.string.network_error_tip);
        } else if (e instanceof ConnectException) {
            errMsg = RuntimeUtil.getAppContext().getString(R.string.network_error_tip);
        }
        return errMsg;
    }

    private String getHttpError(HttpException httpException) {
        Object body;
        try {
            body = httpException.response().errorBody().string();
            ErrorMsg errorMsg = new Gson().fromJson(body.toString(), ErrorMsg.class);

            if("1".equals(errorMsg.tip)){
                return errorMsg.msg;
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    private  @ExceptionHelper.HttpError int getHttpError() {
        if (HttpException.class.isInstance(mException)) {
            @HttpError int err = ((HttpException) mException).code();
            return err;
        } else {
            return NOT_HTTP_ERROR;
        }
    }
}
