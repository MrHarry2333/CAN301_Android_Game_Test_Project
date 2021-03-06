package com.example.androidapplicationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

public class doubleBearGameActivity extends AppCompatActivity {
    public boolean brr = false;//名字是 boolean red right 意为红色方右边是否点击，下同
    public boolean brl = false;
    public boolean bbl = false;
    public boolean bbr = false;

    public boolean fourpress = false;//四个边剑同时被按，游戏节点产生
    public boolean showpic = false;//节点产生时出现图片

    public int redgas = 0;
    public int bluegas = 0;//两方的气的数量，亏气失败

    public int redfight = 0;
    public int bluefight = 0;

    public ImageView rp;//两张图片
    public ImageView bp;

    public boolean pbgm;//是否正在播放bgm

    private SharedPreferences themeColorSharedPreferenceManager;
    private PopupWindow rulePopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        globalThemeColorSelection();
        setContentView(R.layout.activity_double_bear);
        ImageButton rr = (ImageButton) findViewById(R.id.redright);//四个长按钮
        ImageButton rl = (ImageButton) findViewById(R.id.redleft);
        ImageButton bl = (ImageButton) findViewById(R.id.blueleft);
        ImageButton br = (ImageButton) findViewById(R.id.blueright);

        ImageButton ra = (ImageButton) findViewById(R.id.reda);//红蓝双方的六个键， red a b c 和blue a b c
        ImageButton rb = (ImageButton) findViewById(R.id.redb);
        ImageButton rc = (ImageButton) findViewById(R.id.redc);
        ImageButton ba = (ImageButton) findViewById(R.id.bluea);
        ImageButton bb = (ImageButton) findViewById(R.id.blueb);
        ImageButton bc = (ImageButton) findViewById(R.id.bluec);

        ImageButton restart = (ImageButton) findViewById(R.id.restartbutton);//重置键

        ImageButton pbgmr=(ImageButton)findViewById(R.id.pbgm2);//红色方（上方）音乐播放器设置
        ImageButton pbgmb=(ImageButton)findViewById(R.id.pbgm1);

        rp=(ImageView) findViewById(R.id.redpic);
        bp=(ImageView) findViewById(R.id.bluepic);

        MediaPlayer bgm;
        bgm=MediaPlayer.create(this,R.raw.bgm1);
        bgm.setLooping(true);

