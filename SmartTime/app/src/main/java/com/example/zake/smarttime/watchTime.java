package com.example.zake.smarttime;

import android.content.Context;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Handler;
import java.util.*;
import java.util.Timer;


/**
 * Created by zake on 3/18/16.
 */
public class watchTime extends LinearLayout {
    public watchTime(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        hour= (TextView) findViewById(R.id.hour);
        minute= (TextView) findViewById(R.id.minute);
        second= (TextView) findViewById(R.id.second);
        secondmillis= (TextView) findViewById(R.id.secondmillis);
        btnstart= (Button) findViewById(R.id.btnstart);
        btnpause= (Button) findViewById(R.id.btnpause);
        btnresume= (Button) findViewById(R.id.btnresume);
        btnreset= (Button) findViewById(R.id.btnreset);
        btnlap= (Button) findViewById(R.id.btnlap);
        lv= (ListView) findViewById(R.id.lv);
        adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1);
        lv.setAdapter(adapter);
        hour.setText("0");
        minute.setText("0");
        second.setText("0");
        secondmillis.setText("0");
        showtime = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(MSG_WHAT_SHOW_TIME);
            }
        };
        timer.schedule(showtime,200,200);
        btnstart.setVisibility(View.VISIBLE);
        btnpause.setVisibility(View.GONE);
        btnresume.setVisibility(View.GONE);
        btnreset.setVisibility(View.GONE);
        btnlap.setVisibility(View.GONE);
        btnlap.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.insert(String.format("%d:%d:%d:%d",tenMsec/100/60/60,tenMsec/100/60%60,tenMsec/100%60,tenMsec%100),0);
            }
        });
        btnreset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                stoptimer();
                tenMsec = 0;
                adapter.clear();
                btnreset.setVisibility(View.GONE);
                btnstart.setVisibility(View.VISIBLE);
                btnpause.setVisibility(View.GONE);
                btnresume.setVisibility(View.GONE);
                btnlap.setVisibility(View.GONE);
            }
        });
        btnresume.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                starttime();
                btnresume.setVisibility(View.GONE);
                btnpause.setVisibility(View.VISIBLE);

            }
        });
        btnpause.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                stoptimer();
                btnpause.setVisibility(View.GONE);
                btnresume.setVisibility(View.VISIBLE);
            }
        });
        btnstart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                starttime();
                btnstart.setVisibility(View.GONE);
                btnpause.setVisibility(View.VISIBLE);

            }
        });

    }
    private void starttime(){
        if(timerTask==null){
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    tenMsec++;

                }
            };
            timer.schedule(timerTask,10,10);
            btnstart.setVisibility(View.GONE);
            btnpause.setVisibility(View.VISIBLE);
            btnreset.setVisibility(View.VISIBLE);
            btnlap.setVisibility(View.VISIBLE);
        }

    }
    private void stoptimer(){
        if(timerTask!=null){
            timerTask.cancel();
            timerTask=null;
        }
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(android.os.Message msg) {
            switch (msg.what){
                case MSG_WHAT_SHOW_TIME:
                hour.setText(tenMsec/100/60/60+"");
                    minute.setText(tenMsec/100/60%60+"");
                    second.setText((tenMsec/100%60+""));
                    secondmillis.setText(tenMsec%100+"");
                    break;
            }
        }

    };


    private static final int MSG_WHAT_SHOW_TIME = 1;
    private TimerTask showtime = null;
    private int tenMsec = 0;
    private Timer timer = new Timer();
    private TimerTask timerTask = null;

    private ListView lv;
    private TextView hour,minute,second,secondmillis;
    private Button btnstart,btnpause,btnresume,btnreset,btnlap;
    private ArrayAdapter<String> adapter;

    public void onDestroy() {
        timer.cancel();
    }
}
