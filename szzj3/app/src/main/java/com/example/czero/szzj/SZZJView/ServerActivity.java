package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.czero.szzj.R;
import com.example.czero.szzj.adbanner.BaseWebActivity;
import com.example.czero.szzj.adbanner.ImagePagerAdapter;
import com.example.czero.szzj.bannerview.CircleFlowIndicator;
import com.example.czero.szzj.bannerview.ViewFlow;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by zake on 5/20/16.
 */
public class ServerActivity extends Activity implements View.OnClickListener {
    private ImageButton donggansz,xinshengrukou, union,tour,
                        waimai,secondtrade,jianzhizhaopin, lostfound,
                        kuaidi,searchphone,course,searchresult;

    private ViewFlow mViewFlow;
    private CircleFlowIndicator mFlowIndicator;
    private ArrayList<String> imageUrlList = new ArrayList<String>();
    ArrayList<String> linkUrlArray= new ArrayList<String>();
    ArrayList<String> titleList= new ArrayList<String>();
    private LinearLayout notice_parent_ll;
    private LinearLayout notice_ll;
    private ViewFlipper notice_vf;
    private int mCurrPos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabserver);

        donggansz = (ImageButton) findViewById(R.id.donggansz);
        xinshengrukou = (ImageButton) findViewById(R.id.xinshengrukou);
        jianzhizhaopin = (ImageButton) findViewById(R.id.jianzhizhaopin);
        lostfound = (ImageButton) findViewById(R.id.lostfound);
        waimai= (ImageButton) findViewById(R.id.waimai);
        tour= (ImageButton) findViewById(R.id.tour);
        course= (ImageButton) findViewById(R.id.course);
        secondtrade = (ImageButton) findViewById(R.id.secondtrade);
        union = (ImageButton) findViewById(R.id.union);
        kuaidi = (ImageButton) findViewById(R.id.kuaidi);
        searchresult = (ImageButton) findViewById(R.id.searchresult);
        searchphone = (ImageButton) findViewById(R.id.searchphone);

       donggansz.setOnClickListener(this);
        xinshengrukou.setOnClickListener(this);
        jianzhizhaopin.setOnClickListener(this);
        lostfound.setOnClickListener(this);
        waimai.setOnClickListener(this);
        tour.setOnClickListener(this);
        course.setOnClickListener(this);
        secondtrade.setOnClickListener(this);
        union.setOnClickListener(this);
        kuaidi.setOnClickListener(this);
        searchresult.setOnClickListener(this);
        searchphone.setOnClickListener(this);
        initView();
        imageUrlList
                .add("http://bmob-cdn-1826.b0.upaiyun.com/2016/06/02/661c507240e4600c80f7720e6eebcd15.jpg");
        imageUrlList
                .add("http://bmob-cdn-1826.b0.upaiyun.com/2016/06/02/10a72a10402bdaff80cb5cf29e9514a1.jpg");
        imageUrlList
                .add("http://bmob-cdn-1826.b0.upaiyun.com/2016/06/02/00a98ed94019be4b80d4d9d951da92f4.jpg");
        imageUrlList
                .add("http://bmob-cdn-1826.b0.upaiyun.com/2016/06/02/2f3ba21040807672802c3fe5a68681ec.jpg");

        linkUrlArray
                .add("http://stzyjsxy.school.hqcr.com/");
        linkUrlArray
                .add("http://stzyjsxy.school.hqcr.com/");
        linkUrlArray
                .add("http://www.baidu.com/");
        linkUrlArray
                .add("http://www.hao123.com/");
        titleList.add("我一直在这里,等风也等你");
        titleList.add("没有一条通往光荣的道路,是铺满鲜花的");
        titleList.add("只要有你在,我就会努力 ");
        titleList.add("真的很羡慕你们,年纪轻轻就才华横溢 ");
        initBanner(imageUrlList);
        initRollNotice();

    }
    private void initView() {
        mViewFlow = (ViewFlow) findViewById(R.id.viewflow);
        mFlowIndicator = (CircleFlowIndicator) findViewById(R.id.viewflowindic);

    }
    private void initRollNotice() {
        FrameLayout main_notice = (FrameLayout) findViewById(R.id.main_notice);
        notice_parent_ll = (LinearLayout) getLayoutInflater().inflate(
                R.layout.blayout_notice, null);
        notice_ll = ((LinearLayout) this.notice_parent_ll
                .findViewById(R.id.homepage_notice_ll));
        notice_vf = ((ViewFlipper) this.notice_parent_ll
                .findViewById(R.id.homepage_notice_vf));
        main_notice.addView(notice_parent_ll);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        moveNext();
                        Log.d("Task", "下一个");
                    }
                });

            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, 4000);
    }

    private void moveNext() {
        setView(this.mCurrPos, this.mCurrPos + 1);
        this.notice_vf.setInAnimation(this, R.anim.in_bottomtop);
        this.notice_vf.setOutAnimation(this, R.anim.out_bottomtop);
        this.notice_vf.showNext();
    }

    private void setView(int curr, int next) {

        View noticeView = getLayoutInflater().inflate(R.layout.bnotice_item,
                null);
        TextView notice_tv = (TextView) noticeView.findViewById(R.id.notice_tv);
        if ((curr < next) && (next > (titleList.size() - 1))) {
            next = 0;
        } else if ((curr > next) && (next < 0)) {
            next = titleList.size() - 1;
        }
        notice_tv.setText(titleList.get(next));
        notice_tv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Bundle bundle = new Bundle();
                bundle.putString("url", linkUrlArray.get(mCurrPos));
                bundle.putString("title", titleList.get(mCurrPos));
                Intent intent = new Intent(ServerActivity.this,
                        BaseWebActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        if (notice_vf.getChildCount() > 1) {
            notice_vf.removeViewAt(0);
        }
        notice_vf.addView(noticeView, notice_vf.getChildCount());
        mCurrPos = next;

    }
    private void initBanner(ArrayList<String> imageUrlList) {

        mViewFlow.setAdapter(new ImagePagerAdapter(this, imageUrlList,
                linkUrlArray, titleList).setInfiniteLoop(true));
        mViewFlow.setmSideBuffer(imageUrlList.size()); // 实际图片张数，
        // 我的ImageAdapter实际图片张数为3

        mViewFlow.setFlowIndicator(mFlowIndicator);
        mViewFlow.setTimeSpan(4500);
        mViewFlow.setSelection(imageUrlList.size() * 1000); // 设置初始位置
        mViewFlow.startAutoFlowTimer(); // 启动自动播放
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.donggansz:
                Intent intent_donggansz=  new Intent(ServerActivity.this,DongGanSZ.class);
                startActivity(intent_donggansz);
                break;
            case R.id.xinshengrukou:
                Intent intent_xingshengrukou=  new Intent(ServerActivity.this,NewPeople.class);
                startActivity(intent_xingshengrukou);
                break;
            case R.id.union:
                Intent intent_union=  new Intent(ServerActivity.this,UnionActivity.class);
                startActivity(intent_union);
                break;
            case R.id.tour:
                Intent intent_tour=  new Intent(ServerActivity.this,TourActivity.class);
                startActivity(intent_tour);
                break;
            case R.id.waimai:
                Intent intent_waimai= new Intent(ServerActivity.this, WaimaiActivity.class);
                startActivity(intent_waimai);
                break;
            case R.id.secondtrade:
                Intent intent_secondtrade= new Intent(ServerActivity.this, SecondTradeActivity.class);
                startActivity(intent_secondtrade);
                break;
            case R.id.jianzhizhaopin:
               Intent intent_jzzp = new Intent(ServerActivity.this,JZZPActivity.class);
                startActivity(intent_jzzp);
                break;
            case R.id.lostfound:
                Intent intent_lostfound = new Intent(ServerActivity.this, LostFoundActivity.class);
                startActivity(intent_lostfound);
                break;
            case R.id.kuaidi:
                Intent intent_kuaidi= new Intent(ServerActivity.this, KuaidiActivity.class);
                startActivity(intent_kuaidi);
                break;
            case R.id.searchphone:
                Intent intent_searchphone= new Intent(ServerActivity.this, SearchphoneActivity.class);
                startActivity(intent_searchphone);
                break;
            case R.id.course:
                toast("亲,您来的太早了,该功能正在实现^_^");
                break;
            case R.id.searchresult:
                Intent intent_searchsult= new Intent(ServerActivity.this, SearchResultActivity.class);
                startActivity(intent_searchsult);
                break;



        }
    }
    private void toast(String toast){
        Toast.makeText(ServerActivity.this,toast,Toast.LENGTH_SHORT).show();
    }
}
