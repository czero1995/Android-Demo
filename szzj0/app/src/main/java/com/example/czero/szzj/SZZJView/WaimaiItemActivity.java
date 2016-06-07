package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;


import com.example.czero.szzj.R;

import com.example.czero.szzj.SZZJModel.Shop;

import java.util.List;

public class WaimaiItemActivity extends Activity {
    private List<String> titleList; // viewpager的标题


    // 从上级页面中传入的数据
    private Shop shop; // 当期选择的Shop
    private String shopID; // 当前选择的Shop的ID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_item);

        // 获取到从ShopAllActivity中传递过来的Shop对象
        shop = (Shop) getIntent().getSerializableExtra("shop");
        shopID = getIntent().getStringExtra("shopID");
    }

}
