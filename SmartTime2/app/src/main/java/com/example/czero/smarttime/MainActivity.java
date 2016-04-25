package com.example.czero.smarttime;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener{
    private ViewPager viewpager;
    private PagerAdapter pageradapter;
    private List<View> mview = new ArrayList<View>();
    private LinearLayout tabtime;
    private LinearLayout tabalarm;
    private LinearLayout tabtimer;
    private LinearLayout tabwatchtimer;
    private ImageButton tabtime_img;
    private ImageButton tabalarm_img;
    private ImageButton tabtimer_img;
    private ImageButton tabwatchtimer_img;
    private Button button;
    private TextView showtime;
    private NotificationManager notificationManager;
    private int notification_ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    private void initEvent() {
        tabtime.setOnClickListener(this);
        tabalarm.setOnClickListener(this);
        tabtimer.setOnClickListener(this);
        tabwatchtimer.setOnClickListener(this); notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        startService(new Intent(MainActivity.this, MyService.class));
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int currentItem = viewpager.getCurrentItem();
                resetImg();
                switch (currentItem){
                    case 0 :
                        tabtime_img.setImageResource(R.drawable.time_press);
                        break;
                    case 1:
                        tabalarm_img.setImageResource(R.drawable.alarm_press);
                        break;
                    case 2:
                        tabtimer_img.setImageResource(R.drawable.timer_press);
                        break;
                    case 3:
                        tabwatchtimer_img.setImageResource(R.drawable.watchtimer_press);
                        break;
                }
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        viewpager= (ViewPager) findViewById(R.id.viewpager);
        tabtime= (LinearLayout) findViewById(R.id.tabtime);
        tabalarm= (LinearLayout) findViewById(R.id.tabalarm);
        tabtimer= (LinearLayout) findViewById(R.id.tabtimer);
        tabwatchtimer= (LinearLayout) findViewById(R.id.tabwatchtimer);
        tabtime_img = (ImageButton) findViewById(R.id.tabtime_img);
        tabalarm_img = (ImageButton) findViewById(R.id.tabalarm_img);
        tabtimer_img = (ImageButton) findViewById(R.id.tabtimer_img);
        tabwatchtimer_img = (ImageButton) findViewById(R.id.tabwatchtimer_img);
//        button= (Button) findViewById(R.id.button);
//        button.setOnClickListener(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View time = inflater.inflate(R.layout.time,null);
        View alarm = inflater.inflate(R.layout.alarm,null);
        View timer = inflater.inflate(R.layout.timer,null);
        View watchtimer = inflater.inflate(R.layout.watchtimer,null);
        mview.add(time);
        mview.add(alarm);
        mview.add(timer);
        mview.add(watchtimer);
        pageradapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return mview.size();
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
               View view =mview.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mview.get(position));
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }
        };
        viewpager.setAdapter(pageradapter);
    }
    private void resetImg() {
        tabtime_img.setImageResource(R.drawable.time_normal);
        tabalarm_img.setImageResource(R.drawable.alarm_normal);
        tabtimer_img.setImageResource(R.drawable.timer_normal);
        tabwatchtimer_img.setImageResource(R.drawable.watchtimer_normal);
    }

    @Override
    public void onClick(View v) {
        resetImg();
        switch (v.getId()){
            case R.id.tabtime:
                viewpager.setCurrentItem(0);
                tabtime_img.setImageResource(R.drawable.time_press);

                break;
            case R.id.tabalarm:
                viewpager.setCurrentItem(1);
                tabalarm_img.setImageResource(R.drawable.alarm_press);
                break;
            case R.id.tabtimer:
                viewpager.setCurrentItem(2);
                tabtimer_img.setImageResource(R.drawable.timer_press);
                break;
            case R.id.tabwatchtimer:
                viewpager.setCurrentItem(3);
                tabwatchtimer_img.setImageResource(R.drawable.watchtimer_press);
                break;

        }

    }
}
