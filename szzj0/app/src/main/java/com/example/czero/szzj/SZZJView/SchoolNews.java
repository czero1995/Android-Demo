package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJData.AutoScrollViewPager;
import com.example.czero.szzj.SZZJData.ImagePagerAdapter;
import com.example.czero.szzj.SZZJData.ListScrollView;
import com.example.czero.szzj.SZZJData.NewsListAdapter;
import com.example.czero.szzj.SZZJData.TypeDef;
import com.example.czero.szzj.SZZJModel.News;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by zake on 5/28/16.
 */
public class SchoolNews extends Activity implements View.OnClickListener,
        AdapterView.OnItemClickListener {
    private ListScrollView listScrollView;

    // 校历
    private TextView tvWeek; // 周次和星期
    private TextView tvDay; // 年月日

    // 图片轮播
    private FrameLayout flImageAds;
    private AutoScrollViewPager viewPager;
    private List<View> mImgViews;
    private ImageButton btnHideAds;
    private int[] mImgResId = { R.drawable.ic_banner1, R.drawable.ic_banner1,
            R.drawable.ic_banner1, R.drawable.ic_banner1 };

    // 校园新闻
    private ListView lvNewsList;
    private List<News> newsList = new ArrayList<News>();
    private NewsListAdapter newsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schoolnews);

        // 解决ScrollView和ListView之间的冲突
        listScrollView = (ListScrollView) findViewById(R.id.listScrollView);
        lvNewsList = (ListView) findViewById(R.id.lv_news);
        listScrollView.setListView(lvNewsList);

        flImageAds = (FrameLayout) findViewById(R.id.fl_image_ads);
        viewPager = (AutoScrollViewPager) findViewById(R.id.view_pager);
        btnHideAds = (ImageButton) findViewById(R.id.btn_hide_ads);

        mImgViews = new ArrayList<View>();
        for (int i = 0; i < mImgResId.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(mImgResId[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mImgViews.add(imageView);
        }

        btnHideAds.setOnClickListener(this);

        viewPager.setAdapter(new ImagePagerAdapter(this, mImgViews));
        viewPager.setInterval(3000); // 设置自动滚动的间隔时间，单位为毫秒
        viewPager.setDirection(AutoScrollViewPager.RIGHT); // 设置自动滚动的方向，默认向右
        viewPager.setCycle(true); // 是否自动循环轮播，默认为true
        viewPager.setScrollDurationFactor(3); // 设置ViewPager滑动动画间隔时间的倍率，达到减慢动画或改变动画速度的效果
        viewPager.setStopScrollWhenTouch(true); // 当手指碰到ViewPager时是否停止自动滚动，默认为true
        viewPager.setBorderAnimation(true); // 设置循环滚动时滑动到从边缘滚动到下一个是否需要动画，默认为true
        viewPager
                .setSlideBorderMode(AutoScrollViewPager.SLIDE_BORDER_MODE_NONE);// 滑动到第一个或最后一个Item的处理方式，支持没有任何操作、轮播以及传递到父View三种模式

        viewPager.startAutoScroll();

        // 校历
        tvWeek = (TextView) findViewById(R.id.tv_week);
        tvDay = (TextView) findViewById(R.id.tv_day);
        setTime();

        // 新闻
        newsListAdapter = new NewsListAdapter(this, newsList);
        lvNewsList.setAdapter(newsListAdapter);
        lvNewsList.setOnItemClickListener(this);

        getNewsData();
    }

    /**
     * 设置校历中日期的时间
     */
    public void setTime() {
        Calendar calendar = Calendar.getInstance();
        String year = calendar.get(Calendar.YEAR)+"";
        String month = calendar.get(Calendar.MONTH)+1+"";
        String day = calendar.get(Calendar.DAY_OF_MONTH)+"";
        String week = calendar.get(Calendar.WEEK_OF_YEAR)-9+"";
        String dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)+"";
        String chDayOfWeek = TypeDef.chDayOfWeek[Integer.parseInt(dayOfWeek)-1];
        toast(year+"-"+month+"-"+day+" "+" 第 "+week+" 周 "+" "+" 星期 "+chDayOfWeek);
        tvWeek.setText(" 第 "+week+" 周 "+" "+" 星期 "+chDayOfWeek);
        tvDay.setText(year+" 年 "+month+" 月 "+day+" 日");
    }

    /**
     * 初始化新闻列表数据
     * @date 2014-5-3
     * @author Stone
     */
    public void getNewsData() {
        BmobQuery<News> query = new BmobQuery<News>();
        query.order("-updatedAt");
        query.findObjects(this, new FindListener<News>() {

            @Override
            public void onSuccess(List<News> object) {
                newsList = object;
                // 通知Adapter数据更新
                newsListAdapter.refresh((ArrayList<News>) newsList);
                newsListAdapter.notifyDataSetChanged();
                toast("success");
            }

            @Override
            public void onError(int arg0, String arg1) {
                toast("都怪小菜我, 获取数据失败了");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // start auto scroll when onResume
        viewPager.startAutoScroll();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_hide_ads:
                flImageAds.setVisibility(View.GONE);
                break;

            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Intent toNewsDetail = new Intent(SchoolNews.this, NewsActivity.class);
        toNewsDetail.putExtra("NewsID", newsList.get(position).getObjectId());
        toNewsDetail.putExtra("NewsTitle", newsList.get(position).getTitle());
        toNewsDetail.putExtra("NewsAuthor", newsList.get(position).getAuthor());
        toNewsDetail.putExtra("NewsTime", newsList.get(position).getCreatedAt());
        toNewsDetail.putExtra("NewsContent", newsList.get(position).getContent());
        startActivity(toNewsDetail);
    }

    public void toast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT);
    }

    public void clickBack(View v) {
        finish();
    }


}
