package com.example.czero.zmsz.ZMSZView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.czero.zmsz.R;
import com.example.czero.zmsz.ZMSZModel.SecondTrade;

/**
 * Created by zake on 5/20/16.
 */
public class ServerActivity extends Activity implements View.OnClickListener {
    private ImageButton lostfound,kuaidi,waimai,searchphone,secondtrade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabserver);
        lostfound = (ImageButton) findViewById(R.id.lostfound);
        waimai= (ImageButton) findViewById(R.id.waimai);
        kuaidi = (ImageButton) findViewById(R.id.kuaidi);
        secondtrade = (ImageButton) findViewById(R.id.secondtrade);
        searchphone = (ImageButton) findViewById(R.id.searchphone);
        searchphone.setOnClickListener(this);
        secondtrade.setOnClickListener(this);
        waimai.setOnClickListener(this);
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
            case R.id.waimai:
                Intent intent2= new Intent(ServerActivity.this, WaimaiActivity.class);
                startActivity(intent2);
                break;
            case R.id.searchphone:
                Intent intent3= new Intent(ServerActivity.this, SearchphoneActivity.class);
                startActivity(intent3);
                break;
            case R.id.secondtrade:
                Intent intent4= new Intent(ServerActivity.this, SecondTradeActivity.class);
                startActivity(intent4);
                break;
        }
    }

}
