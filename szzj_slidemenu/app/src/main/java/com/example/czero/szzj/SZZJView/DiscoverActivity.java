package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJModel.News;


/**
 * Created by zake on 5/20/16.
 */
public class DiscoverActivity extends Activity implements View.OnClickListener{
    private Button news,lovewall,amusetalk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabdiscover);
        news= (Button) findViewById(R.id.news);
        lovewall= (Button) findViewById(R.id.lovewall);
        amusetalk= (Button) findViewById(R.id.amusetalk);
        news.setOnClickListener(this);
        lovewall.setOnClickListener(this);
        amusetalk.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.news:
                Intent intent_news=new Intent(DiscoverActivity.this,NewsActivity.class);
                startActivity(intent_news);
                break;
            case R.id.lovewall:
                Intent intent_lovewall=new Intent(DiscoverActivity.this,LoveWallActivity.class);
                startActivity(intent_lovewall);
                break;
            case R.id.amusetalk:
                Intent intent_amusetalk=new Intent(DiscoverActivity.this,AmuseTalkActivity.class);
                startActivity(intent_amusetalk);
                break;
        }
    }
}
