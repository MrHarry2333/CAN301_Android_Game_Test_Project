package com.example.finger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.finger.R;

import java.io.IOException;

public class Game extends AppCompatActivity {
    //声明控件
    private ImageView imageView1,imageView2,imageView3;
    private Button button1,button2,button3;
    private ImageButton pause;
    private ImageView[] imageViews;
    private Handler handler;
    private Thread thread;
    private EditText EditText1, EditText2, EditText3, EditText4;
    private MediaPlayer mediaPlayer;
    //
    private int number[];
    //The total number of die
    private int count;
    public volatile boolean isStop = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamepage);
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
        Toast.makeText(Game.this, inputText1,Toast.LENGTH_SHORT).show();
        String inputText2 = EditText2.getText().toString();
        Toast.makeText(Game.this, inputText2,Toast.LENGTH_SHORT).show();
        String inputText3 = EditText1.getText().toString();
        Toast.makeText(Game.this, inputText3,Toast.LENGTH_SHORT).show();
        String inputText4 = EditText2.getText().toString();
        Toast.makeText(Game.this, inputText4,Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(Game.this, "Your point is: " + count, Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Game.this, "Please click start button to start! ", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(Game.this, "Stop and restart! ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        }
    }
