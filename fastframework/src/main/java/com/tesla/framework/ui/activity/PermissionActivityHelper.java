package com.tesla.framework.ui.activity;

import android.os.Bundle;

import com.tesla.framework.support.permission.IPermissionsObserver;
import com.tesla.framework.support.permission.IPermissionsSubject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 01370340 on 2017/9/23.
 * 用来处理权限相关
 */

public  class PermissionActivityHelper  implements IPermissionsSubject,IActivityHelper {

    private List<IPermissionsObserver> observerList;

    protected void onCreate(Bundle savedInstanceState) {
        observerList = new ArrayList<>();
    }

    @Override
    public void attach(IPermissionsObserver observer) {
        if (observerList != null && !observerList.contains(observer)){
            observerList.add(observer);
        }
    }

    @Override
    public void detach(IPermissionsObserver observer) {
        if (observerList != null && observerList.contains(observer)){
            observerList.remove(observer);
        }
    }


    @Override
    public void notifyActivityResult(int requestCode, String[] permissions, int[] grantResults) {
        for (IPermissionsObserver observer : observerList) {
            observer.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
