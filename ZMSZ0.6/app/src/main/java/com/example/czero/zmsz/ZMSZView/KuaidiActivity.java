package com.example.czero.zmsz.ZMSZView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.czero.zmsz.R;

/**
 * Created by zake on 5/27/16.
 */
public class KuaidiActivity extends Activity {
    private String url = "http://www.kuaidi100.com/?from=openv";
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuaidi);
        webView= (WebView) findViewById(R.id.webView);
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
