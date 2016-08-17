package com.example.czero.zhihu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.czero.zhihu.backactivity.BaseActivity;

/**
 * Created by Kay on 15/4/20.
 */
public class WelcomeActivity extends BaseActivity {
    private TextView countDown,jump;
    private ImageView welcomeimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        countDown= (TextView) findViewById(R.id.count_down);
        jump= (TextView) findViewById(R.id.jump);
        welcomeimg= (ImageView) findViewById(R.id.welcomeimage);
        initView();
    }

    private void initView() {
        Glide.with(this).load("http://bmob-cdn-1826.b0.upaiyun.com/2016/07/27/ffb19ed34053c08e807de52407a4c6f0.jpg").placeholder(R.drawable.buildings).into(welcomeimg);
        final Typeface font = Typeface.createFromAsset(getAssets(), "splash.ttf");
        countDown.setTypeface(font);
        // 倒数计时
        CountDownTimer timer = new CountDownTimer(6200, 1000) {
            int num = 5;

            @Override
            public void onTick(long millisUntilFinished) {
                countDown.setText(String.valueOf(num));
                num--;
            }

            @Override
            public void onFinish() {
                Intent i = new Intent(WelcomeActivity.this, MainActivity.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
//                overridePendingTransition(0, 0);
                finish();
            }
        };
        timer.start();
    }


}
