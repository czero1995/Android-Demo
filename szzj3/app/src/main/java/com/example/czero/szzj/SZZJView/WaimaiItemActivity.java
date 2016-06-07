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
            waimaimenu5, waimaimenu6, waimaimenu7, waimaimenu8, waimaimenu9, waimaimenu10,
            waimaimenu11,waimaimenu12,waimaimenu13,waimaimenu14,waimaimenu15,waimaimenu16,waimaimenu17,waimaimenu18,
            waimaimenu19,waimaimenu20,waimaimenu21,waimaimenu22,waimaimenu23,waimaimenu24,waimaimenu25,waimaimenu26,
            waimaimenu27,waimaimenu28,waimaimenu29,waimaimenu30;
    private TextView waimaiprice1,waimaiprice2,waimaiprice3,waimaiprice4,waimaiprice5,
            waimaiprice6,waimaiprice7,waimaiprice8,waimaiprice9,
            waimaiprice10,waimaiprice11,waimaiprice12,waimaiprice13,waimaiprice14,waimaiprice15,waimaiprice16,
            waimaiprice17,waimaiprice18,waimaiprice19,
            waimaiprice20,waimaiprice21,waimaiprice22,waimaiprice23,waimaiprice24,waimaiprice25,
            waimaiprice26,waimaiprice27,waimaiprice28,waimaiprice29,waimaiprice30;

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
        waimaimenu11= (TextView) findViewById(R.id.waimaimenu11);
        waimaimenu12= (TextView) findViewById(R.id.waimaimenu12);
        waimaimenu13= (TextView) findViewById(R.id.waimaimenu13);
        waimaimenu14= (TextView) findViewById(R.id.waimaimenu14);
        waimaimenu15= (TextView) findViewById(R.id.waimaimenu15);
        waimaimenu16= (TextView) findViewById(R.id.waimaimenu16);
        waimaimenu17= (TextView) findViewById(R.id.waimaimenu17);
        waimaimenu18= (TextView) findViewById(R.id.waimaimenu18);
        waimaimenu19= (TextView) findViewById(R.id.waimaimenu19);
        waimaimenu20= (TextView) findViewById(R.id.waimaimenu20);
        waimaimenu21= (TextView) findViewById(R.id.waimaimenu21);
        waimaimenu22= (TextView) findViewById(R.id.waimaimenu22);
        waimaimenu23= (TextView) findViewById(R.id.waimaimenu23);
        waimaimenu24= (TextView) findViewById(R.id.waimaimenu24);
        waimaimenu25= (TextView) findViewById(R.id.waimaimenu25);
        waimaimenu26= (TextView) findViewById(R.id.waimaimenu26);
        waimaimenu27= (TextView) findViewById(R.id.waimaimenu27);
        waimaimenu28= (TextView) findViewById(R.id.waimaimenu28);
        waimaimenu29= (TextView) findViewById(R.id.waimaimenu29);
        waimaimenu30= (TextView) findViewById(R.id.waimaimenu30);
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
        waimaiprice11= (TextView) findViewById(R.id.waimaipirce11);
        waimaiprice12= (TextView) findViewById(R.id.waimaipirce12);
        waimaiprice13= (TextView) findViewById(R.id.waimaipirce13);
        waimaiprice14= (TextView) findViewById(R.id.waimaipirce14);
        waimaiprice15= (TextView) findViewById(R.id.waimaipirce15);
        waimaiprice16= (TextView) findViewById(R.id.waimaipirce16);
        waimaiprice17= (TextView) findViewById(R.id.waimaipirce17);
        waimaiprice18= (TextView) findViewById(R.id.waimaipirce18);
        waimaiprice19= (TextView) findViewById(R.id.waimaipirce19);
        waimaiprice20= (TextView) findViewById(R.id.waimaipirce20);
        waimaiprice21= (TextView) findViewById(R.id.waimaipirce21);
        waimaiprice22= (TextView) findViewById(R.id.waimaipirce22);
        waimaiprice23= (TextView) findViewById(R.id.waimaipirce23);
        waimaiprice24= (TextView) findViewById(R.id.waimaipirce24);
        waimaiprice25= (TextView) findViewById(R.id.waimaipirce25);
        waimaiprice26= (TextView) findViewById(R.id.waimaipirce26);
        waimaiprice27= (TextView) findViewById(R.id.waimaipirce27);
        waimaiprice28= (TextView) findViewById(R.id.waimaipirce28);
        waimaiprice29= (TextView) findViewById(R.id.waimaipirce29);
        waimaiprice30= (TextView) findViewById(R.id.waimaipirce30);
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
        waimaimenu11.setText(shop.getWaimaimenu11());
        waimaimenu12.setText(shop.getWaimaimenu12());
        waimaimenu13.setText(shop.getWaimaimenu13());
        waimaimenu14.setText(shop.getWaimaimenu14());
        waimaimenu15.setText(shop.getWaimaimenu15());
        waimaimenu16.setText(shop.getWaimaimenu16());
        waimaimenu17.setText(shop.getWaimaimenu17());
        waimaimenu18.setText(shop.getWaimaimenu18());
        waimaimenu19.setText(shop.getWaimaimenu19());
        waimaimenu20.setText(shop.getWaimaimenu20());
        waimaimenu21.setText(shop.getWaimaimenu21());
        waimaimenu22.setText(shop.getWaimaimenu22());
        waimaimenu23.setText(shop.getWaimaimenu23());
        waimaimenu24.setText(shop.getWaimaimenu24());
        waimaimenu25.setText(shop.getWaimaimenu25());
        waimaimenu26.setText(shop.getWaimaimenu26());
        waimaimenu27.setText(shop.getWaimaimenu27());
        waimaimenu28.setText(shop.getWaimaimenu28());
        waimaimenu29.setText(shop.getWaimaimenu29());
        waimaimenu30.setText(shop.getWaimaimenu30());
        waimaiprice1.setText(shop.getWaimaiprice1());
        waimaiprice2.setText("$"+shop.getWaimaiprice2());
        waimaiprice3.setText("$"+shop.getWaimaiprice3());
        waimaiprice4.setText("$"+shop.getWaimaiprice4());
        waimaiprice5.setText("$"+shop.getWaimaiprice5());
        waimaiprice6.setText("$"+shop.getWaimaiprice6());
        waimaiprice7.setText("$"+shop.getWaimaiprice7());
        waimaiprice8.setText("$"+shop.getWaimaiprice8());
        waimaiprice9.setText("$"+shop.getWaimaiprice9());
        waimaiprice10.setText("$"+shop.getWaimaiprice10());
        waimaiprice11.setText("$"+shop.getWaimaiprice11());
        waimaiprice12.setText("$"+shop.getWaimaiprice12());
        waimaiprice13.setText("$"+shop.getWaimaiprice13());
        waimaiprice14.setText("$"+shop.getWaimaiprice14());
        waimaiprice15.setText("$"+shop.getWaimaiprice15());
        waimaiprice16.setText("$"+shop.getWaimaiprice16());
        waimaiprice17.setText("$"+shop.getWaimaiprice17());
        waimaiprice18.setText("$"+shop.getWaimaiprice18());
        waimaiprice19.setText("$"+shop.getWaimaiprice19());
        waimaiprice20.setText("$"+shop.getWaimaiprice20());
        waimaiprice21.setText("$"+shop.getWaimaiprice21());
        waimaiprice22.setText("$"+shop.getWaimaiprice22());
        waimaiprice23.setText("$"+shop.getWaimaiprice23());
        waimaiprice24.setText("$"+shop.getWaimaiprice24());
        waimaiprice25.setText("$"+shop.getWaimaiprice25());
        waimaiprice26.setText("$"+shop.getWaimaiprice26());
        waimaiprice27.setText("$"+shop.getWaimaiprice27());
        waimaiprice28.setText("$"+shop.getWaimaiprice28());
        waimaiprice29.setText("$"+shop.getWaimaiprice29());
        waimaiprice30.setText("$"+shop.getWaimaiprice30());


    }



    private void toast(String toast){
        Toast.makeText(WaimaiItemActivity.this,toast,Toast.LENGTH_SHORT).show();
    }
}
