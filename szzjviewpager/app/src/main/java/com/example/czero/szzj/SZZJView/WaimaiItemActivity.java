package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.czero.szzj.R;

import com.example.czero.szzj.SZZJData.LoveItemListAdapter;

import com.example.czero.szzj.SZZJModel.Love;
import com.example.czero.szzj.SZZJModel.Shop;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class WaimaiItemActivity extends Activity {


    private Shop shop; // 当期选择的Shop
    private String shopID; // 当前选择的Shop的ID
//    private WaimaiListAdapter waimaiListAdapter;
//    private WaimaiListAdapter goodListAdapter;
    private ListView lv_waimai_menu;
    private TextView tvshopname,phone1,phone2,phone3;
    private TextView waimaimenu1, waimaimenu2, waimaimenu3, waimaimenu4,
            waimaimenu5, waimaimenu6, waimaimenu7, waimaimenu8, waimaimenu9, waimaimenu10;
    private TextView waimaiprice1,waimaiprice2,waimaiprice3,waimaiprice4,waimaiprice5,
            waimaiprice6,waimaiprice7,waimaiprice8,waimaiprice9,waimaiprice10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waimaimenu);

        // 获取到从ShopAllActivity中传递过来的Shop对象
        shop = (Shop) getIntent().getSerializableExtra("shop");
        shopID = getIntent().getStringExtra("shopID");
        initView();

    }

    private void initView() {
        tvshopname= (TextView) findViewById(R.id.tvshopname);
        phone1= (TextView) findViewById(R.id.phone1);
        phone2= (TextView) findViewById(R.id.phone2);
        phone3= (TextView) findViewById(R.id.phone3);
        waimaimenu1= (TextView) findViewById(R.id.waimaimenu1);
        waimaimenu2= (TextView) findViewById(R.id.waimaimenu2);
        waimaimenu3= (TextView) findViewById(R.id.waimaimenu3);
        waimaimenu4= (TextView) findViewById(R.id.waimaimenu4);
        waimaimenu5= (TextView) findViewById(R.id.waimaimenu5);
        waimaimenu6= (TextView) findViewById(R.id.waimaimenu6);
        waimaimenu7= (TextView) findViewById(R.id.waimaimenu7);
        waimaimenu8= (TextView) findViewById(R.id.waimaimenu8);
        waimaimenu9= (TextView) findViewById(R.id.waimaimenu9);
        waimaimenu10= (TextView) findViewById(R.id.waimaimenu10);
        waimaiprice1= (TextView) findViewById(R.id.waimaipirce1);
        waimaiprice2= (TextView) findViewById(R.id.waimaipirce2);
        waimaiprice3= (TextView) findViewById(R.id.waimaipirce3);
        waimaiprice4= (TextView) findViewById(R.id.waimaipirce4);
        waimaiprice5= (TextView) findViewById(R.id.waimaipirce5);
        waimaiprice6= (TextView) findViewById(R.id.waimaipirce6);
        waimaiprice7= (TextView) findViewById(R.id.waimaipirce7);
        waimaiprice8= (TextView) findViewById(R.id.waimaipirce8);
        waimaiprice9= (TextView) findViewById(R.id.waimaipirce9);
        waimaiprice10= (TextView) findViewById(R.id.waimaipirce10);
        tvshopname.setText(shop.getName());
        phone1.setText(shop.getPhone1());
        phone2.setText(shop.getPhone2());
        phone3.setText(shop.getPhone3());

        waimaimenu1.setText(shop.getWaimaimenu1());
        waimaimenu2.setText(shop.getWaimaimenu2());
        waimaimenu3.setText(shop.getWaimaimenu3());
        waimaimenu4.setText(shop.getWaimaimenu4());
        waimaimenu5.setText(shop.getWaimaimenu5());
        waimaimenu6.setText(shop.getWaimaimenu6());
        waimaimenu7.setText(shop.getWaimaimenu7());
        waimaimenu8.setText(shop.getWaimaimenu8());
        waimaimenu9.setText(shop.getWaimaimenu9());
        waimaimenu10.setText(shop.getWaimaimenu10());
        waimaiprice1.setText(shop.getWaimaiprice1());
        waimaiprice2.setText(shop.getWaimaiprice2());
        waimaiprice3.setText(shop.getWaimaiprice3());
        waimaiprice4.setText(shop.getWaimaiprice4());
        waimaiprice5.setText(shop.getWaimaiprice5());
        waimaiprice6.setText(shop.getWaimaiprice6());
        waimaiprice7.setText(shop.getWaimaiprice7());
        waimaiprice8.setText(shop.getWaimaiprice8());
        waimaiprice9.setText(shop.getWaimaiprice9());
        waimaiprice10.setText(shop.getWaimaiprice10());


    }



    private void toast(String toast){
        Toast.makeText(WaimaiItemActivity.this,toast,Toast.LENGTH_SHORT).show();
    }
}
