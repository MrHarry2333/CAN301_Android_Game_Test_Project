package com.example.androidapplicationtest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Main Activity
 */
public class mainActivity extends AppCompatActivity {

    private TextView startGameTextView;
    private TextView settingTextView;
    private SharedPreferences themeColorSharedPreferenceManager;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        globalThemeColorSelection();
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
                startActivityForResult(jumpToSettingPage, 1);
            }
        });

        // Checking Permission, if no, then an Alert Window will popup
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(!Settings.System.canWrite(this))
                grantingPermissions();
        }

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

    /**
     * onActivityResult for getting result code from settingActivity
     * this result code is to notify mainActivity to change theme or not.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent returnData) {
        super.onActivityResult(requestCode, resultCode, returnData);
        if (requestCode == 1) {
            if (resultCode == 1) {
                recreate();
            }
        }
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