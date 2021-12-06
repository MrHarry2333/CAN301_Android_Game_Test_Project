package com.example.androidapplicationtest;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

/**
 * Global Settings Activity
 */
public class settingActivity extends AppCompatActivity {

    private SeekBar globalBrightSeekBar;
    private Switch autoBrightSwitch;
    public boolean autoBrightEnabledFlag;
    private SeekBar globalVolumeSeekBar;
    private Switch muteSwitch;
    private Button customThemeColorBlue, customThemeColorRed, customThemeColorGreen, customThemeColorYellow;
    private SharedPreferences themeColorSharedPreferenceManager;
    private SharedPreferences.Editor themeColorSharedPreferenceManager_Editor;
    private Button staffButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        globalThemeColorSelection();
        globalBrightSeekBarManager();
        autoBrightnessSwitch();
        globalVolumeSeekBarManager();
        muteSwitch();
        chooseThemeColor();
        staffButton();
    }

    /**
     * Global Theme's Color selection
     * determined by themeColorFlag, modified by chooseThemeColor()
     * note that in settingActivity it uses Theme withActionBar (defined in themes.xml)
     */
    private void globalThemeColorSelection() {
        themeColorSharedPreferenceManager = getSharedPreferences("currentThemeColorMode",0);
        themeColorSharedPreferenceManager_Editor = themeColorSharedPreferenceManager.edit();
        switch (themeColorSharedPreferenceManager.getInt("currentThemeColorMode", 1)) {
            case 2:
                setTheme(R.style.CustomColorTheme_red_withActionBar);
                break;
            case 3:
                setTheme(R.style.CustomColorTheme_green_withActionBar);
                break;
            case 4:
                setTheme(R.style.CustomColorTheme_yellow_withActionBar);
                break;
            default:
                setTheme(R.style.Theme_primaryTheme_withActionBar);
                break;
        }

//        // Debug Msg (Delete in final product)
//        String debugToastMsg = " * Debug Msg *" +
//                " themeColorFlag: " + themeColorSharedPreferenceManager.getInt("currentThemeColorMode", 1);
//        Toast t = Toast.makeText(settingActivity.this, debugToastMsg, Toast.LENGTH_SHORT);
//        t.setGravity(Gravity.FILL_HORIZONTAL, 0, 0);
//        t.show();

        setContentView(R.layout.activity_global_settings);
    }

    /**
     * Global Bright SeekBar Setting for global_bright_Seekbar
     */
    private void globalBrightSeekBarManager() {
        globalBrightSeekBar = (SeekBar) findViewById(R.id.global_bright_Seekbar);
        globalBrightSeekBar.setMax(255);

        try {
            int currentScreenBrightness = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
            globalBrightSeekBar.setProgress(currentScreenBrightness);

            // detect system brightness mode, if is auto then uncheck the autoBrightSwitch
            autoBrightSwitch = (Switch) findViewById(R.id.auto_brightness_setting_switch);
            if (Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC == Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE)) {
                autoBrightEnabledFlag = true;
                autoBrightSwitch.setChecked(true);
                globalBrightSeekBar.setEnabled(false);
            } else {
                autoBrightEnabledFlag = false;
                autoBrightSwitch.setChecked(false);
                globalBrightSeekBar.setEnabled(true);
            }

//            // Debug Msg (Delete in final product)
//            String debugToastMsg = " * Debug Msg *" +
//                "System Current Brightness: " + currentScreenBrightness;
//            Toast t = Toast.makeText(settingActivity.this, debugToastMsg, Toast.LENGTH_LONG);
//            t.setGravity(Gravity.FILL_HORIZONTAL, 0, 0);
//            t.show();
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }

        globalBrightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekbar, int progress, boolean fromUser) {
                Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, progress);
