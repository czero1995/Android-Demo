package com.example.czero.zhihu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.czero.zhihu.backactivity.BaseActivity;
import com.example.czero.zhihu.bean.News;
import com.example.czero.zhihu.http.LoadNewsDetailTask;

import static android.webkit.WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING;


/**
 * Created by czero on 7/26/16.
 */

public class NewsDetailActivity extends BaseActivity {
//    private SwipeBackController swipeBackController;
    private WebView mWebView;
    private News news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_detail);
        mWebView = (WebView) findViewById(R.id.webview);
        setWebView(mWebView);
        news = (News) getIntent().getSerializableExtra("news");
        new LoadNewsDetailTask(mWebView).execute(news.getId());
//        swipeBackController = new SwipeBackController(this);

    }
    private void setWebView(WebView mWebView) {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setLayoutAlgorithm(TEXT_AUTOSIZING);
       mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);
    }

    public static void startActivity(Context context, News news) {
        if (Utility.checkNetworkConnection(context)) {
            Intent i = new Intent(context, NewsDetailActivity.class);
            i.putExtra("news", news);
            context.startActivity(i);
        } else {
            Utility.noNetworkAlert(context);
        }
    }


//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        if (swipeBackController.processEvent(ev)) {
//            return true;
//        } else {
//            return super.onTouchEvent(ev);
//        }
//    }
}
