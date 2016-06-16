package com.bruce.mvp_recyclerview.toolbox;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Bruce on 16/5/20.
 */
public class BaseReceiver extends BroadcastReceiver {
    @Override
    public final void onReceive(Context context, Intent intent) {
        onSyncReceive(context, intent);

        final Context fContext = context;
        final Intent fIntent = intent;
        SchedulerService.postAsync(new Runnable() {
            @Override
            public void run() {
                onAsyncReceive(fContext, fIntent);
            }
        });
    }

    protected void onAsyncReceive(Context context, Intent intent) {
    }

    protected void onSyncReceive(Context context, Intent intent) {
    }
}
