package com.hawk.fast.demo;

/**
 * Created by 01370340 on 2018/1/17.
 */

public class UploadThread extends Thread{
    @Override
    public void run() {
        super.run();
        System.out.println("UploadThread run sleep before");
        /*try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println("UploadThread run sleep after");

    }
}
