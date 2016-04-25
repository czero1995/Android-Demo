package com.example.czero.smarttime;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.os.Handler;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

/**
 * Created by zake on 4/9/16.
 */
public class WatchTImer extends LinearLayout implements View.OnClickListener {
    private Button btn_startwatch, btn_pausewatch, btn_continuewatch, btn_resetwatch, btn_lapwatch;
    private TextView watchtimemillisecond, watchtimeminute, watchtimesecond;
    private java.util.Timer timer = new java.util.Timer();
    private TimerTask timertask = null;
    private TimerTask showtime = null;
    private int timercount;
    private ListView lvwatch;
    private SimpleAdapter simpleAdapter;
    private List<Map<String,Object>> datalist;
    private int num = 1;


    public WatchTImer(Context context) {
        super(context);
    }

    public WatchTImer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WatchTImer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init() {
        btn_startwatch = (Button) findViewById(R.id.btn_startwatch);
        btn_pausewatch = (Button) findViewById(R.id.btn_pausewatch);
        btn_continuewatch = (Button) findViewById(R.id.btn_continuewatch);
        btn_resetwatch = (Button) findViewById(R.id.btn_resetwatch);
        btn_lapwatch = (Button) findViewById(R.id.btn_lapwatch);
        watchtimemillisecond = (TextView) findViewById(R.id.watchtimermillisecond);
        watchtimeminute = (TextView) findViewById(R.id.watchtimerminute);
        watchtimesecond = (TextView) findViewById(R.id.watchtimersecond);
        btn_startwatch.setOnClickListener(this);
        btn_pausewatch.setOnClickListener(this);
        btn_continuewatch.setOnClickListener(this);
        btn_resetwatch.setOnClickListener(this);
        btn_lapwatch.setOnClickListener(this);
        btn_pausewatch.setVisibility(View.GONE);
        btn_continuewatch.setVisibility(View.GONE);
        btn_resetwatch.setVisibility(View.GONE);
        btn_lapwatch.setVisibility(View.GONE);
        lvwatch = (ListView) findViewById(R.id.lvwatch);
        datalist = new ArrayList<Map<String, Object>>();

//        adapter=new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1);
//        lvwatch.setAdapter(adapter);
        simpleAdapter=new SimpleAdapter(getContext(),getData(),R.layout.lapadapter, new String[]{"appicon", "number","text"}, new int[]{R.id.appicon, R.id.number,R.id.text});
        lvwatch.setAdapter(simpleAdapter);
        showtime = new TimerTask(){

            @Override
            public void run() {
                handler.sendEmptyMessage(1);
            }
        };
        timer.schedule(showtime,200,200);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_startwatch:
                starWatchTime();
                btn_startwatch.setVisibility(View.GONE);
                btn_pausewatch.setVisibility(View.VISIBLE);
                btn_lapwatch.setVisibility(View.VISIBLE);

                break;
            case R.id.btn_pausewatch:
                stopWatchTime();
                btn_pausewatch.setVisibility(View.GONE);
                btn_startwatch.setVisibility(View.GONE);
                btn_continuewatch.setVisibility(View.VISIBLE);
                btn_resetwatch.setVisibility(View.VISIBLE);
                btn_lapwatch.setVisibility(View.GONE);
                break;
            case R.id.btn_continuewatch:
                starWatchTime();
                btn_continuewatch.setVisibility(View.GONE);
                btn_startwatch.setVisibility(View.GONE);
                btn_pausewatch.setVisibility(View.VISIBLE);
                btn_resetwatch.setVisibility(View.GONE);
                btn_lapwatch.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_resetwatch:
                stopWatchTime();
                timercount=0;
                btn_startwatch.setVisibility(View.VISIBLE);
                btn_pausewatch.setVisibility(View.GONE);
                btn_continuewatch.setVisibility(View.GONE);
                btn_resetwatch.setVisibility(View.GONE);
                btn_lapwatch.setVisibility(View.GONE);
                datalist.clear();
                break;
            case R.id.btn_lapwatch:
                getData();
                break;
        }

    }
    private List<Map<String, Object>> getData() {

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("appicon", R.drawable.appicon);
            map.put("number",datalist.size());
            map.put("text", String.format("%d:%d:%d",timercount/100/60%60,timercount/100%60,timercount%100));
            datalist.add(map);
            lvwatch.invalidateViews();//动态加载listview

        return datalist;

    }


    private void starWatchTime() {
        if (timertask == null) {
            timertask = new TimerTask() {
                @Override
                public void run() {
                    timercount++;
                }
            };
            timer.schedule(timertask, 10, 10);
        }
    }

    private void stopWatchTime() {
        if (timertask != null) {
            timertask.cancel();
            timertask = null;
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    watchtimeminute.setText(timercount / 100 / 60 % 60 + "");
                    watchtimesecond.setText(timercount / 100 % 60+ "");
                    watchtimemillisecond.setText(timercount %100 + "");
                    break;
            }
        }
    };
}
