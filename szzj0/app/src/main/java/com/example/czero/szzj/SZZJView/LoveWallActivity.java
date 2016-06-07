package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJData.LoveItemListAdapter;
import com.example.czero.szzj.SZZJModel.Love;
import com.example.czero.szzj.SZZJUtil.Util;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;


/**
 * Created by zake on 5/28/16.
 */
public class LoveWallActivity extends Activity implements AdapterView.OnItemClickListener{
    private ListView lvlovewall;
    private SwipeRefreshLayout refreshlove;
    private Button btn_love;
    private EditText etlovetalk;
    private LoveItemListAdapter loveItemListAdapter;
    private List<Love> love= new ArrayList<Love>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lovewall);
        refreshlove= (SwipeRefreshLayout) findViewById(R.id.refresh_love);
        refreshlove.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshlove.setColorSchemeResources(android.R.color.holo_orange_light);
                if(!Util.isNetworkConnected(getBaseContext())){
                    Toast.makeText(LoveWallActivity.this,"请查询网络连接",Toast.LENGTH_SHORT).show();
                    refreshlove.setRefreshing(false);
                }else {
                    Toast.makeText(LoveWallActivity.this, "正在寻找痴情种子(*^__^*)! ", Toast.LENGTH_SHORT).show();
                    getLoveData();
                    refreshlove.setRefreshing(false);
                }
            }
        });
        lvlovewall= (ListView) findViewById(R.id.lv_lovewall);
        etlovetalk= (EditText) findViewById(R.id.etlovetalk);
        btn_love= (Button) findViewById(R.id.btn_love);
        loveItemListAdapter = new LoveItemListAdapter(LoveWallActivity.this, (ArrayList<Love>) love);
        lvlovewall.setAdapter(loveItemListAdapter);
        lvlovewall.setOnItemClickListener(this);
        getLoveData();
        btn_love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Love love = new Love();
                String description = etlovetalk.getText().toString();
                if(description.equals("")){
                    toast("输入点什么吧!(*^__^*) ");
                }else{
                    love.setDescription(description);
                    love.save(LoveWallActivity.this,new SaveListener() {
                        @Override
                        public void onSuccess() {
                            toast("表白成功");
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            toast(s);
                        }
                    });
                    getLoveData();
                }

            }
        });
    }
    private void getLoveData() {
        BmobQuery<Love> query = new BmobQuery<Love>();
        query.order("-createdAt");
        query.findObjects(this, new FindListener<Love>() {

            @Override
            public void onSuccess(List<Love> object) {
                //toast("查询成功. 共计" + object.size());
                if(object.size()==0)
                    toast("亲, 你来得太早了点哦");
                else {
                    love = object;
                    // 通知Adapter数据更新
                    loveItemListAdapter.refresh((ArrayList<Love>) love);
                    //tradeItemListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(int arg0, String msg) {
                toast("查询失败:"+msg);
            }

        });
    }

    private void toast(String toast) {
        Toast.makeText(this, toast,  Toast.LENGTH_SHORT).show();
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
