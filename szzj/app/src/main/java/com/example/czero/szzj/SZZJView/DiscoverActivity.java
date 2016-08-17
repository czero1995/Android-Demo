package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJData.DiscoverListAdapter;
import com.example.czero.szzj.SZZJModel.Discover;
import com.example.czero.szzj.SmartC.SmarcActivity;
import com.example.czero.szzj.View.marqeeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;


/**
 * Created by zake on 5/20/16.
 */
public class DiscoverActivity extends Activity implements View.OnClickListener, OnItemClickListener {
    private Button news, lovewall, hbhz, amusetalk, activity, pictext, qnzz, nsns, nlzx;
    private ListView lv_discover;

    private DiscoverListAdapter discoverListAdapter;
    private List<Discover> discoverList = new ArrayList<Discover>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabdiscover);
        lv_discover = (ListView) findViewById(R.id.lv_discover);
        discoverListAdapter = new DiscoverListAdapter(this, (ArrayList<Discover>) discoverList);
        lv_discover.setAdapter(discoverListAdapter);
        lv_discover.setOnItemClickListener(this);
        news = (Button) findViewById(R.id.news);
        lovewall = (Button) findViewById(R.id.lovewall);
        hbhz = (Button) findViewById(R.id.hbhz);
        amusetalk = (Button) findViewById(R.id.amusetalk);
        activity = (Button) findViewById(R.id.activity);
        pictext = (Button) findViewById(R.id.pic_text);
        qnzz = (Button) findViewById(R.id.qnzz);
        nsns = (Button) findViewById(R.id.nsns);
        nlzx = (Button) findViewById(R.id.smartc);
        pictext = (Button) findViewById(R.id.pic_text);

        pictext.setOnClickListener(this);
        hbhz.setOnClickListener(this);
        qnzz.setOnClickListener(this);
        nsns.setOnClickListener(this);
        nlzx.setOnClickListener(this);
        news.setOnClickListener(this);
        lovewall.setOnClickListener(this);
        amusetalk.setOnClickListener(this);
        activity.setOnClickListener(this);
        getDiscoverDate();

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.news:
                Intent intent_news = new Intent(DiscoverActivity.this, NewsActivity.class);
                startActivity(intent_news);
                break;
            case R.id.lovewall:
                Intent intent_lovewall = new Intent(DiscoverActivity.this, LoveWallActivity.class);
                startActivity(intent_lovewall);
                break;
            case R.id.amusetalk:
                Intent intent_amusetalk = new Intent(DiscoverActivity.this, AmuseTalkActivity.class);
                startActivity(intent_amusetalk);
                break;
            case R.id.activity:
                Intent intent_activity = new Intent(DiscoverActivity.this, ActivityNews.class);
                startActivity(intent_activity);
                break;

            case R.id.hbhz:
                toast("功能正在完善");
                break;
            case R.id.qnzz:
                toast("功能正在完善");
                break;

            case R.id.smartc:
                Intent intent_smartc = new Intent(DiscoverActivity.this, SmarcActivity.class);
                startActivity(intent_smartc);
                break;

        }
    }

    private void getDiscoverDate() {

        BmobQuery<Discover> query = new BmobQuery<Discover>();
        query.order("--updatedAt");
        Discover discover = new Discover();

        query.findObjects(this, new FindListener<Discover>() {

            @Override
            public void onSuccess(List<Discover> object) {
//                if (object.size() == 0)
//                    toast("亲, 你来得太早了点哦");
                discoverList = object;
                // 通知Adapter数据更新
                discoverListAdapter.refresh((ArrayList<Discover>) discoverList);
                discoverListAdapter.notifyDataSetChanged();

            }

            @Override
            public void onError(int arg0, String msg) {

            }

        });
    }

    private void toast(String toast) {
        Toast.makeText(DiscoverActivity.this, toast, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent toDiscoverItem = new Intent(DiscoverActivity.this, DiscoverItemActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("discover", discoverList.get(position));
        bundle.putString("discoverID", discoverList.get(position).getObjectId());
        toDiscoverItem.putExtras(bundle);
        startActivity(toDiscoverItem);

    }

    public void onBackPressed() {
        new AlertDialog.Builder(this).setTitle("确认退出汕职之家吗？")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“确认”后的操作
                        DiscoverActivity.this.finish();

                    }
                })
                .setNegativeButton("返回", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“返回”后的操作,这里不设置没有任何操作
                    }
                }).show();
    }
}
