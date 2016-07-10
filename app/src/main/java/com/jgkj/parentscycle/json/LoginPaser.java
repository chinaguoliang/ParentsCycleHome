package com.jgkj.parentscycle.json;

import android.util.Log;

import com.jgkj.parentscycle.bean.LoginInfo;
import com.jgkj.parentscycle.global.BgGlobal;
import com.jgkj.parentscycle.net.JsonUtil;
import com.jgkj.parentscycle.net.NetListener;
import com.jgkj.parentscycle.net.PaserJson;

import org.json.JSONException;

/**
 * Created by chen on 16/7/7.
 */
public class LoginPaser implements PaserJson {
    @Override
    public Object parseJSonObject(String response) throws JSONException {
        Log.d("result","the response code:" + response);
        LoginInfo atatol=(LoginInfo) JsonUtil.getObject(response, LoginInfo.class);
        return atatol;
    }

    @Override
    public Object getErrorBeanData() {
        LoginInfo meData = new LoginInfo();
        meData.setResult(NetListener.REQUEST_NET_ERROR_CODE);
        meData.setMsg(NetListener.REQUEST_NET_ERROR_MSG);
        return meData;
    }

    @Override
    public Object getNetNotConnectData() {
        LoginInfo meData = new LoginInfo();
        meData.setResult(NetListener.REQUEST_NET_NOT_CONNECT_CODE);
        meData.setMsg(NetListener.REQUEST_NOT_NET_ERROR_MSG);
        return meData;
    }
}