//                WindowManager.LayoutParams lp = settingActivity.this.getWindow().getAttributes();
//                lp.screenBrightness = progress / 255f;
//                settingActivity.this.getWindow().setAttributes(lp);
            }
            public void onStartTrackingTouch(SeekBar seekbar) { }
            public void onStopTrackingTouch(SeekBar seekbar) { }
        });
    }


    /**
     * Auto Global Brightness SeekBar Setting for auto_brightness_setting_switch
     */
    private void autoBrightnessSwitch(){
        autoBrightSwitch = (Switch) findViewById(R.id.auto_brightness_setting_switch);
        autoBrightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton button, boolean b) {
                if (b) {
                    // auto bright
                    try {
                        Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
                        globalBrightSeekBar.setEnabled(false);
                        autoBrightEnabledFlag = false;
                        Toast t = Toast.makeText(settingActivity.this, "", Toast.LENGTH_SHORT);
                        t.setText("Auto Screen Brightness On");
                        t.show();
                    } catch (Exception localException) {
                        localException.printStackTrace();
                    }
                } else {
                    // manual bright
                    try {
                        Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
                        globalBrightSeekBar.setEnabled(true);
                        autoBrightEnabledFlag = true;
                        Toast t = Toast.makeText(settingActivity.this, "", Toast.LENGTH_SHORT);
                        t.setText("Manually Control Screen Brightness");
                        t.show();
                    } catch (Exception localException) {
                        localException.printStackTrace();
                    }
                }
            }
        });
    }


    /**
     * Global Volume Switch Setting for global_volume_Seekbar
     */
    private void globalVolumeSeekBarManager() {
        globalVolumeSeekBar = (SeekBar) findViewById(R.id.global_volume_Seekbar);
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxSystemVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentSystemVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        /* Debug Msg (Delete in final product)
        String debugToastMsg = " * Debug Msg *" +
                "System Max Volume: " + maxSystemVolume +
                "\nSystem Current Volume: " + currentSystemVolume;
        Toast t = Toast.makeText(settingActivity.this, debugToastMsg, Toast.LENGTH_LONG);
        t.setGravity(Gravity.FILL_HORIZONTAL, 0, 0);
        t.show();
         */

        globalVolumeSeekBar.setMax(maxSystemVolume);
        globalVolumeSeekBar.setProgress(currentSystemVolume);
    }


    /**
     * Mute Switch Setting for mute_setting_switch
     */
    private void muteSwitch() {
        muteSwitch = (Switch) findViewById(R.id.mute_setting_switch);
        muteSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    try {
                        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                        audioManager.setStreamMute(AudioManager.STREAM_MUSIC, true);
                        globalVolumeSeekBar.setEnabled(false);
                        // Toast.makeText(settingActivity.this, "Muted", Toast.LENGTH_SHORT).show();
                        Toast t = Toast.makeText(settingActivity.this, "", Toast.LENGTH_SHORT);
                        t.setText("Muted");
                        t.show();
                    } catch (Exception localException) {
                        localException.printStackTrace();
                    }
                } else {
                    try {
                        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                        audioManager.setStreamMute(AudioManager.STREAM_MUSIC, true);
                        globalVolumeSeekBar.setEnabled(true);
                        Toast t = Toast.makeText(settingActivity.this, "", Toast.LENGTH_SHORT);
                        t.setText("Unmuted");
                        t.show();
                    } catch (Exception localException) {
                        localException.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * Theme Color Choose
     */
    private void chooseThemeColor() {
        customThemeColorBlue = (Button) findViewById(R.id.theme_color_blue_selection_button);
        customThemeColorBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themeColorSharedPreferenceManager_Editor.putInt("currentThemeColorMode", 1);
                themeColorSharedPreferenceManager_Editor.commit();
                recreate();
            }
        });
        customThemeColorRed = (Button) findViewById(R.id.theme_color_red_selection_button);
        customThemeColorRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themeColorSharedPreferenceManager_Editor.putInt("currentThemeColorMode", 2);
                themeColorSharedPreferenceManager_Editor.commit();
                recreate();
            }
        });
        customThemeColorGreen = (Button) findViewById(R.id.theme_color_green_selection_button);
        customThemeColorGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themeColorSharedPreferenceManager_Editor.putInt("currentThemeColorMode", 3);
                themeColorSharedPreferenceManager_Editor.commit();
                recreate();
            }
        });
        customThemeColorYellow = (Button) findViewById(R.id.theme_color_yellow_selection_button);
        customThemeColorYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themeColorSharedPreferenceManager_Editor.putInt("currentThemeColorMode", 4);
                themeColorSharedPreferenceManager_Editor.commit();
                recreate();
            }
        });
    }

    /**
     * onBackPressed end settingActivity when back button is pressed
     * then send Intent data to mainActivity
     */
    @Override
    public void onBackPressed() {
        Intent i = new Intent(settingActivity.this, mainActivity.class);
        setResult(1, i);
        finish();
    }

    /**
     * Staff members Button for staff_button
     */
    private void staffButton() {
        staffButton = (Button) findViewById(R.id.staff_button);
        staffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent jumpToSettingPage = new Intent(settingActivity.this, staffMembersActivity.class);
                startActivity(jumpToSettingPage);
            }
        });
    }

}
