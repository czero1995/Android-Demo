package com.example.czero.zhihu;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.czero.zhihu.adapter.NewsAdapter;
import com.example.czero.zhihu.backactivity.BaseActivity;
import com.example.czero.zhihu.bean.News;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.yalantis.phoenix.PullToRefreshView;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener,PullToRefreshView.OnRefreshListener{
        private ListView lv;
    private NewsAdapter adapter;
    private boolean isConnected;
    private LinearLayout unconnect;
    PullToRefreshView mPtrNewsList;


    public static String NEWSLIST_LATEST = "http://news-at.zhihu.com/api/4/news/latest";
    public static String STORY_VIEW = "http://daily.zhihu.com/story/";
    public static String NEWSDETAIL = "http://news-at.zhihu.com/api/4/news/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSwipeBackEnabled(true);
        unconnect = (LinearLayout) findViewById(R.id.unconnect);
        isConnected = Utility.checkNetworkConnection(this);

       mPtrNewsList= (PullToRefreshView) findViewById(R.id.pull_to_refresh);
        mPtrNewsList.setOnRefreshListener(this);


        lv = (ListView) findViewById(R.id.lv);

        lv.setOnItemClickListener(this);
        if (isConnected) {
            getData();
        } else {
            Utility.noNetworkAlert(this);
            unconnect.setVisibility(View.VISIBLE);
        }
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        NewsDetailActivity.startActivity(this, (News) adapter.getItem(position));

    }

    private void getData() {

        StringRequest request = new StringRequest(NEWSLIST_LATEST, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.i("info", s);
                try {
                    dealData(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        Volley.newRequestQueue(getApplicationContext()).add(request);
    }


    private void dealData(String s) throws JSONException {
        Gson gson = new Gson();

        Type listType = new TypeToken<ArrayList<News>>() {
        }.getType();
        JSONObject object = new JSONObject(s);
        ArrayList<News> news = gson.fromJson(object.getString("stories"), listType);
        adapter = new NewsAdapter(this, news);
        lv.setAdapter(adapter);

    }
    public void setSwipeBackEnabled(boolean isSwipeBackEnabled) {
        mSwipeBackLayout.setSwipeBackEnabled(isSwipeBackEnabled);
    }






    @Override
    public void onRefresh() {
        getData();
        mPtrNewsList.setRefreshing(false);
    }
}
