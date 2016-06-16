package com.bruce.mvp_recyclerview.toolbox;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 *
 * Created by Bruce on 4/25/16.
 */
public class ToastUtil {
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());

    public static void toastShort(final String msg) {
        if (Looper.getMainLooper().equals(Looper.myLooper())) {
            Toast.makeText(RuntimeUtil.getAppContext(), msg, Toast.LENGTH_SHORT).show();
            return;
        }

        HANDLER.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RuntimeUtil.getAppContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void toastShort(final int msgId) {
        if (Looper.getMainLooper().equals(Looper.myLooper())) {
            Toast.makeText(RuntimeUtil.getAppContext(), msgId, Toast.LENGTH_SHORT).show();
            return;
        }

        HANDLER.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RuntimeUtil.getAppContext(), msgId, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void toastLong(final String msg) {
        if (Looper.getMainLooper().equals(Looper.myLooper())) {
            Toast.makeText(RuntimeUtil.getAppContext(), msg, Toast.LENGTH_LONG).show();
            return;
        }

        HANDLER.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RuntimeUtil.getAppContext(), msg, Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void toastLong(final int msgId) {
        if (Looper.getMainLooper().equals(Looper.myLooper())) {
            Toast.makeText(RuntimeUtil.getAppContext(), msgId, Toast.LENGTH_LONG).show();
            return;
        }

        HANDLER.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RuntimeUtil.getAppContext(), msgId, Toast.LENGTH_LONG).show();
            }
        });
    }
}
