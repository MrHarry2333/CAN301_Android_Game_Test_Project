package com.example.guessgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;





public class MainActivity extends AppCompatActivity {
    ImageView cutSmallTop,stoneSmallTop,buSmallTop,cutSmallBottom,stoneSmallBottom,buSmallBottom,cutBigTop,stoneBigTop,buBigTop,cutBigBottom,stoneBigBottom,buBigBottom,winTop,winBottom,restartBottom,playBottom,musicStop;
    ImageButton playBtn;
    int chooseNum=0;
    int chooseCutTop=0;
    int chooseStoneTop=0;
    int chooseBuTop=0;
    int chooseCutBottom=0;
    int chooseStoneBottom=0;
    int chooseBuBottom=0;
    int chooseOnce=0;
    int chooseSecondTime=0;
    int finalResult=0;

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViewComponent();
        startGame();
        mp = MediaPlayer.create(this, R.raw.music1);
        mp.setLooping(true);
        playBtn = (ImageButton) findViewById(R.id.musicPlayButton);
        mp.start();
        

    }



    public void playBtnClick(View view) {
        if(!mp.isPlaying()){
            mp.start();
            playBtn.setBackgroundResource(R.mipmap.playmusic);
        } else {
            mp.pause();
            playBtn.setBackgroundResource(R.mipmap.musicpause);
        }
    }


    public void setViewComponent(){
        //初始化所有控件

        cutSmallTop = findViewById(R.id.cut);
        stoneSmallTop = findViewById(R.id.stone);
        buSmallTop=findViewById(R.id.bu);
        cutSmallBottom=findViewById(R.id.cutBottom);
        stoneSmallBottom=findViewById(R.id.stoneBottom);
        buSmallBottom=findViewById(R.id.buBottom);

        cutBigTop=findViewById(R.id.cutBig);
        stoneBigTop=findViewById(R.id.stoneBig);
        buBigTop=findViewById(R.id.buBig);
        cutBigBottom=findViewById(R.id.cutBigBottom);
        stoneBigBottom=findViewById(R.id.stoneBigBottom);
        buBigBottom=findViewById(R.id.buBigBottom);

        winTop=findViewById(R.id.winTop);
        winBottom=findViewById(R.id.winBottom);
        restartBottom=findViewById(R.id.restart);
        playBottom=findViewById(R.id.play);

        //musicStop

        //设置初始的大石头剪刀布和胜利图标不可见

        cutBigBottom.setVisibility(View.GONE);

        cutBigTop.setVisibility(View.GONE);
        stoneBigTop.setVisibility(View.GONE);
        stoneBigBottom.setVisibility(View.GONE);
        buBigTop.setVisibility(View.GONE);
        buBigBottom.setVisibility(View.GONE);

        winTop.setVisibility(View.GONE);
        winBottom.setVisibility(View.GONE);




        //设置点击响应


                cutSmallTop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        chooseCutTop = 1;
                        chooseNum++;
                        if (chooseOnce == 1) {
                            chooseCutTop = 2;
                            chooseSecondTime++;
                        }
                    }
                });
        stoneSmallTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseStoneTop=1;
                chooseNum++;
                if(chooseOnce==1){
                    chooseStoneTop=2;
                    chooseSecondTime++;
                }
            }
        });
        buSmallTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseBuTop=1;
                chooseNum++;
                if(chooseOnce==1){
                    chooseBuTop=2;
                    chooseSecondTime++;
                }
            }
        });
        cutSmallBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseCutBottom=1;
                chooseNum++;
                if(chooseOnce==1){
                    chooseCutBottom=2;
                    chooseSecondTime++;
                }
            }
        });
        stoneSmallBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseStoneBottom=1;
                chooseNum++;
                if(chooseOnce==1){
                    chooseStoneBottom=2;
                    chooseSecondTime++;
                }

            }
        });
        buSmallBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseBuBottom=1;
                chooseNum++;
                if(chooseOnce==1){
                    chooseBuBottom=2;
                    chooseSecondTime++;
                }
            }
        });

        //重启

        restartBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshInt();
            }
        });



        //点击play

        playBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示第一次的2次猜拳
                if(chooseOnce==0){
                    if(chooseNum==4){
                        startGame();
                        chooseOnce=1;
                    }
                }
                //显示最终结果
                if(chooseOnce==1&&chooseSecondTime==2){
                    if(chooseCutTop==2){
                        cutBigTop.setVisibility(View.GONE);
                    }
                    if(chooseStoneTop==2){
                        stoneBigTop.setVisibility(View.GONE);
                    }
                    if(chooseBuTop==2){
                        buBigTop.setVisibility(View.GONE);
                    }
                    if(chooseCutBottom==2){
                        cutBigBottom.setVisibility(View.GONE);
                    }
                    if(chooseStoneBottom==2){
                        stoneBigBottom.setVisibility(View.GONE);
                    }
                    if(chooseBuBottom==2){
                        buBigBottom.setVisibility(View.GONE);
                    }
                    finalResult=1;

                }

                //显示胜利
                if(finalResult==1){
                    if(chooseCutTop==1){
                        if(chooseCutBottom==1){

                        }
                        if(chooseStoneBottom==1){
                            winBottom.setVisibility(View.VISIBLE);
                        }
                        if(chooseBuBottom==1){

                            winTop.setVisibility(View.VISIBLE);

                        }

                    }
                    if(chooseStoneTop==1){
                        if(chooseCutBottom==1){
                            winTop.setVisibility(View.VISIBLE);
                        }
                        if(chooseStoneBottom==1){

                        }
                        if(chooseBuBottom==1){
                            winBottom.setVisibility(View.VISIBLE);
                        }
                    }
                    if(chooseBuTop==1){
                        if(chooseCutBottom==1){
                            winBottom.setVisibility(View.VISIBLE);
                        }
                        if(chooseStoneBottom==1){
                            winTop.setVisibility(View.VISIBLE);
                        }
                        if(chooseBuBottom==1){

                        }
                    }

                }


            }
        });


    }


    public void startGame(){


        if(chooseNum==4) {
            if (chooseCutTop == 1) {
                cutBigTop.setVisibility(View.VISIBLE);
            }
            if (chooseStoneTop == 1) {
                stoneBigTop.setVisibility(View.VISIBLE);
            }
            if (chooseBuTop == 1) {
                buBigTop.setVisibility(View.VISIBLE);
            }
            if (chooseCutBottom == 1) {
                cutBigBottom.setVisibility(View.VISIBLE);
            }
            if (chooseStoneBottom == 1) {
                stoneBigBottom.setVisibility(View.VISIBLE);
            }
            if (chooseBuBottom == 1) {
                buBigBottom.setVisibility(View.VISIBLE);
            }
        }


    }


    public void refreshInt (){
         chooseNum=0;
         chooseCutTop=0;
         chooseStoneTop=0;
         chooseBuTop=0;
         chooseCutBottom=0;
         chooseStoneBottom=0;
         chooseBuBottom=0;
         chooseOnce=0;
         chooseSecondTime=0;
         finalResult=0;

        cutBigBottom.setVisibility(View.GONE);
        cutBigTop.setVisibility(View.GONE);
        stoneBigTop.setVisibility(View.GONE);
        stoneBigBottom.setVisibility(View.GONE);
        buBigTop.setVisibility(View.GONE);
        buBigBottom.setVisibility(View.GONE);

        winTop.setVisibility(View.GONE);
        winBottom.setVisibility(View.GONE);

    }


}