package com.example.zake.smarttime;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.TimerTask;

/**
 * Created by zake on 3/16/16.
 */
public class Timer extends LinearLayout {
    public Timer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Timer(Context context) {
        super(context);
    }



    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        start = (Button) findViewById(R.id.start);
        pause = (Button) findViewById(R.id.pause);
        reset = (Button) findViewById(R.id.reset);
        resume = (Button) findViewById(R.id.resume);
        ethour= (EditText) findViewById(R.id.ethour);
        etminute= (EditText) findViewById(R.id.etminute);
        etsecond= (EditText) findViewById(R.id.etsecond);
        ethour.setText("00");
        etminute.setText("00");
        etsecond.setText("00");
        start.setVisibility(View.VISIBLE);
        start.setEnabled(false);
        pause.setVisibility(View.GONE);
        reset.setVisibility(View.GONE);
        resume.setVisibility(View.GONE);

        start.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                starttimer();
            }
        });
        resume.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                starttimer();
                resume.setVisibility(View.GONE);
                pause.setVisibility(View.VISIBLE);

            }
        });
        pause.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                stoptimer();
                pause.setVisibility(View.GONE);
                resume.setVisibility(View.VISIBLE);

            }
        });

        reset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                stoptimer();
                ethour.setText("00");
                etminute.setText("00");
                etsecond.setText("00");
                start.setVisibility(View.VISIBLE);
                pause.setVisibility(View.GONE);
                reset.setVisibility(View.GONE);
                resume.setVisibility(View.GONE);
            }
        });
        ethour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!TextUtils.isEmpty(s)){
                    int value = Integer.parseInt(s.toString());
                    if(value>59){
                        ethour.setText("59");
                    }else if(value<0){
                        ethour.setText("0");
                    }

                }

                checktoenablebtngroupstart();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etminute.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!TextUtils.isEmpty(s)){
                    int value = Integer.parseInt(s.toString());
                    if(value>59){
                        etminute.setText("59");
                    }else if(value<0){
                        etminute.setText("0");
                    }

                }

                checktoenablebtngroupstart();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etsecond.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!TextUtils.isEmpty(s)){
                    int value = Integer.parseInt(s.toString());
                    if(value>59){
                        etsecond.setText("59");
                    }else if(value<0){
                        etsecond.setText("0");
                    }
                }

                checktoenablebtngroupstart();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void
    checktoenablebtngroupstart(){
        start.setEnabled((!TextUtils.isEmpty(ethour.getText())&&Integer.parseInt(ethour.getText().toString())>0)||
                (!TextUtils.isEmpty(etminute.getText())&&Integer.parseInt(etminute.getText().toString())>0)||
                (!TextUtils.isEmpty(etsecond.getText())&&Integer.parseInt(etsecond.getText().toString())>0));

    }
    private void starttimer(){
        if(timetask==null){
            timercount= Integer.parseInt(ethour.getText().toString())*60*60+
                    Integer.parseInt(etminute.getText().toString())*60
                    +Integer.parseInt(etsecond.getText().toString());;
            timetask = new TimerTask() {
                @Override
                public void run() {
                    timercount--;
//                    Message msg = new Message();
//                    msg.what=MSG_WHAT_TIME_TICK;
//                    msg.arg1=timercount;
                    handler.sendEmptyMessage(MSG_WHAT_TIME_TICK);
                    if(timercount<=0){
                        handler.sendEmptyMessage(MSG_WHAT_TIME_IS_UP);
                       stoptimer();
                    }
                }
            };
            timer.schedule(timetask,1000,1000);
            start.setVisibility(View.GONE);
            pause.setVisibility(View.VISIBLE);
            reset.setVisibility(View.VISIBLE);

        }

    }
    private  void stoptimer(){
        if(timetask!=null){
            timetask.cancel();
            timetask=null;

        }
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(android.os.Message msg) {

            switch (msg.what){
                case MSG_WHAT_TIME_TICK:
                    int hour = timercount/60/60;
                    int min = (timercount/60)%60;
                    int sec = timercount%60;
                    ethour.setText((hour+""));
                    etminute.setText(min+"");
                    etsecond.setText(sec+"");
                    break;

                case MSG_WHAT_TIME_IS_UP:
                    new AlertDialog.Builder(getContext()).setTitle("time is up").setMessage("time up too").setNegativeButton("cancel",null).show();
                    start.setVisibility(View.VISIBLE);
                    pause.setVisibility(View.GONE);
                    reset.setVisibility(View.GONE);
                    resume.setVisibility(View.GONE);
                    break;
            }
        }

    };

    private static final int MSG_WHAT_TIME_IS_UP = 1;
    private static final int MSG_WHAT_TIME_TICK = 2;
    private int timercount= 0 ;
    private java.util.Timer timer = new java.util.Timer();
    private TimerTask timetask = null;
    private Button start,pause,reset,resume;
    private EditText ethour,etminute,etsecond;

}
