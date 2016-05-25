package com.example.czero.zmsz.ZMSZView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.czero.zmsz.R;
import com.example.czero.zmsz.ZMSZData.MineListAdapter;

import cn.bmob.v3.BmobUser;

/**
 * Created by zake on 5/20/16.
 */
public class HappyActivity extends Activity implements AdapterView.OnItemClickListener{
    private String[] userItemNames = {"zmsz"} ;
    private String[] userItemContents = {""} ;
    private String[] goItemNames = {"住宿", "出行"};
    private String[] goItemContents = {"", ""};
    private String[] amuseItemNames = {"出车旅游", "KTV", "推荐给朋友", "退出账号"};
    private String[] amuseItemContents = {"", "", "", ""};
    private int[] userImgIds = {R.drawable.ic_menu_myplaces};
    private int[] goImgIds = {R.drawable.ic_menu_find_holo_light,R.drawable.ic_menu_copy_holo_light};
    private int[] amuseImgIds={R.drawable.ic_menu_notifications, R.drawable.ic_menu_info_details, R.drawable.ic_menu_share, R.drawable.ic_star_yes};
    private ListView lvMinUser;
    private ListView lvGo;
    private ListView lvAmuse;
    private MineListAdapter userListAdapter;
    private MineListAdapter goListAdapter;
    private MineListAdapter amuseListAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabhappy);
        lvMinUser= (ListView) findViewById(R.id.lv_mine_user);
        lvGo= (ListView) findViewById(R.id.lv_go);
        lvAmuse= (ListView) findViewById(R.id.lv_amuse);
        userListAdapter = new MineListAdapter(this,userItemNames,userItemContents,userImgIds);
        goListAdapter = new MineListAdapter(this,goItemNames,goItemContents,goImgIds);
        amuseListAdapter = new MineListAdapter(this,amuseItemNames,amuseItemContents,amuseImgIds);
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
                    toast("aaaaaaa");
                    Intent toMineInfo = new Intent(HappyActivity.this, MineInfoActivity.class);
                    startActivity(toMineInfo);
                    break;
            }
            if (parent.getId() == R.id.lv_go) {
                //toast("点击了订单区域");
                Intent toOrderInfo;
                switch (position) {
                    case 0:
                        toast("aaaaaaa");
                        break;
                    case 1:
                        toast("aaaaaaa");
                        break;
                    default:
                        break;
                }
            }

            //其他
            if (parent.getId() == R.id.lv_amuse) {

                switch (position) {
                    case 1:    //软件相关
                        toast("aaaaaaa");
                        break;
                    case 2:        //推荐给朋友
                        toast("aaaaaaa");
                        break;
                    case 3:        //退出当期账号
                        toast("aaaaaaa");
                        break;

                }
            }
        }
    }
    private void toast(String toast){
        Toast.makeText(HappyActivity.this,toast,Toast.LENGTH_SHORT).show();
    }
}

