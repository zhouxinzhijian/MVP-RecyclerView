package com.bruce.mvp_recyclerview.toolbox;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Created by Bruce on 16/5/20.
 */
public class SchedulerService {

    private static final String BACKGROUND_THREAD_NAME = "SchedulerService_background_thread";
    private final static ExecutorService DEFAULT_EXECUTOR = Executors.newCachedThreadPool();
    private final static ExecutorService BACKGROUND_EXECUTOR = new BackgroundThreadPoolExecutor(0, Integer.MAX_VALUE,
            60L, TimeUnit.SECONDS,
            new LinkedBlockingDeque<Runnable>(),
            BACKGROUND_THREAD_NAME);

    private final static Handler MAIN_HANDLER = new Handler(Looper.getMainLooper());

    public static void postAsync(Runnable r) {
        if ( r != null ) {
            DEFAULT_EXECUTOR.execute(r);
        }
    }

    public static void postBackground(Runnable r) {
        if ( r != null ) {
            BACKGROUND_EXECUTOR.execute(r);
        }
    }

    public static void postMain(Runnable r) {
        if ( r != null ) {
            MAIN_HANDLER.post(r);
        }
    }

}
