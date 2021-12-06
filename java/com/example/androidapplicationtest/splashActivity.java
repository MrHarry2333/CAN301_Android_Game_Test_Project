package com.example.androidapplicationtest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

/**
 * Splash Screen 2 seconds
 */
public class splashActivity extends Activity {
    private static final int sleepTime = 2000;
    private SharedPreferences themeColorSharedPreferenceManager;

    @Override
    protected void onCreate(Bundle arg0) {
//        final View view = View.inflate(this, R.layout.activity_splash, null);
//        setContentView(view);
        globalThemeColorSelection();
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
                if (sleepTime - costTime > 0) {
                    try {
                        Thread.sleep(sleepTime - costTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                startActivity(new Intent(splashActivity.this, mainActivity.class));
                finish();
            }
        }).start();
    }

    /**
     * Global Theme's Color selection
     * determined by themeColorFlag, modified by chooseThemeColor()
     */
    private void globalThemeColorSelection() {
        themeColorSharedPreferenceManager = getSharedPreferences("currentThemeColorMode",0);
        switch (themeColorSharedPreferenceManager.getInt("currentThemeColorMode", 1)) {
            case 2:
                setTheme(R.style.CustomColorTheme_red);
                break;
            case 3:
                setTheme(R.style.CustomColorTheme_green);
                break;
            case 4:
                setTheme(R.style.CustomColorTheme_yellow);
                break;
            default:
                setTheme(R.style.Theme_primaryTheme);
                break;
        }
    }
}
