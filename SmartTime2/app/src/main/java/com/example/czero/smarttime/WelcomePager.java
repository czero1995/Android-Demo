package com.example.czero.smarttime;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by zake on 4/10/16.
 */
public class WelcomePager extends Activity {
    private boolean isFirstIn = false;
    private static final int TIME = 2000;
    private static final int GO_HOME = 1;
    private static final int GO_GUIDE = 2;


    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case GO_HOME:
                    goHome();
                    break;

                case GO_GUIDE:
                    goGuide();
                    break;
            }

        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomepager);
        init();
    }

    private void init(){
        SharedPreferences perPreferences = getSharedPreferences("czero", MODE_PRIVATE);
        isFirstIn = perPreferences.getBoolean("isFirstIn", true);
        if (!isFirstIn) {
            mHandler.sendEmptyMessageDelayed(GO_HOME, TIME);
        }else{
            mHandler.sendEmptyMessageDelayed(GO_GUIDE, TIME);
            Editor editor = perPreferences.edit();
            editor.putBoolean("isFirstIn", false);
            editor.commit();
        }

    }

    private void goHome(){
        Intent i = new Intent(WelcomePager.this,MainActivity.class);
        startActivity(i);
        finish();
    }
    private void goGuide(){
        Intent i = new Intent(WelcomePager.this,GuidePager.class);
        startActivity(i);
        finish();
    }

}

