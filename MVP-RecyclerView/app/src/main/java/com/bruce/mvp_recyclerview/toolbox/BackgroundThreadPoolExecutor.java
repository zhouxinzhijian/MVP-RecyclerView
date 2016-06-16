package com.bruce.mvp_recyclerview.toolbox;

import android.os.Process;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Bruce on 16/5/20.
 */
public class BackgroundThreadPoolExecutor extends ThreadPoolExecutor {

    public BackgroundThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
                                        long keepAliveTime, TimeUnit unit,
                                        BlockingQueue<Runnable> workQueue, final String threadName) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, new ThreadFactory() {

            @Override
            public Thread newThread(final Runnable r) {
                return new Thread(new Runnable() {

                    @Override
                    public void run() {
                        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
                        r.run();
                    }

                }, threadName);
            }
        });
    }
}
