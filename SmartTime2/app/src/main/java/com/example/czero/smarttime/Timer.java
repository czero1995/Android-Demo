package com.example.czero.smarttime;

import android.app.AlertDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.TimerTask;
import android.os.Handler;

/**
 * Created by zake on 4/9/16.
 */
public class Timer extends LinearLayout implements View.OnClickListener {
    private Button btn_start,btn_pause,btn_continue,btn_reset;
    private EditText hour,minute,second;
    private int timecount=0;
    private static final int MESSAGE1=1;
    private static final int MESSAGE2=2;
    private java.util.Timer timer= new java.util.Timer();
    private TimerTask timetask = null;
    public Timer(Context context) {
        super(context);
    }

    public Timer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Timer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init() {
        btn_start= (Button) findViewById(R.id.btn_comein);
        btn_pause= (Button) findViewById(R.id.btn_pause);
        btn_continue= (Button) findViewById(R.id.btn_continue);
        btn_reset= (Button) findViewById(R.id.btn_reset);
        hour= (EditText) findViewById(R.id.hour);
        minute= (EditText) findViewById(R.id.minute);
        second= (EditText) findViewById(R.id.second);
        btn_start.setEnabled(false);
        btn_pause.setVisibility(View.GONE);
        btn_continue.setVisibility(View.GONE);
        btn_reset.setVisibility(View.GONE);
        btn_start.setOnClickListener(this);
        btn_pause.setOnClickListener(this);
        btn_continue.setOnClickListener(this);
        btn_reset.setOnClickListener(this);
        checkButtonEnable();
        /**
         * 设置输入的文本内容区域
         */
        hour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(!TextUtils.isEmpty(s)){
                int value = Integer.parseInt(s.toString());
                if(value>59){
                    hour.setText("59");

                }
            }
                checkButtonEnable();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        minute.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!TextUtils.isEmpty(s)){
                    int value = Integer.parseInt(s.toString());
                    if(value>59){
                        minute.setText("59");

                    }
                }
                checkButtonEnable();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        second.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!TextUtils.isEmpty(s)){
                    int value = Integer.parseInt(s.toString());
                    if(value>59){
                        second.setText("59");
                    }
                }
                checkButtonEnable();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }



    private void checkButtonEnable(){
        btn_start.setEnabled((!TextUtils.isEmpty(hour.getText())&&Integer.parseInt(hour.getText().toString())>0)||
                (!TextUtils.isEmpty(minute.getText())&&Integer.parseInt(minute.getText().toString())>0)||
                (!TextUtils.isEmpty(second.getText())&&Integer.parseInt(second.getText().toString())>0));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_comein:
                startTime();
                btn_start.setVisibility(View.GONE);
                btn_pause.setVisibility(View.VISIBLE);
                btn_reset.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_pause:
                stopTime();
                btn_pause.setVisibility(View.GONE);
                btn_continue.setVisibility(View.VISIBLE);
                btn_reset.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_continue:
                startTime();
                btn_continue.setVisibility(View.GONE);
                btn_pause.setVisibility(View.VISIBLE);
                btn_reset.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_reset:
                stopTime();
                hour.setText("00");
                minute.setText("00");
                second.setText("00");
                btn_start.setVisibility(View.VISIBLE);
                btn_pause.setVisibility(View.GONE);
                btn_continue.setVisibility(View.GONE);
                btn_reset.setVisibility(View.GONE);
        }
    }

    /**
     * 时间停止,调用timetask.cancel方法
     */
    private void stopTime() {
        if(timetask!=null){
            timetask.cancel();
            timetask=null;
        }
    }

    private void startTime() {
        if (timetask == null) {
            timecount = Integer.parseInt(hour.getText().toString()) * 60 * 60 + Integer.parseInt(minute.getText().toString()) * 60 + Integer.parseInt(second.getText().toString());

            timetask = new TimerTask() {
                @Override
                public void run() {
                    timecount--;
                    handler.sendEmptyMessage(MESSAGE1);

                    if(timecount<=0){
                        handler.sendEmptyMessage(MESSAGE2);
                        stopTime();
                    }
                }
            };
            timer.schedule(timetask, 1000, 1000);
        }
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MESSAGE1:
                    int hourcount = timecount/60/60;
                    int minutecount = (timecount/60)%60;
                    int secondcount = timecount%60;
                    hour.setText(hourcount+"");
                    minute.setText(minutecount+"");
                    second.setText(secondcount+"");
                    break;
                case MESSAGE2:
                    new AlertDialog.Builder(getContext()).setTitle("time is up").setMessage("time is up").setNegativeButton("cancel",null).show();
                    btn_start.setVisibility(View.VISIBLE);
                    btn_pause.setVisibility(View.GONE);
                    btn_continue.setVisibility(View.GONE);
                    btn_reset.setVisibility(View.GONE);
                    break;
            }
        }
    };
}
