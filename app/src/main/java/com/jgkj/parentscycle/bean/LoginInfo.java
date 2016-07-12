package com.jgkj.parentscycle.bean;

import com.jgkj.parentscycle.net.NetBeanSuper;

import java.io.Serializable;

/**
 * Created by chen on 16/7/7.
 */
public class LoginInfo extends NetBeanSuper implements Serializable{
    String msg;
    boolean success;
    String phone;
    String passwd;

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
