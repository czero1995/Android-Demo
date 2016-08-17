package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJData.UnionItemListAdapter;
import com.example.czero.szzj.SZZJData.WelcomeItemListAdapter;
import com.example.czero.szzj.SZZJModel.Union;
import com.example.czero.szzj.SZZJModel.Welcome;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.push.BmobPush;
import cn.bmob.sms.BmobSMS;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;


/**
 * Created by zake on 5/20/16.
 */
public class WELCOMESZZJ extends Activity implements AdapterView.OnItemClickListener{
    private static final String APPID = "f511c1b5bea912ef964937dcd486da8f";
    private static final int GO_HOME = 100;
    private static final int GO_LOGIN = 200;
    private ImageView welcomepic;
    private ListView lvwelcome;
    private TextView welcometext;
    private Button welcomejump;
    private WelcomeItemListAdapter welcomeItemListAdapter;

    private List<Welcome> welcomeList = new ArrayList<Welcome>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bmob.initialize(this, APPID);
        BmobSMS.initialize(this, APPID);
        // 初始化BmobSDK
        // 使用推送服务时的初始化操作
        BmobInstallation.getCurrentInstallation(this).save();
        // 启动推送服务
        BmobPush.startWork(this);

        setContentView(R.layout.activity_welcomeszzj);

        lvwelcome = (ListView) findViewById(R.id.lv_welcome);
      welcomepic= (ImageView) findViewById(R.id.welcomepic);
        welcometext= (TextView) findViewById(R.id.welcometext);
        welcomeItemListAdapter = new WelcomeItemListAdapter(WELCOMESZZJ.this, (ArrayList<Welcome>) welcomeList);
        lvwelcome.setAdapter(welcomeItemListAdapter);
        lvwelcome.setOnItemClickListener(this);
        welcomejump= (Button) findViewById(R.id.welcomejump);
        welcomejump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WELCOMESZZJ.this,MainActivity.class);
                startActivity(intent);
              mHandler.removeMessages(GO_LOGIN);
                finish();
            }
        });
        getWelcomeData();
        animal();
        mHandler.sendEmptyMessageDelayed(GO_LOGIN, 5000);
    }
    private void getWelcomeData(){
        BmobQuery<Welcome> query = new BmobQuery<Welcome>();
        query.order("-updatedAt");
        query.findObjects(this, new FindListener<Welcome>() {

            @Override
            public void onSuccess(List<Welcome> object) {

                //toast("查询成功. 共计" + object.size());


//                    img_default.setVisibility(View.GONE);
                    welcomeList = object;
                    // 通知Adapter数据更新
                    welcomeItemListAdapter.refresh((ArrayList<Welcome>) welcomeList);
                    //tradeItemListAdapter.notifyDataSetChanged();


            }

            @Override
            public void onError(int arg0, String msg) {
            }

        });
    }

    private void toast(String toast) {
        Toast.makeText(this, toast,  Toast.LENGTH_SHORT).show();
    }
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_HOME:
                    break;
                case GO_LOGIN:
                    Intent intent = new Intent(WELCOMESZZJ.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    };

    /*
    动画效果
     */
    private void animal() {
        ObjectAnimator.ofFloat(welcomepic, "rotationY", 0F, 360F).setDuration(2000).start();
        ObjectAnimator.ofFloat(welcomepic, "alpha", 0F, 1F).setDuration(2000).start();
        ObjectAnimator.ofFloat(welcometext, "alpha", 0F, 1F).setDuration(2000).start();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        toast("欢迎使用汕职之家.够真橙,活青春!");
    }
}