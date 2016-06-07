package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.czero.szzj.R;


/**
 * Created by zake on 5/20/16.
 */
public class ServerActivity extends Activity implements View.OnClickListener {
    private ImageButton donggansz,xinshengrukou,jianzhizhaopin,
                        lostfound,waimai,study,secondtrade,
                        union,kuaidi,searchresult,searchphone,goway;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabserver);
        donggansz = (ImageButton) findViewById(R.id.donggansz);
        xinshengrukou = (ImageButton) findViewById(R.id.xinshengrukou);
        jianzhizhaopin = (ImageButton) findViewById(R.id.jianzhizhaopin);
        lostfound = (ImageButton) findViewById(R.id.lostfound);
        waimai= (ImageButton) findViewById(R.id.waimai);
        study= (ImageButton) findViewById(R.id.study);
        secondtrade = (ImageButton) findViewById(R.id.secondtrade);
        union = (ImageButton) findViewById(R.id.union);
        kuaidi = (ImageButton) findViewById(R.id.kuaidi);
        searchresult = (ImageButton) findViewById(R.id.searchresult);
        searchphone = (ImageButton) findViewById(R.id.searchphone);
        goway = (ImageButton) findViewById(R.id.goway);
       donggansz.setOnClickListener(this);
        xinshengrukou.setOnClickListener(this);
        jianzhizhaopin.setOnClickListener(this);
        lostfound.setOnClickListener(this);
        waimai.setOnClickListener(this);
        study.setOnClickListener(this);
        secondtrade.setOnClickListener(this);
        union.setOnClickListener(this);
        kuaidi.setOnClickListener(this);
        searchresult.setOnClickListener(this);
        searchphone.setOnClickListener(this);
        goway.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.donggansz:
               toast("亲,您来的太早了,该功能正在实现^_^");
                break;
            case R.id.xinshengrukou:
                toast("亲,您来的太早了,该功能正在实现^_^");
                break;
            case R.id.jianzhizhaopin:
                toast("亲,您来的太早了,该功能正在实现^_^");
                break;
            case R.id.lostfound:
                Intent intent_lostfound = new Intent(ServerActivity.this, LostFoundActivity.class);
                startActivity(intent_lostfound);
                break;
            case R.id.waimai:
                Intent intent_waimai= new Intent(ServerActivity.this, WaimaiActivity.class);
                startActivity(intent_waimai);
                break;
            case R.id.study:
                toast("亲,您来的太早了,该功能正在实现^_^");
                break;
            case R.id.secondtrade:
                Intent intent_secondtrade= new Intent(ServerActivity.this, SecondTradeActivity.class);
                startActivity(intent_secondtrade);
                break;
            case R.id.union:
                toast("亲,您来的太早了,该功能正在实现^_^");
                break;
            case R.id.kuaidi:
                Intent intent_kuaidi= new Intent(ServerActivity.this, KuaidiActivity.class);
                startActivity(intent_kuaidi);
                break;
            case R.id.searchresult:
                toast("亲,您来的太早了,该功能正在实现^_^");
                break;
            case R.id.searchphone:
                Intent intent_searchphone= new Intent(ServerActivity.this, SearchphoneActivity.class);
                startActivity(intent_searchphone);
                break;
            case R.id.goway:
                toast("亲,您来的太早了,该功能正在实现^_^");
                break;

        }
    }
    private void toast(String toast){
        Toast.makeText(ServerActivity.this,toast,Toast.LENGTH_SHORT).show();
    }
}
