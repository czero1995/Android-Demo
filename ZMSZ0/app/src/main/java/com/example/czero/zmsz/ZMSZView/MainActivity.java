package com.example.czero.zmsz.ZMSZView;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.czero.zmsz.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private List<View> mview = new ArrayList<View>();
    private LinearLayout tabServer;
    private LinearLayout tabDiscover;
    private LinearLayout tabHappy;
    private ImageButton tabServer_img;
    private ImageButton tabDiscover_img;
    private ImageButton tabHappy_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }
    private void initEvent() {
        tabServer.setOnClickListener(this);
        tabDiscover.setOnClickListener(this);
        tabHappy.setOnClickListener(this);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                int currentItem = viewPager.getCurrentItem();
//                switch (currentItem){
//                    case 0 :
//                        Toast.makeText(MainActivity.this,"1",Toast.LENGTH_SHORT).show();
//                        break;
//                    case 1:
//                        Toast.makeText(MainActivity.this,"2",Toast.LENGTH_SHORT).show();
//                        break;
//                    case 2:
//                        Toast.makeText(MainActivity.this,"3",Toast.LENGTH_SHORT).show();
//                        break;
//                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabServer = (LinearLayout) findViewById(R.id.tab_server);
        tabDiscover = (LinearLayout) findViewById(R.id.tab_discover);
        tabHappy = (LinearLayout) findViewById(R.id.tab_happy);
        tabServer_img = (ImageButton) findViewById(R.id.tab_server_img);
        tabDiscover_img = (ImageButton) findViewById(R.id.tab_discover_img);
        tabHappy_img = (ImageButton) findViewById(R.id.tab_happy_img);
        LayoutInflater inflater = LayoutInflater.from(this);
        View server = inflater.inflate(R.layout.activity_tabserver, null);
        View discover = inflater.inflate(R.layout.activity_tabdiscover, null);
        View happy = inflater.inflate(R.layout.activity_tabhappy, null);
        mview.add(server);
        mview.add(discover);
        mview.add(happy);
        pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return mview.size();
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = mview.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mview.get(position));
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        };
        viewPager.setAdapter(pagerAdapter);
    }

//    private void resetImg() {
//    tabServer_img.setImageResource(null);
//    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab_server:
                viewPager.setCurrentItem(0);
                Toast.makeText(MainActivity.this,"server",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tab_discover:
                viewPager.setCurrentItem(1);
                Toast.makeText(MainActivity.this,"discover",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tab_happy:
                viewPager.setCurrentItem(2);
                Toast.makeText(MainActivity.this,"happy",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
