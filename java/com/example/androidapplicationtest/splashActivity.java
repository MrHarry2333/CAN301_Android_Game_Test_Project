package com.example.androidapplicationtest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;

/**
 * Splash Screen 2 seconds
 */
public class splashActivity extends Activity {
    private static final int sleepTime = 2000;
    private Handler sleepHandler;
    private SharedPreferences themeColorSharedPreferenceManager;

    @Override
    protected void onCreate(Bundle arg0) {
//        final View view = View.inflate(this, R.layout.activity_splash, null);
//        setContentView(view);
        globalThemeColorSelection();
        setContentView(R.layout.activity_splash);
        super.onCreate(arg0);

        // delay for 2000ms (sleepTime) before showing mainActivity
        sleepHandler = new Handler();
        sleepHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(splashActivity.this, mainActivity.class),
                        ActivityOptions.makeSceneTransitionAnimation(splashActivity.this).toBundle());
                // splashActivity.this.finish();
            }
        }, sleepTime);

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
