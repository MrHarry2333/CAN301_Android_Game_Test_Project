package com.example.androidapplicationtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Main Activity
 */
public class mainActivity extends AppCompatActivity {

    private TextView startGameTextView;
    private TextView settingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Start Games TextView
        startGameTextView = (TextView) findViewById(R.id.start_game_main_textView);
        startGameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent jumpToSettingPage = new Intent(mainActivity.this, selectGamesActivity.class);
                startActivity(jumpToSettingPage);
            }
        });

        // Setting TextView
        settingTextView = (TextView) findViewById(R.id.settings_main_textView);
        settingTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent jumpToSettingPage = new Intent(mainActivity.this, settingActivity.class);
                startActivity(jumpToSettingPage);
            }
        });

        // Check Permissions status
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_SETTINGS,
                Manifest.permission.ACCESS_NOTIFICATION_POLICY
        }, 1);

    }

}