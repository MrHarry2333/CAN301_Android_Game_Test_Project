package com.example.androidapplicationtest;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class messageBoardActivity extends AppCompatActivity {

    private static final String TAG = "zbw";
    private static final int DATA_CAPACITY = 50;

    private ListView mListView;
    private List<String> mList = new ArrayList<String>(DATA_CAPACITY);
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_board_main);

        mListView = (ListView) findViewById(R.id.listView);
        for (int i = 0; i < DATA_CAPACITY; i++) {
            mList.add("" + i);
        }

        mAdapter = new MyAdapter(this, mList);
        mListView.setAdapter(mAdapter);

    }
}