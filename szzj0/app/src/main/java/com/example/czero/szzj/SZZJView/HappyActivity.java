package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJData.HappyListAdapter;

import cn.bmob.v3.BmobUser;

/**
 * Created by zake on 5/20/16.
 */
public class HappyActivity extends Activity implements AdapterView.OnItemClickListener {
    private String[] userItemNames = {"zmsz"};
    private String[] userItemContents = {""};
    private String[] goItemNames = {"住宿", "出行"};
    private String[] goItemContents = {"", ""};
    private String[] amuseItemNames = {"出车旅游", "KTV", "推荐给朋友", "退出账号"};
    private String[] amuseItemContents = {"", "", "", ""};
    private int[] userImgIds = {R.drawable.ic_menu_myplaces};
    private int[] goImgIds = {R.drawable.ic_menu_find_holo_light, R.drawable.ic_menu_copy_holo_light};
    private int[] amuseImgIds = {R.drawable.ic_menu_notifications, R.drawable.ic_menu_info_details, R.drawable.ic_menu_share, R.drawable.ic_star_yes};
    private ListView lvMinUser;
    private ListView lvGo;
    private ListView lvAmuse;
    private HappyListAdapter userListAdapter;
    private HappyListAdapter goListAdapter;
    private HappyListAdapter amuseListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabhappy);
        lvMinUser = (ListView) findViewById(R.id.lv_mine_user);
        lvGo = (ListView) findViewById(R.id.lv_go);
        lvAmuse = (ListView) findViewById(R.id.lv_amuse);
        userListAdapter = new HappyListAdapter(this, userItemNames, userItemContents, userImgIds);
        goListAdapter = new HappyListAdapter(this, goItemNames, goItemContents, goImgIds);
        amuseListAdapter = new HappyListAdapter(this, amuseItemNames, amuseItemContents, amuseImgIds);
        lvMinUser.setAdapter(userListAdapter);
        lvGo.setAdapter(goListAdapter);
        lvAmuse.setAdapter(amuseListAdapter);
        lvMinUser.setOnItemClickListener(this);
        lvGo.setOnItemClickListener(this);
        lvAmuse.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.lv_mine_user) {
            switch (position) {
                case 0:
                    Intent toMineInfo = new Intent(HappyActivity.this, MineInfoActivity.class);
                    startActivity(toMineInfo);
                    break;
            }
        }
        if (parent.getId() == R.id.lv_go) {
            switch (position) {
                case 0:
                    toast("住宿");
                    break;
                case 1:
                    toast("出行");
                    break;
            }
        }
        if (parent.getId() == R.id.lv_amuse) {
            switch (position) {
                case 0:
                    toast("出车旅游");
                    break;
                case 1:
                    toast("佳佳KTV");
                    break;
                case 2:
                    toast("推荐给朋友");
                    break;
                case 3:
                    BmobUser.logOut(this);
                    Intent toLogin = new Intent(HappyActivity.this, LoginActivity.class);
                    startActivity(toLogin);
                    finish();
                    break;
            }
        }
    }


    private void toast(String toast) {
        Toast.makeText(HappyActivity.this, toast, Toast.LENGTH_SHORT).show();
    }
}

