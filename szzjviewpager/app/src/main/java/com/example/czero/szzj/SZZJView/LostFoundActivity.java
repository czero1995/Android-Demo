package com.example.czero.szzj.SZZJView;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJData.LostFoundBaseAdapterHelper;
import com.example.czero.szzj.SZZJData.LostFoundQuickAdapter;
import com.example.czero.szzj.SZZJModel.Found;
import com.example.czero.szzj.SZZJModel.Lost;
import com.example.czero.szzj.SZZJUtil.Util;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import static com.example.czero.szzj.R.id.tv_describe;
import static com.example.czero.szzj.R.id.tv_photo;
import static com.example.czero.szzj.R.id.tv_time;
import static com.example.czero.szzj.R.id.tv_title;
/**
 * Created by zake on 5/23/16.
 */
public class LostFoundActivity extends LostFoundBaseActivity implements View.OnClickListener {
    private SwipeRefreshLayout refreshLayout;
    RelativeLayout layout_action;
    LinearLayout layout_all;
    TextView tv_lost;
    ListView listview;
    Button btn_add;
    protected LostFoundQuickAdapter<Lost> LostAdapter;
    protected LostFoundQuickAdapter<Found> FoundAdapter;
    private Button layout_found;
    private Button layout_lost;
    PopupWindow morePop;
    RelativeLayout progress;
    LinearLayout layout_no;
    TextView tv_no;
    public static final int REQUESTCODE_ADD = 1;
    @Override
    public void setContentView() {
        setContentView(R.layout.activity_lostfound);
    }

