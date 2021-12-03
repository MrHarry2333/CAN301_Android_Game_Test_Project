package com.example.androidapplicationtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Splash Screen 2秒开屏界面
 */
public class splashActivity extends Activity {
    private static final int sleepTime = 2000;

    @Override
    protected void onCreate(Bundle arg0) {
//        final View view = View.inflate(this, R.layout.activity_splash, null);
//        setContentView(view);
        setContentView(R.layout.activity_splash);
        super.onCreate(arg0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Thread(new Runnable() {
            public void run() {
                long start = System.currentTimeMillis();
                long costTime = System.currentTimeMillis() - start;
                // 等待sleeptime时长
                if (sleepTime - costTime > 0) {
                    try {
                        Thread.sleep(sleepTime - costTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 进入主页面
                startActivity(new Intent(splashActivity.this, mainActivity.class));
                finish();
            }
        }).start();
    }
}
