package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJData.NewsItemListAdapter;
import com.example.czero.szzj.SZZJModel.Love;
import com.example.czero.szzj.SZZJModel.News;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by zake on 5/28/16.
 */
public class NewsActivity extends Activity{
    private ListView lv_news;
    private List<News> news= new ArrayList<News>();
    private NewsItemListAdapter newsItemListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        lv_news= (ListView) findViewById(R.id.lv_news);
        newsItemListAdapter=new NewsItemListAdapter(NewsActivity.this, (ArrayList<News>) news);
        lv_news.setAdapter(newsItemListAdapter);
        getNewsData();
    }
    private void getNewsData() {
        BmobQuery<News> query = new BmobQuery<News>();
        query.order("-createdAt");
        query.findObjects(this, new FindListener<News>() {

            @Override
            public void onSuccess(List<News> object) {
                //toast("查询成功. 共计" + object.size());
                if(object.size()==0)
                    toast("亲, 你来得太早了点哦");
                else {
                    news = object;
                    // 通知Adapter数据更新
                    newsItemListAdapter.refresh((ArrayList<News>) news);
                    //tradeItemListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(int arg0, String msg) {
                toast("查询失败:"+msg);
            }

        });
    }
    public void toast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }

}
