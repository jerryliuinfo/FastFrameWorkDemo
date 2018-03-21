package com.hawk.fast.demo;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.util.Log;

import com.tesla.framework.common.util.log.Logger;
import com.tesla.framework.common.util.log.NLog;

/**
 * Created by 01370340 on 2018/1/25.
 */

public class MyApplication extends Application {
    public static final String TAG = MyApplication.class.getSimpleName();

    private static MyApplication application;
    public static final String ACTION = "com.hawk.fast.demo.ACTION_SHOW_DIALOG";
    @Override
    public void onCreate() {
        super.onCreate();

        application = this;

        Log.d(TAG, "MyApplication onCreate process name = "+ Process.myPid() +", process name = "+getCurProcessName(this));

        NLog.setDebug(true, Logger.DEBUG);
    }


    String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
    
    


    public static MyApplication getInstance(){
        return application;
    }



}