    @Override
    public void initViews() {
        progress = (RelativeLayout) findViewById(R.id.progress);
        layout_no = (LinearLayout) findViewById(R.id.layout_no);
        tv_no = (TextView) findViewById(R.id.tv_no);

        layout_action = (RelativeLayout) findViewById(R.id.layout_action);
        layout_all = (LinearLayout) findViewById(R.id.layout_all);
        tv_lost = (TextView) findViewById(R.id.tv_lost);
        tv_lost.setTag("丢失");
        listview = (ListView) findViewById(R.id.list_lost);
        btn_add = (Button) findViewById(R.id.btn_add);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setColorSchemeResources(android.R.color.holo_orange_light);
                if(!Util.isNetworkConnected(getBaseContext())){
                    Toast.makeText(LostFoundActivity.this,"请查询网络连接",Toast.LENGTH_SHORT).show();
                    refreshLayout.setRefreshing(false);
                }else {
                    Toast.makeText(LostFoundActivity.this, "正在刷新数据", Toast.LENGTH_SHORT).show();
                    queryLosts();
                    refreshLayout.setRefreshing(false);
                }
            }
        });

    }

    @Override
    public void initListeners() {
        btn_add.setOnClickListener(this);
        layout_all.setOnClickListener(this);
    }

    @Override
    public void initData() {
        if (LostAdapter == null) {
            LostAdapter = new LostFoundQuickAdapter<Lost>(this, R.layout.lostfound_item_list) {
                @Override
                protected void convert(LostFoundBaseAdapterHelper helper, Lost lost) {
                    helper.setText(tv_title, lost.getTitle())
                            .setText(tv_describe, lost.getDescribe())
                            .setText(tv_time, lost.getCreatedAt())
                            .setText(tv_photo, lost.getPhone());
                }
            };
        }

        if (FoundAdapter == null) {
            FoundAdapter = new LostFoundQuickAdapter<Found>(this, R.layout.lostfound_item_list) {
                @Override
                protected void convert(LostFoundBaseAdapterHelper helper, Found found) {
                    helper.setText(tv_title, found.getTitle())
                            .setText(tv_describe, found.getDescribe())
                            .setText(tv_time, found.getCreatedAt())
                            .setText(tv_photo, found.getPhone());
                }
            };
        }
        listview.setAdapter(LostAdapter);
        queryLosts();
    }

    @Override
    public void onClick(View v) {
        if (v == layout_all) {
            showListPop();
        } else if (v == btn_add) {
            Intent intent = new Intent(this, LostFoundAddActivity.class);
            intent.putExtra("from", tv_lost.getTag().toString());
            startActivityForResult(intent,REQUESTCODE_ADD);
        } else if (v == layout_found) {
            changeTextView(v);
            morePop.dismiss();
            queryFounds();
        } else if (v == layout_lost) {
            changeTextView(v);
            morePop.dismiss();
            queryLosts();
        }
    }
    private void changeTextView(View v) {
        if (v == layout_found) {
            tv_lost.setTag("Found");
            tv_lost.setText("发现");
        } else {
            tv_lost.setTag("Lost");
            tv_lost.setText("丢失");
        }
    }
    private void showListPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_lost, null);
        layout_found = (Button) view.findViewById(R.id.layout_found);
        layout_lost = (Button) view.findViewById(R.id.layout_lost);
        layout_found.setOnClickListener(this);
        layout_lost.setOnClickListener(this);
        morePop = new PopupWindow(view, mScreenWidth, 600);

        morePop.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    morePop.dismiss();
                    return true;
                }
                return false;
            }
        });

        morePop.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        morePop.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        morePop.setTouchable(true);
        morePop.setFocusable(true);
        morePop.setOutsideTouchable(true);
        morePop.setBackgroundDrawable(new BitmapDrawable());
        morePop.showAsDropDown(layout_action, 0, -dip2px(this, 2.0F));
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case REQUESTCODE_ADD:
                String tag = tv_lost.getTag().toString();
                if (tag.equals("丢失")) {
                    queryLosts();
                } else {
                    queryFounds();
                }
                break;
        }
    }
    private void queryLosts() {
        showView();
        BmobQuery<Lost> query = new BmobQuery<Lost>();
        query.order("-createdAt");
        query.findObjects(this, new FindListener<Lost>() {

            @Override
            public void onSuccess(List<Lost> losts) {
                LostAdapter.clear();
                FoundAdapter.clear();
                if (losts == null || losts.size() == 0) {
                    showErrorView(0);
                    Toast.makeText(LostFoundActivity.this,"请检查数据连接!!!",Toast.LENGTH_SHORT).show();
                    LostAdapter.notifyDataSetChanged();
                    return;
                }
                progress.setVisibility(View.GONE);
                LostAdapter.addAll(losts);
                listview.setAdapter(LostAdapter);
            }

            @Override
            public void onError(int code, String arg0) {
                showErrorView(0);
            }
        });
    }

    public void queryFounds() {
        showView();
        BmobQuery<Found> query = new BmobQuery<Found>();
        query.order("-createdAt");
        query.findObjects(this, new FindListener<Found>() {

            @Override
            public void onSuccess(List<Found> arg0) {
                LostAdapter.clear();
                FoundAdapter.clear();
                if (arg0 == null || arg0.size() == 0) {
                    showErrorView(1);
                    FoundAdapter.notifyDataSetChanged();
                    return;
                }
                FoundAdapter.addAll(arg0);
                listview.setAdapter(FoundAdapter);
                progress.setVisibility(View.GONE);
            }

            @Override
            public void onError(int code, String arg0) {
                // TODO Auto-generated method stub
                showErrorView(1);
            }
        });
    }

    private void showErrorView(int tag) {
        progress.setVisibility(View.GONE);
        listview.setVisibility(View.GONE);
        layout_no.setVisibility(View.VISIBLE);
        if (tag == 0) {
//            tv_no.setText(getResources().getText(R.string.list_no_data_lost));
        } else {
//            tv_no.setText(getResources().getText(R.string.list_no_data_found));
        }
    }

    private void showView() {
        listview.setVisibility(View.VISIBLE);
        layout_no.setVisibility(View.GONE);
    }
}
