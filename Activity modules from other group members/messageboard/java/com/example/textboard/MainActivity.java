package com.example.textboard;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "zbw";
    private static final int DATA_CAPACITY = 50;

    private ListView mListView;
    private List<String> mList = new ArrayList<String>(DATA_CAPACITY);
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);
        for (int i = 0; i < DATA_CAPACITY; i++) {
            mList.add("" + i);
        }

        mAdapter = new MyAdapter(this, mList);
        mListView.setAdapter(mAdapter);

    }
}