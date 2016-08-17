package com.example.czero.androidstudy.ui.homefragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.czero.androidstudy.R;
import com.example.czero.androidstudy.base.BaseFragment;
import com.example.czero.androidstudy.view.s.loadmore.AutoListView;
import com.example.czero.androidstudy.view.s.loadmore.ListViewAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by filip on 8/21/2015.
 */
public class ReMenTuiJianFragment extends BaseFragment implements AutoListView.OnRefreshListener,AutoListView.OnLoadListener,AdapterView.OnItemClickListener {
    private AutoListView lstv;
    private ListViewAdapter adapter;
    private List<String> list = new ArrayList<String>();
    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            List<String> result = (List<String>) msg.obj;
            switch (msg.what) {
                case AutoListView.REFRESH:
                    lstv.onRefreshComplete();
                    list.clear();
                    list.addAll(result);
                    break;
                case AutoListView.LOAD:
                    lstv.onLoadComplete();
                    list.addAll(result);
                    break;
            }
            lstv.setResultSize(result.size());
            adapter.notifyDataSetChanged();
        };
    };
    @Override
    protected int obtainLayoutID() {
        return R.layout.home_rementuijian;
    }

    @Override
    protected void onViewCreated(View view) {
        lstv = (AutoListView)view.findViewById(R.id.lstv);
        adapter = new ListViewAdapter(getContext(), list);
        lstv.setAdapter(adapter);
        lstv.setOnRefreshListener(this);
        lstv.setOnLoadListener(this);
        lstv.setOnItemClickListener(this);
        initData();
    }
    private void initData() {
        loadData(AutoListView.REFRESH);
    }
    private void loadData(final int what) {
        // 这里模拟从服务器获取数据
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(3000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = handler.obtainMessage();
                msg.what = what;
                msg.obj = getData();
                handler.sendMessage(msg);
            }
        }).start();

    }
    // 测试数据
    public List<String> getData() {
        List<String> result = new ArrayList<String>();
        Random random = new Random();
        for (int i = 1; i < 10; i++) {
            long l = random.nextInt(10000);
            result.add("这是第：" + l+"个数据");
            result.add("这是"+i+"个数据");
        }
        return result;
    }
    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initDatas(Bundle savedInstanceState) {

    }
    @Override
    public void onLoad() {
        loadData(AutoListView.LOAD);
    }

    @Override
    public void onRefresh() {
        loadData(AutoListView.REFRESH);

    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(mContext,"测试的数据"+i,Toast.LENGTH_SHORT).show();
    }
}
