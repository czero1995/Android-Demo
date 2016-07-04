package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.example.czero.szzj.R;


/**
 * Created by zake on 5/27/16.
 */
public class SearchResultActivity extends Activity implements View.OnClickListener {
    private String qmcjurl = "http://www.chsi.com.cn/cet/";
    private String sljcjurl = "http://www.chsi.com.cn/cet/";
    private String jsjdjurl = "http://chaxun.neea.edu.cn/examcenter/query.cn?op=doQueryCond&sid=300&pram=results";
    private String dccsurl="http://www.way2english.com/service/chlcs.htm";
    private String studyurl="http://www.tingroom.com";
    private Button qmcj,sljcj,jsjdj,dccs,study,dati;
    private WebView searchwebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresult);
        searchwebView= (WebView) findViewById(R.id.searchwebview);
        WebSettings settings = searchwebView.getSettings();
//        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);//设置缩放功能
        settings.setDisplayZoomControls(false);//设置放大缩小按键隐藏


        qmcj= (Button) findViewById(R.id.qmcj);
        sljcj= (Button) findViewById(R.id.sljcj);
        jsjdj= (Button) findViewById(R.id.jsjdj);
        dccs= (Button) findViewById(R.id.dccs);
        study= (Button) findViewById(R.id.study);
        dati= (Button) findViewById(R.id.dati);
        qmcj.setOnClickListener(this);
        sljcj.setOnClickListener(this);
        jsjdj.setOnClickListener(this);
        dccs.setOnClickListener(this);
        study.setOnClickListener(this);
        dati.setOnClickListener(this);

    }


    public void onClick(View v) {
        switch (v.getId()){
            case R.id.qmcj:
                Toast.makeText(SearchResultActivity.this,"功能正在完善,敬请期待",Toast.LENGTH_SHORT).show();
                break;
            case R.id.sljcj:
                searchwebView.setVisibility(View.VISIBLE);
                sljcj();
                break;
            case R.id.jsjdj:
                searchwebView.setVisibility(View.VISIBLE);
                jsjdj();
                break;
            case R.id.dccs:
                searchwebView.setVisibility(View.VISIBLE);
                dccs();
                break;
            case R.id.study:
                searchwebView.setVisibility(View.VISIBLE);
                study();
                break;
            case R.id.dati:
                Toast.makeText(SearchResultActivity.this,"功能正在完善,敬请期待",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void sljcj(){
        searchwebView.loadUrl(sljcjurl);
        searchwebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制网页在webview中去打开，false调用系统或这第三方浏览器中去打开
                view.loadUrl(url);

                return true;
            }

        });

    }
    public void jsjdj(){
        searchwebView.loadUrl(jsjdjurl);
        searchwebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制网页在webview中去打开，false调用系统或这第三方浏览器中去打开
                view.loadUrl(url);
                return true;
            }

        });

    }
    public void dccs(){
        WebSettings settings = searchwebView.getSettings();
        settings.setSupportZoom(true);
        searchwebView.loadUrl(dccsurl);
        searchwebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制网页在webview中去打开，false调用系统或这第三方浏览器中去打开
                view.loadUrl(url);
                return true;
            }

        });

    }
    public void study(){
        searchwebView.loadUrl(studyurl);
        searchwebView.setWebViewClient(new WebViewClient() {
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
            if (searchwebView.canGoBack()) {
                searchwebView.goBack();
                return true;

            } else {
//                    finish();
                searchwebView.setVisibility(View.GONE);
            }
        }

        return super.onKeyDown(keyCode, event);
    }

}
