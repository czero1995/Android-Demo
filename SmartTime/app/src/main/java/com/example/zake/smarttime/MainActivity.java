package com.example.zake.smarttime;
import net.youmi.android.AdManager;
import net.youmi.android.offers.OffersManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    private TabHost tabHost;
    private watchTime watchTime;
    private NotificationManager notificationManager;
    private int notification_ID;
    private String appId = "5a31b9e674f8e6a5";
    private String appSecret="b40c9ba805c28325";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AdManager.getInstance(MainActivity.this).init(appId, appSecret, true);
        OffersManager.getInstance(MainActivity.this).onAppLaunch();
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();
        ;
        tabHost.addTab(tabHost.newTabSpec("time").setIndicator("时钟").setContent(R.id.time));
        tabHost.addTab(tabHost.newTabSpec("alarmtime").setIndicator("闹钟").setContent(R.id.alarmtime));
        tabHost.addTab(tabHost.newTabSpec("timer").setIndicator("计时器").setContent(R.id.timer));
        tabHost.addTab(tabHost.newTabSpec("watchtime").setIndicator("秒表").setContent(R.id.watchtime));
        watchTime = (com.example.zake.smarttime.watchTime) findViewById(R.id.watchtime);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        startService(new Intent(MainActivity.this, MyService.class));
        OffersManager.getInstance(MainActivity.this).showOffersWall();

    }

    @Override
    protected void onDestroy() {

        watchTime.onDestroy();
        super.onDestroy();
        OffersManager.getInstance(MainActivity.this).onAppExit();
    }
}
