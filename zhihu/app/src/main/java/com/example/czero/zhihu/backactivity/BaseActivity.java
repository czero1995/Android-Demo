package com.example.czero.zhihu.backactivity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.czero.zhihu.R;

public class BaseActivity extends Activity {

    protected SwipeBackLayout mSwipeBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSwipeBackLayout = new SwipeBackLayout(this);
        mSwipeBackLayout.attachToActivity(this);
        mSwipeBackLayout.setBgTransparent();

        // enable status bar tint
        // enable navigation bar tint
        if (hasKitKat() && !hasLollipop()) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        } else if (hasLollipop()) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS |
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    // | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(getColor(R.color.title_bar_color));
        }

    }


    /**
     * 是否禁用滑动返回
     */
    public void setSwipeBackEnabled(boolean isSwipeBackEnabled) {
        mSwipeBackLayout.setSwipeBackEnabled(isSwipeBackEnabled);
    }

    public static boolean hasKitKat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
}
