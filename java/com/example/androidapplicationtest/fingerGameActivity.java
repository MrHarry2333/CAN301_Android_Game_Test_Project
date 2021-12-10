package com.example.androidapplicationtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.io.IOException;

public class fingerGameActivity extends AppCompatActivity {
    private ImageView imageView1,imageView2,imageView3;
    private Button button1,button2,button3;
    private ImageButton pause;
    private ImageView[] imageViews;
    private Handler handler;
    private Thread thread;
    private EditText EditText1, EditText2, EditText3, EditText4;
    private MediaPlayer mediaPlayer;
    private int number[];
    // The total number of die
    private int count;
    public volatile boolean isStop = false;

    private SharedPreferences themeColorSharedPreferenceManager;
    private PopupWindow rulePopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        globalThemeColorSelection();
        setContentView(R.layout.activity_finger);
        mediaPlayer = MediaPlayer.create(this,R.raw.music);
        //循环播放
        mediaPlayer.setLooping(true);
        //找到所有控件
        number = new int[3];
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        pause = findViewById(R.id.timeout);
        EditText1 = findViewById(R.id.edit_text1);
        EditText2 = findViewById(R.id.edit_text2);
        imageViews = new ImageView[]{imageView1,imageView2,imageView3};
        //pause button
        //if (status == 0x12){
        // mediaplayer.pause();
        // status = 0x13;
        //  pause.setBackgroundResource(R.drawable.play);
        // } else if (status == 0x13){
        // mediaplayer.start();
        //status = 0x12
        // pause.setBackgroundResource(R.drawable.pause);
        // }
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果音乐播放，暂停
                if(!mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                    pause.setBackgroundResource(R.drawable.pause);
                }
                //如果音乐暂停，播放
                else {
                    mediaPlayer.pause();
                    pause.setBackgroundResource(R.drawable.play);
                }

            }

        });

        //EditText
        String inputText1 = EditText1.getText().toString();
        Toast t1 = Toast.makeText(fingerGameActivity.this, "",Toast.LENGTH_SHORT);
        t1.setText(inputText1);
        t1.show();
        String inputText2 = EditText2.getText().toString();
        Toast t2 = Toast.makeText(fingerGameActivity.this, "",Toast.LENGTH_SHORT);
        t2.setText(inputText2);
        t2.show();
        String inputText3 = EditText1.getText().toString();
        Toast t3 = Toast.makeText(fingerGameActivity.this, "",Toast.LENGTH_SHORT);
        t3.setText(inputText3);
        t3.show();
        String inputText4 = EditText2.getText().toString();
        Toast t4 = Toast.makeText(fingerGameActivity.this, "",Toast.LENGTH_SHORT);
        t4.setText(inputText4);
        t4.show();
        //Button
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (thread != null&&isStop){
                    isStop = false;
                }
                handler = new Handler(Looper.myLooper()){
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        for(int i = 0;i < 3;i++) {
                            switch (number[i]) {
                                case 1:
                                    imageViews[i].setImageResource(R.drawable.point1);
                                    break;
                                case 2:
                                    imageViews[i].setImageResource(R.drawable.point2);
                                    break;
                                case 3:
                                    imageViews[i].setImageResource(R.drawable.point3);
                                    break;
                                case 4:
                                    imageViews[i].setImageResource(R.drawable.point4);
                                    break;
                                case 5:
                                    imageViews[i].setImageResource(R.drawable.point5);
                                    break;
                                case 6:
                                    imageViews[i].setImageResource(R.drawable.point6);
                                    break;
                                case 7:
                                    imageViews[i].setImageResource(R.drawable.point7);
                                    break;
                                case 8:
                                    imageViews[i].setImageResource(R.drawable.point8);
                                    break;
                                case 9:
                                    imageViews[i].setImageResource(R.drawable.point9);
                                    break;
                            }
                        }
                    }
                };
                thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (!isStop) {
                            Message message = handler.obtainMessage();
                            //每次start前让总点数归零
                            count = 0;
                            for (int i = 0; i < 3; i++) {
                                try {
                                    Thread.sleep(50);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                int random = (int) (Math.random() * 9 + 1);
                                number[i] = random;
                                count += random;
                            }
                            handler.sendMessage(message);
                        }
                    }
                });
                thread.start();
            }
        });
        //Stop button
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                if(!isStop) {
                    isStop = true;
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Toast t = Toast.makeText(fingerGameActivity.this, "" + count, Toast.LENGTH_SHORT);
                    t.setText("Your point is: ");
                    t.show();
                }else {
                    Toast t  = Toast.makeText(fingerGameActivity.this, "", Toast.LENGTH_SHORT);
                    t.setText("Please click start button to start! ");
                    t.show();
                }
            }
        });
        //Initial die state
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isStop) {
                    for (int i = 0; i < 3; i++) {
                        imageViews[i].setImageResource(R.drawable.logo);
                    }
                }else {
                    Toast t  = Toast.makeText(fingerGameActivity.this, "", Toast.LENGTH_SHORT);
                    t.setText("Stop and restart! ");
                    t.show();
                }
            }
        });
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

    /**
     * ActionBar "showRule" button initializer
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu_in_game, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * ActionBar "showRule" button set listener
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_show_rules:
                // make a popup window
                initShowRulePopupWindow();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Construct a PopupWindow to show rule
     */
    private void initShowRulePopupWindow() {
        View v = LayoutInflater.from(fingerGameActivity.this).inflate(R.layout.popupwindow_game2_rule, null, false);
        rulePopupWindow = new PopupWindow(v, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        rulePopupWindow.setOutsideTouchable(true);
        // p.setBackgroundDrawable(new ColorDrawable(0x00000000));
        rulePopupWindow.showAsDropDown(findViewById(R.id.action_show_rules));
    }

    /**
     * Dismiss the PopupWindow for rule when Destroy current Activity
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (rulePopupWindow != null) {
            rulePopupWindow.dismiss();
        }
    }
}