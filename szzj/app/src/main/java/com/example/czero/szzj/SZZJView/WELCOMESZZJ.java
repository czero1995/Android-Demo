package com.example.czero.szzj.SZZJView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJData.UnionItemListAdapter;
import com.example.czero.szzj.SZZJData.WelcomeItemListAdapter;
import com.example.czero.szzj.SZZJModel.Union;
import com.example.czero.szzj.SZZJModel.Welcome;
import com.example.czero.szzj.View.CircularAnimUtil;
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
    private TextView countDown,jump;
    private static final int ANIMATION_DURATION = 5000;
    private static final float SCALE_END = 1.13F;
    private ImageView mSplashImage;
    private TextView titleView;
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
        mSplashImage = (ImageView) findViewById(R.id.iv_entry);
        titleView = (TextView) findViewById( R.id.tv_title);
        countDown= (TextView) findViewById(R.id.count_down);
        jump= (TextView) findViewById(R.id.jump);
        animateImage();
        Glide.with(this).load("http://119.29.121.145/123.jpeg").placeholder(R.drawable.background).into(mSplashImage);
        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CircularAnimUtil.startActivity((Activity) WELCOMESZZJ.this, ServerActivity.class, view ,R.color.bantouming);


            }
        });
        final Typeface font = Typeface.createFromAsset(getAssets(), "splash.ttf");
        countDown.setTypeface(font);
        // 倒数计时
        CountDownTimer timer = new CountDownTimer(4200, 1000) {
            int num = 5;

            @Override
            public void onTick(long millisUntilFinished) {
                countDown.setText(String.valueOf(num));
                num--;
            }

            @Override
            public void onFinish() {
//                Intent i = new Intent(WELCOMESZZJ.this, MainActivity.class);
//                startActivity(i);
//                finish();
            }
        };
        timer.start();
    }

    private void animateImage() {
        android.animation.ObjectAnimator animatorX = android.animation.ObjectAnimator.ofFloat(mSplashImage, "scaleX", 1f, SCALE_END);
        android.animation.ObjectAnimator animatorY = android.animation.ObjectAnimator.ofFloat(mSplashImage, "scaleY", 1f, SCALE_END);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(ANIMATION_DURATION).play(animatorX).with(animatorY);
        set.start();
//
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
               Intent intent = new Intent(WELCOMESZZJ.this,MainActivity.class);
                startActivity(intent);
               WELCOMESZZJ.this.finish();
            }
        });
    }
//    private void getWelcomeData(){
//        BmobQuery<Welcome> query = new BmobQuery<Welcome>();
//        query.order("-updatedAt");
//        query.findObjects(this, new FindListener<Welcome>() {
//
//            @Override
//            public void onSuccess(List<Welcome> object) {
//
//                //toast("查询成功. 共计" + object.size());
//
//
////                    img_default.setVisibility(View.GONE);
//                    welcomeList = object;
//                    // 通知Adapter数据更新
//                    welcomeItemListAdapter.refresh((ArrayList<Welcome>) welcomeList);
//                    //tradeItemListAdapter.notifyDataSetChanged();
//
//
//            }
//
//            @Override
//            public void onError(int arg0, String msg) {
//            }
//
//        });
//    }


    private void toast(String toast) {
        Toast.makeText(this, toast,  Toast.LENGTH_SHORT).show();
    }

    /*
    动画效果
     */
//    private void animal() {
//        ObjectAnimator.ofFloat(welcomepic, "rotationY", 0F, 360F).setDuration(2000).start();
//        ObjectAnimator.ofFloat(welcomepic, "alpha", 0F, 1F).setDuration(2000).start();
//        ObjectAnimator.ofFloat(welcometext, "alpha", 0F, 1F).setDuration(2000).start();
//    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        toast("欢迎使用汕职之家.够真橙,活青春!");
    }
}