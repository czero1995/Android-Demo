package com.example.czero.zmsz.ZMSZView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.os.Handler;
import com.example.czero.zmsz.R;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;


/**
 * Created by zake on 5/20/16.
 */
public class WELCOMEZMSZ extends Activity {
    private static final String APPID = "f511c1b5bea912ef964937dcd486da8f";
    private static final int GO_HOME=100;
    private static final int GO_LOGIN=200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, APPID);
        // 初始化BmobSDK
        // 使用推送服务时的初始化操作
        BmobInstallation.getCurrentInstallation(this).save();
        // 启动推送服务
        BmobPush.startWork(this);

        setContentView(R.layout.activity_welcomezmsz);
        mHandler.sendEmptyMessageDelayed(GO_LOGIN,3000);
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GO_HOME:
                    break;
                case GO_LOGIN:
                    Intent intent = new Intent(WELCOMEZMSZ.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    };

}

