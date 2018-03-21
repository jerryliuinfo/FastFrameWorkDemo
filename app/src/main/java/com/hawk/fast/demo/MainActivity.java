package com.hawk.fast.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //NLog.d(NLog.TAG,"app source = %s", AppUtils.getSource(this));


        findViewById(R.id.btn_take_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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


}
