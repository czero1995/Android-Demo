package com.example.czero.androidstudy.main;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.czero.androidstudy.R;
import com.example.czero.androidstudy.view.s.PagerBottomTabStrip.Controller;
import com.example.czero.androidstudy.view.s.PagerBottomTabStrip.OnTabItemSelectListener;
import com.example.czero.androidstudy.view.s.PagerBottomTabStrip.PagerBottomTabLayout;
import com.example.czero.androidstudy.view.s.PagerBottomTabStrip.TabItemBuilder;
import com.example.czero.androidstudy.view.s.PagerBottomTabStrip.TabLayoutMode;

public class MainActivity extends AppCompatActivity {
    Controller controller;
    private HomeFragment homeFragment;
    private DiscoverFragment discoverFragment;
    private PersonFragment personFragment;
    Fragment[] fragments;
    private boolean isExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        //透明状态栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//透明导航栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.activity_main);
        initSaveInstanceState(savedInstanceState);
        BottomTabTest();
    }

    private void initSaveInstanceState(Bundle savedInstanceState) {

        if (savedInstanceState != null) {  // “内存重启”时调用
            homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(HomeFragment.newInstance().getClass().getName());
            discoverFragment = (DiscoverFragment) getSupportFragmentManager().findFragmentByTag(DiscoverFragment.newInstance().getClass().getName());
            personFragment = (PersonFragment) getSupportFragmentManager().findFragmentByTag(PersonFragment.newInstance().getClass().getName());
            fragments = new Fragment[]{homeFragment, discoverFragment, personFragment};
            getSupportFragmentManager().beginTransaction()
                    .show(homeFragment).hide(discoverFragment)
                    .hide(personFragment).commit(); // 解决重叠问题
        } else {  // 正常时
            homeFragment = HomeFragment.newInstance();
            discoverFragment = DiscoverFragment.newInstance();
            personFragment = PersonFragment.newInstance();
            fragments = new Fragment[]{homeFragment, discoverFragment, personFragment};
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frameLayout, homeFragment, homeFragment.getClass().getName())
                    .add(R.id.frameLayout, discoverFragment, discoverFragment.getClass().getName())
                    .add(R.id.frameLayout, personFragment, personFragment.getClass().getName())
                    .hide(discoverFragment).hide(personFragment).commit(); // 添加显示第一个fragment
        }

    }

    private void BottomTabTest() {
        PagerBottomTabLayout pagerBottomTabLayout = (PagerBottomTabLayout) findViewById(R.id.tab);
        TabItemBuilder tabItemBuilder = new TabItemBuilder(this).create()
                .setDefaultIcon(R.mipmap.ic_launcher)
                .setText("首页")
                .setSelectedColor(getResources().getColor(R.color.mainblue))
                .setTag("A")
                .build();

        controller = pagerBottomTabLayout.builder()
                .addTabItem(tabItemBuilder)
                .addTabItem(R.mipmap.ic_launcher, "发现", getResources().getColor(R.color.mainorange))
                .addTabItem(R.mipmap.ic_launcher, "我", getResources().getColor(R.color.mainblue))
                .setMode(TabLayoutMode.HIDE_TEXT | TabLayoutMode.CHANGE_BACKGROUND_COLOR)
                .build();
        controller.addTabItemClickListener(listener);
    }

    OnTabItemSelectListener listener = new OnTabItemSelectListener() {
        @Override
        public void onSelected(int index, Object tag) {

            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(homeFragment).hide(discoverFragment).hide(personFragment);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.frameLayout, fragments[index]);
            }
            trx.show(fragments[index]).commitAllowingStateLoss();


        }

        @Override
        public void onRepeatClick(int index, Object tag) {


        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {
                isExit = true;
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        isExit = false;
                    }
                }, 2000);
                return false;
            }
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
