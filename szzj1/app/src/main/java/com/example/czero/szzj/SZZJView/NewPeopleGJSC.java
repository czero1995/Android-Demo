package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.czero.szzj.R;

/**
 * Created by zake on 6/24/16.
 */
public class NewPeopleGJSC extends Activity{
    private String url = "http://mbus.mapbar.com/shantou/xianlu";
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gjsc);
        webView= (WebView) findViewById(R.id.webViewgjsc);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
//        settings.setSupportZoom(true);
//        settings.setBuiltInZoomControls(true);//设置缩放功能
//        settings.setDisplayZoomControls(false);//设置放大缩小按键隐藏

        kuaidi();

    }

    public void kuaidi(){
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制网页在webview中去打开，false调用系统或这第三方浏览器中去打开
                view.loadUrl(url);
                return true;
            }

        });

    }

    //改写物理按键返回的逻辑

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();
                return true;

            } else {
                finish();
            }
        }

        return super.onKeyDown(keyCode, event);
    }
}