        rr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brr = true;
                showpic=false;
                if(brl&&bbl&&bbr){
                    fourpress = true;
                }
                if(fourpress){
                    showpic = true;
                    fourpress = false;
                    brr=false;
                    brl=false;
                    bbl=false;
                    bbr=false;
//                    Toast.makeText(getApplicationContext(),"show picture",Toast.LENGTH_SHORT).show();

                    if(redfight==1&&bluefight==3){           //情况为红1蓝3，1是气，2是护盾，3是包子
                        if(bluegas<=0){                      //判定是否还有气
                            rp.setImageResource(R.mipmap.victory1);//无气红胜
                            bp.setImageResource(R.mipmap.nothing);
                            redgas=0;                        //游戏清零
                            bluegas=0;
                            redfight=0;
                            bluefight=0;

                        }
                        bp.setImageResource(R.mipmap.victory1);//有气蓝胜
                        rp.setImageResource(R.mipmap.nothing);
                        redgas=0;
                        bluegas=0;
                        redfight=0;
                        bluefight=0;
                    }
                    if(redfight==3&&bluefight==1){           //情况为红3蓝1
                        if(redgas<=0){
                            bp.setImageResource(R.mipmap.victory1);
                            rp.setImageResource(R.mipmap.nothing);
                            redgas=0;
                            bluegas=0;
                            redfight=0;
                            bluefight=0;
                        }
                        rp.setImageResource(R.mipmap.victory1);
                        bp.setImageResource(R.mipmap.nothing);
                        redgas=0;
                        bluegas=0;
                        redfight=0;
                        bluefight=0;
                    }
                    else {

                        if (redfight == 1 && bluefight == 1) {           //情况为红蓝同1
                            redgas++;
                            bluegas++;
                            rp.setImageResource(R.mipmap.gas1);
                            bp.setImageResource(R.mipmap.gas1);
                        }

                        if (redfight == 1 && bluefight == 2) {           //情况为红1蓝2
                            redgas++;
                            rp.setImageResource(R.mipmap.gas1);
                            bp.setImageResource(R.mipmap.shield1);
                        }

                        if(redfight== 2 &&bluefight== 1){
                            bluegas++;
                            rp.setImageResource(R.mipmap.shield1);
                            bp.setImageResource(R.mipmap.gas1);
                        }

                        if(redfight== 2 &&bluefight== 2){
                            rp.setImageResource(R.mipmap.shield1);
                            bp.setImageResource(R.mipmap.shield1);

                        }

                        if (redfight == 2 && bluefight == 3) {
                            if (bluegas <= 0) {                      //判定是否还有气
                                rp.setImageResource(R.mipmap.victory1);//无气红胜
                                bp.setImageResource(R.mipmap.nothing);
                                redgas=0;
                                bluegas=0;
                                redfight=0;
                                bluefight=0;
                            }else{
                                bluegas--;
                                rp.setImageResource(R.mipmap.shield1);
                                bp.setImageResource(R.mipmap.baozi1);
                            }

                        }

                        if(redfight== 3 &&bluefight== 2){
                            if(redgas<=0){
                                bp.setImageResource(R.mipmap.victory1);
                                rp.setImageResource(R.mipmap.nothing);
                                redgas=0;
                                bluegas=0;
                                redfight=0;
                                bluefight=0;
                            }else{
                                redgas--;
                                rp.setImageResource(R.mipmap.baozi1);
                                bp.setImageResource(R.mipmap.shield1);
                            }

                        }

                        if(redfight== 3 &&bluefight== 3){
                            if(redgas<=0&&bluegas<=0){
                                rp.setImageResource(R.mipmap.victory1);
                                bp.setImageResource(R.mipmap.victory1);
                                redgas=0;
                                bluegas=0;
                                redfight=0;
                                bluefight=0;
                            }else{
                                if(redgas<=0){
                                    bp.setImageResource(R.mipmap.victory1);
                                    rp.setImageResource(R.mipmap.nothing);
                                    redgas=0;
                                    bluegas=0;
                                    redfight=0;
                                    bluefight=0;
                                }
                                if (bluegas <= 0) {
                                    rp.setImageResource(R.mipmap.victory1);
                                    bp.setImageResource(R.mipmap.nothing);
                                    redgas=0;
                                    bluegas=0;
                                    redfight=0;
                                    bluefight=0;
                                }
                                redgas--;
                                bluegas--;
                                rp.setImageResource(R.mipmap.baozi1);
                                bp.setImageResource(R.mipmap.baozi1);

                            }

                        }



                    }

                    //fourpass即将结束


                }
            }
        });

        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brl = true;
                showpic=false;
                if(brr&&bbl&&bbr){
                    fourpress = true;
                }
                if(fourpress){
                    showpic = true;
                    fourpress = false;
                    brr=false;
                    brl=false;
                    bbl=false;
                    bbr=false;
//                    Toast.makeText(getApplicationContext(),"show picture",Toast.LENGTH_SHORT).show();

                    if(redfight==1&&bluefight==3){           //情况为红1蓝3，1是气，2是护盾，3是包子
                        if(bluegas<=0){                      //判定是否还有气
                            rp.setImageResource(R.mipmap.victory1);//无气红胜
                            bp.setImageResource(R.mipmap.nothing);
                            redgas=0;                        //游戏清零
                            bluegas=0;
                            redfight=0;
                            bluefight=0;

                        }
                        bp.setImageResource(R.mipmap.victory1);//有气蓝胜
                        rp.setImageResource(R.mipmap.nothing);
                        redgas=0;
                        bluegas=0;
                        redfight=0;
                        bluefight=0;
                    }
                    if(redfight==3&&bluefight==1){           //情况为红3蓝1
                        if(redgas<=0){
                            bp.setImageResource(R.mipmap.victory1);
                            rp.setImageResource(R.mipmap.nothing);
                            redgas=0;
                            bluegas=0;
                            redfight=0;
                            bluefight=0;
                        }
                        rp.setImageResource(R.mipmap.victory1);
                        bp.setImageResource(R.mipmap.nothing);
                        redgas=0;
                        bluegas=0;
                        redfight=0;
                        bluefight=0;
                    }
                    else {

                        if (redfight == 1 && bluefight == 1) {           //情况为红蓝同1
                            redgas++;
                            bluegas++;
                            rp.setImageResource(R.mipmap.gas1);
                            bp.setImageResource(R.mipmap.gas1);
                        }

                        if (redfight == 1 && bluefight == 2) {           //情况为红1蓝2
                            redgas++;
                            rp.setImageResource(R.mipmap.gas1);
                            bp.setImageResource(R.mipmap.shield1);
                        }

                        if(redfight== 2 &&bluefight== 1){
                            bluegas++;
                            rp.setImageResource(R.mipmap.shield1);
                            bp.setImageResource(R.mipmap.gas1);
                        }

                        if(redfight== 2 &&bluefight== 2){
                            rp.setImageResource(R.mipmap.shield1);
                            bp.setImageResource(R.mipmap.shield1);

                        }

                        if (redfight == 2 && bluefight == 3) {
                            if (bluegas <= 0) {                      //判定是否还有气
                                rp.setImageResource(R.mipmap.victory1);//无气红胜
                                bp.setImageResource(R.mipmap.nothing);
                                redgas=0;
                                bluegas=0;
                                redfight=0;
                                bluefight=0;
                            }else{
                                bluegas--;
                                rp.setImageResource(R.mipmap.shield1);
                                bp.setImageResource(R.mipmap.baozi1);
                            }

                        }

                        if(redfight== 3 &&bluefight== 2){
                            if(redgas<=0){
                                bp.setImageResource(R.mipmap.victory1);
                                rp.setImageResource(R.mipmap.nothing);
                                redgas=0;
                                bluegas=0;
                                redfight=0;
                                bluefight=0;
                            }else{
                                redgas--;
                                rp.setImageResource(R.mipmap.baozi1);
                                bp.setImageResource(R.mipmap.shield1);
                            }

                        }

                        if(redfight== 3 &&bluefight== 3){
                            if(redgas<=0&&bluegas<=0){
                                rp.setImageResource(R.mipmap.victory1);
                                bp.setImageResource(R.mipmap.victory1);
                                redgas=0;
                                bluegas=0;
                                redfight=0;
                                bluefight=0;
                            }else{
                                if(redgas<=0){
                                    bp.setImageResource(R.mipmap.victory1);
                                    rp.setImageResource(R.mipmap.nothing);
                                    redgas=0;
                                    bluegas=0;
                                    redfight=0;
                                    bluefight=0;
                                }
                                if (bluegas <= 0) {
                                    rp.setImageResource(R.mipmap.victory1);
                                    bp.setImageResource(R.mipmap.nothing);
                                    redgas=0;
                                    bluegas=0;
                                    redfight=0;
                                    bluefight=0;
                                }
                                redgas--;
                                bluegas--;
                                rp.setImageResource(R.mipmap.baozi1);
                                bp.setImageResource(R.mipmap.baozi1);

                            }

                        }



                    }

                    //fourpass即将结束
                }
            }
        });

        bl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bbl = true;
                showpic=false;
                if(brr&&brl&&bbr){
                    fourpress = true;
                }
                if(fourpress){
                    showpic = true;
                    fourpress = false;
                    brr=false;
                    brl=false;
                    bbl=false;
                    bbr=false;
//                    Toast.makeText(getApplicationContext(),"show picture",Toast.LENGTH_SHORT).show();

                    if(redfight==1&&bluefight==3){           //情况为红1蓝3，1是气，2是护盾，3是包子
                        if(bluegas<=0){                      //判定是否还有气
                            rp.setImageResource(R.mipmap.victory1);//无气红胜
                            bp.setImageResource(R.mipmap.nothing);
                            redgas=0;                        //游戏清零
                            bluegas=0;
                            redfight=0;
                            bluefight=0;

                        }
                        bp.setImageResource(R.mipmap.victory1);//有气蓝胜
                        rp.setImageResource(R.mipmap.nothing);
                        redgas=0;
                        bluegas=0;
                        redfight=0;
                        bluefight=0;
                    }
                    if(redfight==3&&bluefight==1){           //情况为红3蓝1
                        if(redgas<=0){
                            bp.setImageResource(R.mipmap.victory1);
                            rp.setImageResource(R.mipmap.nothing);
                            redgas=0;
                            bluegas=0;
                            redfight=0;
                            bluefight=0;
                        }
                        rp.setImageResource(R.mipmap.victory1);
                        bp.setImageResource(R.mipmap.nothing);
                        redgas=0;
                        bluegas=0;
                        redfight=0;
                        bluefight=0;
                    }
                    else {

                        if (redfight == 1 && bluefight == 1) {           //情况为红蓝同1
                            redgas++;
                            bluegas++;
                            rp.setImageResource(R.mipmap.gas1);
                            bp.setImageResource(R.mipmap.gas1);
                        }

                        if (redfight == 1 && bluefight == 2) {           //情况为红1蓝2
                            redgas++;
                            rp.setImageResource(R.mipmap.gas1);
                            bp.setImageResource(R.mipmap.shield1);
                        }

                        if(redfight== 2 &&bluefight== 1){
                            bluegas++;
                            rp.setImageResource(R.mipmap.shield1);
                            bp.setImageResource(R.mipmap.gas1);
                        }

                        if(redfight== 2 &&bluefight== 2){
                            rp.setImageResource(R.mipmap.shield1);
                            bp.setImageResource(R.mipmap.shield1);

                        }

                        if (redfight == 2 && bluefight == 3) {
                            if (bluegas <= 0) {                      //判定是否还有气
                                rp.setImageResource(R.mipmap.victory1);//无气红胜
                                bp.setImageResource(R.mipmap.nothing);
                                redgas=0;
                                bluegas=0;
                                redfight=0;
                                bluefight=0;
                            }else{
                                bluegas--;
                                rp.setImageResource(R.mipmap.shield1);
                                bp.setImageResource(R.mipmap.baozi1);
                            }

                        }

                        if(redfight== 3 &&bluefight== 2){
                            if(redgas<=0){
                                bp.setImageResource(R.mipmap.victory1);
                                rp.setImageResource(R.mipmap.nothing);
                                redgas=0;
                                bluegas=0;
                                redfight=0;
                                bluefight=0;
                            }else{
                                redgas--;
                                rp.setImageResource(R.mipmap.baozi1);
                                bp.setImageResource(R.mipmap.shield1);
                            }

                        }

                        if(redfight== 3 &&bluefight== 3){
                            if(redgas<=0&&bluegas<=0){
                                rp.setImageResource(R.mipmap.victory1);
                                bp.setImageResource(R.mipmap.victory1);
                                redgas=0;
                                bluegas=0;
                                redfight=0;
                                bluefight=0;
                            }else{
                                if(redgas<=0){
                                    bp.setImageResource(R.mipmap.victory1);
                                    rp.setImageResource(R.mipmap.nothing);
                                    redgas=0;
                                    bluegas=0;
                                    redfight=0;
                                    bluefight=0;
                                }
                                if (bluegas <= 0) {
                                    rp.setImageResource(R.mipmap.victory1);
                                    bp.setImageResource(R.mipmap.nothing);
                                    redgas=0;
                                    bluegas=0;
                                    redfight=0;
                                    bluefight=0;
                                }
                                redgas--;
                                bluegas--;
                                rp.setImageResource(R.mipmap.baozi1);
                                bp.setImageResource(R.mipmap.baozi1);

                            }

                        }



                    }

                    //fourpass即将结束
                }
            }
        });

        br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bbr = true;
                showpic=false;
                if(brr&&brl&&bbl){
                    fourpress = true;
                }
                if(fourpress){
                    showpic = true;
                    fourpress = false;
                    brr=false;
                    brl=false;
                    bbl=false;
                    bbr=false;
//                    Toast.makeText(getApplicationContext(),"show picture",Toast.LENGTH_SHORT).show();

                    if(redfight==1&&bluefight==3){           //情况为红1蓝3，1是气，2是护盾，3是包子
                        if(bluegas<=0){                      //判定是否还有气
                            rp.setImageResource(R.mipmap.victory1);//无气红胜
                            bp.setImageResource(R.mipmap.nothing);
                            redgas=0;                        //游戏清零
                            bluegas=0;
                            redfight=0;
                            bluefight=0;

                        }
                        bp.setImageResource(R.mipmap.victory1);//有气蓝胜
                        rp.setImageResource(R.mipmap.nothing);
                        redgas=0;
                        bluegas=0;
                        redfight=0;
                        bluefight=0;
                    }
                    if(redfight==3&&bluefight==1){           //情况为红3蓝1
                        if(redgas<=0){
                            bp.setImageResource(R.mipmap.victory1);
                            rp.setImageResource(R.mipmap.nothing);
                            redgas=0;
                            bluegas=0;
                            redfight=0;
                            bluefight=0;
                        }
                        rp.setImageResource(R.mipmap.victory1);
                        bp.setImageResource(R.mipmap.nothing);
                        redgas=0;
                        bluegas=0;
                        redfight=0;
                        bluefight=0;
                    }
                    else {

                        if (redfight == 1 && bluefight == 1) {           //情况为红蓝同1
                            redgas++;
                            bluegas++;
                            rp.setImageResource(R.mipmap.gas1);
                            bp.setImageResource(R.mipmap.gas1);
                        }

                        if (redfight == 1 && bluefight == 2) {           //情况为红1蓝2
                            redgas++;
                            rp.setImageResource(R.mipmap.gas1);
                            bp.setImageResource(R.mipmap.shield1);
                        }

                        if(redfight== 2 &&bluefight== 1){
                            bluegas++;
                            rp.setImageResource(R.mipmap.shield1);
                            bp.setImageResource(R.mipmap.gas1);
                        }

                        if(redfight== 2 &&bluefight== 2){
                            rp.setImageResource(R.mipmap.shield1);
                            bp.setImageResource(R.mipmap.shield1);

                        }

                        if (redfight == 2 && bluefight == 3) {
                            if (bluegas <= 0) {                      //判定是否还有气
                                rp.setImageResource(R.mipmap.victory1);//无气红胜
                                bp.setImageResource(R.mipmap.nothing);
                                redgas=0;
                                bluegas=0;
                                redfight=0;
                                bluefight=0;
                            }else{
                                bluegas--;
                                rp.setImageResource(R.mipmap.shield1);
                                bp.setImageResource(R.mipmap.baozi1);
                            }

                        }

                        if(redfight== 3 &&bluefight== 2){
                            if(redgas<=0){
                                bp.setImageResource(R.mipmap.victory1);
                                rp.setImageResource(R.mipmap.nothing);
                                redgas=0;
                                bluegas=0;
                                redfight=0;
                                bluefight=0;
                            }else{
                                redgas--;
                                rp.setImageResource(R.mipmap.baozi1);
                                bp.setImageResource(R.mipmap.shield1);
                            }

                        }

                        if(redfight== 3 &&bluefight== 3){
                            if(redgas<=0&&bluegas<=0){
                                rp.setImageResource(R.mipmap.victory1);
                                bp.setImageResource(R.mipmap.victory1);
                                redgas=0;
                                bluegas=0;
                                redfight=0;
                                bluefight=0;
                            }else{
                                if(redgas<=0){
                                    bp.setImageResource(R.mipmap.victory1);
                                    rp.setImageResource(R.mipmap.nothing);
                                    redgas=0;
                                    bluegas=0;
                                    redfight=0;
                                    bluefight=0;
                                }
                                if (bluegas <= 0) {
                                    rp.setImageResource(R.mipmap.victory1);
                                    bp.setImageResource(R.mipmap.nothing);
                                    redgas=0;
                                    bluegas=0;
                                    redfight=0;
                                    bluefight=0;
                                }
                                redgas--;
                                bluegas--;
                                rp.setImageResource(R.mipmap.baozi1);
                                bp.setImageResource(R.mipmap.baozi1);

                            }

                        }



                    }

                    //fourpass即将结束
                }
            }
        });

        ra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redfight = 1;
                redgas++;

            }
        });

        rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redfight = 2;

            }
        });

        rc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redfight = 3;
                redgas--;

            }
        });

        ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bluefight = 1;
                bluegas++;

            }
        });

        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bluefight = 2;

            }
        });

        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bluefight = 3;
                bluegas--;

            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brr =false;
                brl = false;
                bbr = false;
                bbl = false;
                fourpress=false;
                showpic=false;
                redgas=0;
                bluegas=0;
                redfight=0;
                bluefight=0;
                rp.setImageResource(R.mipmap.nothing);
                bp.setImageResource(R.mipmap.nothing);
            }
        });

        pbgmr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!pbgm){
                    bgm.start();
                    pbgm=true;
                }else{
                    bgm.pause();
                    pbgm=false;
                }
            }
        });

        pbgmb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!pbgm){
                    bgm.start();
                    pbgm=true;
                }else{
                    bgm.pause();
                    pbgm=false;
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
        View v = LayoutInflater.from(doubleBearGameActivity.this).inflate(R.layout.popupwindow_game3_rule, null, false);
        rulePopupWindow = new PopupWindow(v, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        rulePopupWindow.setOutsideTouchable(true);
        // p.setBackgroundmipmap(new Colormipmap(0x00000000));
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
