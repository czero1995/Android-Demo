package com.example.czero.smarttime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zake on 4/10/16.
 */
public class GuidePager extends Activity implements ViewPager.OnPageChangeListener {
    private ViewPager vp;
    private ViewPagerAdapter vpAdapter;
    private List<View> views;
    private int[] ids = { R.id.iv1, R.id.iv2, R.id.iv3 };
    private ImageView[] dots;
    private Button btn_comein;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagerguide);
        initView();
        intDots();

    }

    private void intDots() {
        dots = new ImageView[views.size()];
        for(int i =0;i<views.size();i++){
            dots[i] = (ImageView) findViewById(ids[i]);
        }
    }

    private void initView() {
        LayoutInflater inflatr = LayoutInflater.from(this);
        views = new ArrayList<View>();
        views.add(inflatr.inflate(R.layout.pager1,null));
        views.add(inflatr.inflate(R.layout.pager2,null));
        views.add(inflatr.inflate(R.layout.pager3,null));
        vpAdapter = new ViewPagerAdapter(views,this);
        vp= (ViewPager) findViewById(R.id.viewpager);
        vp.setAdapter(vpAdapter);
        btn_comein= (Button) views.get(2).findViewById(R.id.btn_comein);
        btn_comein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GuidePager.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        vp.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        for(int i =0; i <ids.length;i++){
            if (state == i) {
                dots[i].setImageResource(R.drawable.login_point_selected);
            } else {
                dots[i].setImageResource(R.drawable.login_point);
            }
        }
    }

}
