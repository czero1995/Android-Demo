package com.example.czero.zmsz.ZMSZView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TabHost;

import com.example.czero.zmsz.R;

/**
 * Created by zake on 5/20/16.
 */
public class DiscoverActivity extends LinearLayout {
    private TabHost tabHost;

    public DiscoverActivity(Context context) {
        super(context);
    }

    public DiscoverActivity(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DiscoverActivity(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("push").setIndicator("校内速推").setContent(R.id.push));
        tabHost.addTab(tabHost.newTabSpec("happytalk").setIndicator("开心吐槽").setContent(R.id.happytalk));
        tabHost.addTab(tabHost.newTabSpec("twotrade").setIndicator("二手交易").setContent(R.id.twotrade));

    }


}
