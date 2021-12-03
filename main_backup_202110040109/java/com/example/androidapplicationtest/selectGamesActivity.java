package com.example.androidapplicationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class selectGamesActivity extends AppCompatActivity {
    private Button game1;
    private Button game2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_games);

        game1ButtonManager();
        game2ButtonManager();

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