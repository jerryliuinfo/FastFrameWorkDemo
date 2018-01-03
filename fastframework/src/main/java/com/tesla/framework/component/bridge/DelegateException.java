package com.tesla.framework.component.bridge;

/**
 * Created by jerryliu on 2017/7/11.
 */

public class DelegateException extends RuntimeException {
    private int code;
    private String msg;

    private DelegateException(){
    }
    public DelegateException(int code, String msg){
        this.code = code;
        this.msg = msg;
    }


    public DelegateException(String msg){
        this.msg = msg;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
