package com.example.czero.zmsz.ZMSZView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.czero.zmsz.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zake on 5/20/16.
 */
public class ServerActivity extends Activity implements View.OnClickListener {
    private ImageButton lostfound,kuaidi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabserver);
        lostfound = (ImageButton) findViewById(R.id.lostfound);
        kuaidi = (ImageButton) findViewById(R.id.kuaidi);
        lostfound.setOnClickListener(this);
        kuaidi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lostfound:
                Intent intent = new Intent(ServerActivity.this, LostFoundActivity.class);
                startActivity(intent);
                break;
            case R.id.kuaidi:
                Intent intent1= new Intent(ServerActivity.this, KuaidiActivity.class);
                startActivity(intent1);
                break;
        }
    }

}
