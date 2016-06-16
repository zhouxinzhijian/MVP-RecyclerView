package com.bruce.mvp_recyclerview.toolbox;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * 运行时工具类
 * Created by Bruce on 4/20/16.
 */
public class RuntimeUtil {
    private static Context sAppContext;
    /**
     * 初始化应用上下文
     * @param context 应用上下文
     */
    public static void initAppContext(Context context) {
        sAppContext = context;
    }

    /**
     * @return 返回应用上下文
     */
    public static Context getAppContext() {
        return sAppContext;
    }

    private static String getProcessNameByProcessInfo() {
        if ( sAppContext == null ) {
            return "";
        }
        int pid = android.os.Process.myPid();

        ActivityManager activityMgr = (ActivityManager)sAppContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> mProcesses = activityMgr.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo item : mProcesses) {
            if (pid == item.pid) {
                return item.processName;
            }
        }
        return sAppContext.getApplicationInfo().processName;
    }

    private static String getProcessName() {
        File cmdFile = new File("/proc/self/cmdline");

        if(cmdFile.exists() && !cmdFile.isDirectory()) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(cmdFile)));
                String procName = reader.readLine();

                if(!TextUtils.isEmpty(procName)) {
                    return procName.trim();
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            finally {
                if(reader != null) {
                    try {
                        reader.close();
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return getProcessNameByProcessInfo();
    }
}
