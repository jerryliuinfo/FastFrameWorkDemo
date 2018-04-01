package com.hawk.fast.demo;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.tesla.framework.common.util.alarm.AlarmUtil;
import com.tesla.framework.common.util.handler.HandlerUtil;
import com.tesla.framework.common.util.log.NLog;

public class MainActivity extends Activity implements HandlerUtil.MessageReceiveListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    private Handler mHander = new HandlerUtil.MyHandler(this);

    private BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //NLog.d(NLog.TAG,"app source = %s", AppUtils.getSource(this));


        findViewById(R.id.btn_take_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHander.sendEmptyMessage(100);
            }
        });


        findViewById(R.id.btn_single_alarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long triggerAtMilles = System.currentTimeMillis() + 10 * 1000;
                long intervalMillis = 0;
                AlarmUtil.setAlarmNew(MainActivity.this,triggerAtMilles,intervalMillis, getBroadCastPendingIntent(200,intervalMillis));
            }
        });
        findViewById(R.id.btn_repeat_alarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long triggerAtMilles = System.currentTimeMillis() + 10 * 1000;
                long intervalMillis = 10*1000;

                AlarmUtil.setAlarmNew(MainActivity.this,triggerAtMilles,intervalMillis, getBroadCastPendingIntent(100,intervalMillis));
            }
        });
        findViewById(R.id.btn_service_single_alarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long triggerAtMilles = System.currentTimeMillis() + 10 * 1000;
                long intervalMillis = 0;
                AlarmUtil.setAlarmNew(MainActivity.this,triggerAtMilles,intervalMillis, getServicePendingIntent(300,intervalMillis));
            }
        });
        findViewById(R.id.btn_service_repeat_alarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long triggerAtMilles = System.currentTimeMillis() + 10 * 1000;
                long intervalMillis = 10*1000;

                AlarmUtil.setAlarmNew(MainActivity.this,triggerAtMilles,intervalMillis, getServicePendingIntent(400,intervalMillis));
            }
        });
    }

    public PendingIntent getBroadCastPendingIntent(int id, long intervalMillis){
        Intent intent = new Intent(LoongggAlarmReceiver.ALARM_ACTION);
        intent.putExtra("intervalMillis", intervalMillis);
        intent.putExtra("id", id);
        PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this, id, intent, PendingIntent
                .FLAG_CANCEL_CURRENT);
        return sender;
    }


    public PendingIntent getServicePendingIntent(int id, long intervalMillis){
        Intent intent = new Intent(AlarmService.ACTION_ALARM);
        intent.putExtra("intervalMillis", intervalMillis);
        intent.putExtra("id", id);
        PendingIntent sender = PendingIntent.getService(MainActivity.this, id, intent, PendingIntent
                .FLAG_CANCEL_CURRENT);
        return sender;
    }



    private void requestPermi(){
        /*final String imgPath = "";
        APermissionGroupAction permissionGroupAction = new APermissionGroupAction(this,null,) {
            @Override
            public void doAction() {
                super.doAction();


                NougatCompat.openCamera(MainActivity.this,imgPath,100);
            }

            @Override
            protected void onPermissionDenied(String[] permissions, int[] grantResults, boolean[] alwaysDenied) {
                super.onPermissionDenied(permissions, grantResults, alwaysDenied);

                NLog.d(NLog.TAG, "所需权限被拒绝");
            }
        };
        permissionGroupAction.run();*/

    }


    @Override
    public boolean isActivityDestroyed() {
        return isDestroyed();
    }

    @Override
    public void handleMessage(Message message) {
        NLog.d(TAG, "handleMessage what = %s", message.what);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
