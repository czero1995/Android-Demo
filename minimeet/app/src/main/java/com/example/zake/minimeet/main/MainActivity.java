package com.example.zake.minimeet.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.zake.minimeet.R;
import com.example.zake.minimeet.view.bottomtabstrip.Controller;
import com.example.zake.minimeet.view.bottomtabstrip.OnTabItemSelectListener;
import com.example.zake.minimeet.view.bottomtabstrip.PagerBottomTabLayout;
import com.example.zake.minimeet.view.bottomtabstrip.TabItemBuilder;
import com.example.zake.minimeet.view.bottomtabstrip.TabLayoutMode;

public class MainActivity extends AppCompatActivity {
    Controller controller;
    private HomeFragment homeFragment;
    private OrderFragment orderFragment;
    private PersonFragment personFragment;
    Fragment[] fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    initSaveInstanceState(savedInstanceState);
        BottomTabTest();
    }


    private void initSaveInstanceState(Bundle savedInstanceState) {

        if (savedInstanceState != null) {  // “内存重启”时调用
            homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(homeFragment.newInstance().getClass().getName());
           orderFragment  = (OrderFragment) getSupportFragmentManager().findFragmentByTag(OrderFragment.newInstance().getClass().getName());
            personFragment = (PersonFragment) getSupportFragmentManager().findFragmentByTag(PersonFragment.newInstance().getClass().getName());
            fragments = new Fragment[]{homeFragment,orderFragment,personFragment};
            getSupportFragmentManager().beginTransaction()
                    .show(homeFragment).hide(orderFragment)
                    .hide(personFragment).commit(); // 解决重叠问题
        } else {  // 正常时
            homeFragment = HomeFragment.newInstance();
            orderFragment = OrderFragment.newInstance();
            personFragment = PersonFragment.newInstance();
            fragments = new Fragment[]{homeFragment, orderFragment, personFragment};
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frameLayout, homeFragment, homeFragment.getClass().getName())
                    .add(R.id.frameLayout, orderFragment, orderFragment.getClass().getName())
                    .add(R.id.frameLayout, personFragment, personFragment.getClass().getName())
                    .hide(orderFragment).hide(personFragment).commit(); // 添加显示第一个fragment
        }

    }

    private void BottomTabTest() {
        PagerBottomTabLayout pagerBottomTabLayout = (PagerBottomTabLayout) findViewById(R.id.tab);
        TabItemBuilder tabItemBuilder = new TabItemBuilder(this).create()
                .setDefaultIcon(R.mipmap.ic_launcher)
                .setText("首页")
                .setSelectedColor(getResources().getColor(R.color.lightblue))
                .setTag("A")
                .build();

        controller = pagerBottomTabLayout.builder()
                .addTabItem(tabItemBuilder)
                .addTabItem(R.mipmap.ic_launcher, "约见", getResources().getColor(R.color.mainblue))
                .addTabItem(R.mipmap.ic_launcher, "我", getResources().getColor(R.color.lightblue))
                .setMode(TabLayoutMode.HIDE_TEXT | TabLayoutMode.CHANGE_BACKGROUND_COLOR)
                .build();
        controller.addTabItemClickListener(listener);
    }

    OnTabItemSelectListener listener = new OnTabItemSelectListener() {
        @Override
        public void onSelected(int index, Object tag) {

            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(homeFragment).hide(orderFragment).hide(personFragment);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.frameLayout, fragments[index]);
            }
            trx.show(fragments[index]).commitAllowingStateLoss();



        }

        @Override
        public void onRepeatClick(int index, Object tag) {


        }
    };

    }

