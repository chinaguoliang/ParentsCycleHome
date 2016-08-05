package com.jgkj.parentscycle.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.jgkj.parentscycle.R;
import com.jgkj.parentscycle.utils.UtilTools;

import android.os.Handler;
import android.view.WindowManager;

/**
 * Created by chen on 16/7/6.
 */
public class SplashActivity extends Activity{
    //首页
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity_layout);
        WindowManager wm = this.getWindowManager();
        UtilTools.SCREEN_WIDTH = wm.getDefaultDisplay().getWidth();
        UtilTools.SCREEN_HEIGHT = wm.getDefaultDisplay().getHeight();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        }, 2000);
    }
}
