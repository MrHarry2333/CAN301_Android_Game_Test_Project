package com.example.androidapplicationtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
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

        // Checking Permission, if no, then an Alert Window will popup
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_SETTINGS) != PackageManager.PERMISSION_GRANTED)
            grantingPermissions();

    }

    /**
     * Grant Permissions WRITE_SETTINGS using an Alert Window
     * this project will only run on SDK > 23 (Android 8+)
     * so implementation for eariler Android build is ignored :P
     */
    private void grantingPermissions() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("System Permission Required!");
        builder.setMessage("This app may require system permissions to change screen brightness and system volumes." +
                "\n\nClick \"Go to Settings\" to enable related permissions.");
        builder.setPositiveButton("Go to Settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivityForResult(intent, 0);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}