package com.example.androidapplicationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class selectGamesActivity extends AppCompatActivity {
    private Button game1;
    private Button game2;
    private SharedPreferences themeColorSharedPreferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        globalThemeColorSelection();
        setContentView(R.layout.activity_select_games);

        game1ButtonManager();
        game2ButtonManager();

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
                setTheme(R.style.Theme_AndroidApplicationTest);
                break;
        }
    }

    /**
     * Game 1 Button Listener
     */
    private void game1ButtonManager() {
        game1 = (Button) findViewById(R.id.select_game_1_button);
        game1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent jumpToSettingPage = new Intent(selectGamesActivity.this, guessGameActivity.class);
                startActivity(jumpToSettingPage);
            }
        });
    }

    /**
     * Game 2 Button Listener
     */
    private void game2ButtonManager() {
        game2 = (Button) findViewById(R.id.select_game_2_button);
        game2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent jumpToSettingPage = new Intent(selectGamesActivity.this, staffMembersActivity.class);
                startActivity(jumpToSettingPage);
            }
        });
    }
}