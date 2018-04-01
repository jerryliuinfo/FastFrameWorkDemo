package com.hawk.fast.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.tesla.framework.common.util.alarm.AlarmUtil;
import com.tesla.framework.common.util.log.NLog;

/**
 * Created by loongggdroid on 2016/3/21.
 */
public class LoongggAlarmReceiver extends BroadcastReceiver {

    public static final String ALARM_ACTION = "com.loonggg.alarm.clock";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        long intervalMillis = intent.getLongExtra("intervalMillis", 0);
        NLog.d(NLog.TAG,"LoongggAlarmReceiver onReceive  intervalMillis = %s",intervalMillis);
        //说明是重复闹钟
        if (intervalMillis != 0) {
            //设置下一次闹钟
            AlarmUtil.setAlarmTime(context, System.currentTimeMillis() + intervalMillis,
                    intent);
        }
    }


}
