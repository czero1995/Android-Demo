package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.czero.szzj.R;

/**
 * Created by zake on 6/11/16.
 */
public class NewPeopleXYTB extends Activity {
    private String url = "http://tieba.baidu.com/f?kw=%C9%C7%CD%B7%D6%B0%D2%B5%BC%BC%CA%F5%D1%A7%D4%BA";
    private WebView xytbwebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xytb);
        xytbwebView = (WebView) findViewById(R.id.xytbwebView);

        xytbwebView.loadUrl(url);
        xytbwebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制网页在webview中去打开，false调用系统或这第三方浏览器中去打开
                view.loadUrl(url);
                return true;
            }
        });
    }

    //改写物理按键返回的逻辑


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (xytbwebView.canGoBack()) {
                xytbwebView.goBack();
                return true;

            } else {
                finish();
            }
        }

        return super.onKeyDown(keyCode, event);
    }
}
