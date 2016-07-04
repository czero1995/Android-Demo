package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJModel.QMCJ;
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
    private Button xinshengrukou, union, tour,
            waimai, secondtrade, jianzhizhaopin, lostfound, qmcj,
            kuaidigongjiao, searchphone, course, searchresult;

    private ViewFlow mViewFlow;
    private CircleFlowIndicator mFlowIndicator;
    private ArrayList<String> imageUrlList = new ArrayList<String>();
    ArrayList<String> linkUrlArray = new ArrayList<String>();
    ArrayList<String> titleList = new ArrayList<String>();
    private LinearLayout notice_parent_ll;
    private LinearLayout notice_ll;
    private ViewFlipper notice_vf;
    private int mCurrPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabserver);
        qmcj = (Button) findViewById(R.id.qmcj);
        xinshengrukou = (Button) findViewById(R.id.xinshengrukou);
        jianzhizhaopin = (Button) findViewById(R.id.jianzhizhaopin);
        lostfound = (Button) findViewById(R.id.lostfound);
        waimai = (Button) findViewById(R.id.waimai);
        tour = (Button) findViewById(R.id.tour);
        course = (Button) findViewById(R.id.course);
        secondtrade = (Button) findViewById(R.id.secondtrade);
        union = (Button) findViewById(R.id.union);
        kuaidigongjiao = (Button) findViewById(R.id.kuaidigongjiao);
        searchresult = (Button) findViewById(R.id.searchresult);
        searchphone = (Button) findViewById(R.id.searchphone);
        qmcj.setOnClickListener(this);
        xinshengrukou.setOnClickListener(this);
        jianzhizhaopin.setOnClickListener(this);
        lostfound.setOnClickListener(this);
        waimai.setOnClickListener(this);
        tour.setOnClickListener(this);
        course.setOnClickListener(this);
        secondtrade.setOnClickListener(this);
        union.setOnClickListener(this);
        kuaidigongjiao.setOnClickListener(this);
        searchresult.setOnClickListener(this);
        searchphone.setOnClickListener(this);
        initView();
        imageUrlList
                .add("http://bmob-cdn-1826.b0.upaiyun.com/2016/06/04/2d5b449640b5a6cc8087bdc6afd9097f.png");
        imageUrlList
                .add("http://bmob-cdn-1826.b0.upaiyun.com/2016/06/02/10a72a10402bdaff80cb5cf29e9514a1.jpg");
        imageUrlList
                .add("http://bmob-cdn-1826.b0.upaiyun.com/2016/06/02/00a98ed94019be4b80d4d9d951da92f4.jpg");
        imageUrlList
                .add("http://bmob-cdn-1826.b0.upaiyun.com/2016/06/02/2f3ba21040807672802c3fe5a68681ec.jpg");
        imageUrlList
                .add("http://bmob-cdn-1826.b0.upaiyun.com/2016/06/02/661c507240e4600c80f7720e6eebcd15.jpg");

        linkUrlArray
                .add("http://v.youku.com/v_show/id_XOTU1MTU2NDky.html?from=s1.8-1-1.2");
        linkUrlArray
                .add("http://baidu.com/");
        linkUrlArray
                .add("http://baidu.com/");
        linkUrlArray
                .add("http://baidu.com/");
        linkUrlArray
                .add("http://baidu.com");
        titleList.add("生活万种风情,因为过它的人有趣有味.");
        titleList.add("因为你有双下巴,所以碰到任何困难,不要低头.");
        titleList.add("避免了失败,也就避免了成功");
        titleList.add("合群是淘汰的开始！");
        titleList.add("我现在满心幸福,只因你在我眼前,微笑如初.");
        titleList.add("这个世界是没有公平的,不努力就注定被欺负");
        titleList.add("人生最多只有900个月,我在过去的250个月里一事无成");
        titleList.add("在这里,要么庸俗,要么孤独");
        titleList.add("人最怕的就是圈子太low,大家不成长还乐在其中");
        initBanner(imageUrlList);
        initRollNotice();
    }

    public void onBackPressed() {
        new AlertDialog.Builder(this).setTitle("确认退出汕职之家吗？")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“确认”后的操作
                        ServerActivity.this.finish();

                    }
                })
                .setNegativeButton("返回", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“返回”后的操作,这里不设置没有任何操作
                    }
                }).show();
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
            case R.id.qmcj:
                Intent intent_qmcj = new Intent(ServerActivity.this, QMCJActivity.class);
                startActivity(intent_qmcj);
                break;
            case R.id.xinshengrukou:
                Intent intent_xingshengrukou = new Intent(ServerActivity.this, NewPeople.class);
                startActivity(intent_xingshengrukou);
                break;
            case R.id.union:
                Intent intent_union = new Intent(ServerActivity.this, UnionActivity.class);
                startActivity(intent_union);
                break;
            case R.id.tour:
                Intent intent_tour = new Intent(ServerActivity.this, TourActivity.class);
                startActivity(intent_tour);
                break;
            case R.id.waimai:
                Intent intent_waimai = new Intent(ServerActivity.this, WaimaiActivity.class);
                startActivity(intent_waimai);
                break;
            case R.id.secondtrade:
                Intent intent_secondtrade = new Intent(ServerActivity.this, SecondTradeActivity.class);
                startActivity(intent_secondtrade);
                break;
            case R.id.jianzhizhaopin:
                Intent intent_jzzp = new Intent(ServerActivity.this, JZZPActivity.class);
                startActivity(intent_jzzp);
                break;
            case R.id.lostfound:
                Intent intent_lostfound = new Intent(ServerActivity.this, LostFoundActivity.class);
                startActivity(intent_lostfound);
                break;
            case R.id.kuaidigongjiao:
                Intent intent_kuaidigongjiao = new Intent(ServerActivity.this, KuaidiGongjiao.class);
                startActivity(intent_kuaidigongjiao);
                break;
            case R.id.searchphone:
                Intent intent_searchphone = new Intent(ServerActivity.this, SearchphoneActivity.class);
                startActivity(intent_searchphone);
                break;
            case R.id.course:
                Intent intent_course = new Intent(ServerActivity.this, CourseActivity.class);
                startActivity(intent_course);
                break;
            case R.id.searchresult:
                Intent intent_searchsult = new Intent(ServerActivity.this, SearchResultActivity.class);
                startActivity(intent_searchsult);
                break;


        }
    }

    private void toast(String toast) {
        Toast.makeText(ServerActivity.this, toast, Toast.LENGTH_SHORT).show();
    }
}
