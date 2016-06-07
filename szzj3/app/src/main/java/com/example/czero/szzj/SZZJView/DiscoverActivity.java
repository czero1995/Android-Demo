package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJModel.News;


/**
 * Created by zake on 5/20/16.
 */
public class DiscoverActivity extends Activity implements View.OnClickListener{
    private Button news,lovewall,amusetalk;
    private Button szgl,chgl,lygl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabdiscover);
        news= (Button) findViewById(R.id.news);
        lovewall= (Button) findViewById(R.id.lovewall);
        amusetalk= (Button) findViewById(R.id.amusetalk);
        szgl= (Button) findViewById(R.id.szgl);
        chgl= (Button) findViewById(R.id.chgl);
        lygl= (Button) findViewById(R.id.lygl);
        news.setOnClickListener(this);
        lovewall.setOnClickListener(this);
        amusetalk.setOnClickListener(this);
        szgl.setOnClickListener(this);
        chgl.setOnClickListener(this);
        lygl.setOnClickListener(this);

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
            case R.id.szgl:
                toast("功能正在完善,尽请期待!");
                break;
            case R.id.chgl:
                toast("功能正在完善,尽请期待!");
                break;
            case R.id.lygl:
                toast("功能正在完善,尽请期待!");

        }
    }
    private void toast(String toast){
        Toast.makeText(DiscoverActivity.this,toast,Toast.LENGTH_SHORT).show();
    }
}
