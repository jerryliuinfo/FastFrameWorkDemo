package com.hawk.fast.demo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.tesla.framework.common.util.alarm.AlarmUtil;
import com.tesla.framework.common.util.log.NLog;

/**
 * Created by 01370340 on 2018/4/1.
 */

public class AlarmService extends Service {
    public static final String ACTION_ALARM = "com.hawk.fast.demo.AlarmService.ACTION_ALARM";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        NLog.d("AlarmService onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        long intervalMillis = intent.getLongExtra("intervalMillis", 0);
        NLog.d(NLog.TAG,"AlarmService onStartCommand intervalMillis = %s",intervalMillis );
        //说明是重复闹钟
        if (intervalMillis != 0) {
            //设置下一次闹钟
            AlarmUtil.setAlarmTime(getApplicationContext(), System.currentTimeMillis() + intervalMillis,
                    intent);
        }


        return super.onStartCommand(intent, flags, startId);
    }


}
