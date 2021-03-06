package com.videogo.ui.devicelist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.videogo.RootActivity;
import com.videogo.widget.TitleBar;

//import com.videogo.main.CustomApplication;

/**
 * 一键配置准备界面
 * 
 * @author chengjuntao
 * @data 2014-4-9
 */
public class AutoWifiResetActivity extends RootActivity implements OnClickListener {

    private View btnNext;
    private TextView topTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        ((CustomApplication) getApplication()).addSingleActivity(AutoWifiResetActivity.class.getName(), this);
        // 页面统计
        super.onCreate(savedInstanceState);
        setContentView(com.videogo.open.R.layout.auto_wifi_reset);
        initTitleBar();
        initUI();
    }

    /**
     * 初始化标题栏
     */
    private void initTitleBar() {
        TitleBar mTitleBar = (TitleBar) findViewById(com.videogo.open.R.id.title_bar);
        mTitleBar.setTitle(com.videogo.open.R.string.device_reset_title);
        mTitleBar.addBackButton(new OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 这里对方法做描述
     * 
     * @see
     * @since V1.0
     */
    private void initUI() {
        topTip = (TextView) findViewById(com.videogo.open.R.id.topTip);
        btnNext = findViewById(com.videogo.open.R.id.btnNext);
        topTip.setText(com.videogo.open.R.string.device_reset_tip);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        int i = v.getId();
        if (i == com.videogo.open.R.id.btnNext) {
            intent = new Intent(this, AutoWifiNetConfigActivity.class);
            intent.putExtras(getIntent());
            startActivity(intent);

        } else {
        }
    }
}
