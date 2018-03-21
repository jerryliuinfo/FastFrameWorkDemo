package com.hawk.fast.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.tesla.framework.common.util.handler.HandlerUtil;
import com.tesla.framework.common.util.log.NLog;

public class MainActivity extends Activity implements HandlerUtil.MessageReceiveListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    private Handler mHander = new HandlerUtil.MyHandler(this);

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
}
