package com.example.androidapplicationtest;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class messageBoardActivity extends AppCompatActivity {

    private static final String TAG = "zbw";
    private static final int DATA_CAPACITY = 50;

    private SharedPreferences themeColorSharedPreferenceManager;

    private ListView mListView;
    private List<String> mList = new ArrayList<String>(DATA_CAPACITY);
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        globalThemeColorSelection();
        setContentView(R.layout.activity_message_board_main);

        mListView = (ListView) findViewById(R.id.listView);
        for (int i = 0; i < DATA_CAPACITY; i++) {
            mList.add("" + i);
        }

        mAdapter = new MyAdapter(this, mList);
        mListView.setAdapter(mAdapter);

    }

    /**
     * Global Theme's Color selection
     * determined by themeColorFlag, modified by chooseThemeColor()
     */
    private void globalThemeColorSelection() {
        themeColorSharedPreferenceManager = getSharedPreferences("currentThemeColorMode",0);
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
    }
}