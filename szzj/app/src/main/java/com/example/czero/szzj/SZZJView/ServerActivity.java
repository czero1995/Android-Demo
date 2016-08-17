package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;



import com.example.czero.szzj.R;

import com.example.czero.szzj.SmartC.SmarcActivity;
import com.example.czero.szzj.View.CircularAnimUtil;
import com.example.czero.szzj.View.ExpandableButtonMenu.ExpandableButtonMenu;
import com.example.czero.szzj.View.ExpandableButtonMenu.ExpandableMenuOverlay;
import com.example.czero.szzj.View.backactivity.BaseActivity;
import com.example.czero.szzj.View.bannar.BannerLayout;
import com.example.czero.szzj.View.marqeeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;





/**
 * Created by zake on 5/20/16.
 */
public class ServerActivity extends Activity implements View.OnClickListener {
    private Button  union, tour,
            waimai, secondtrade, lostfound,
           course, lovewall,zhihuribao,xiaoyuandongtai;
    private MarqueeView marqueeView;

    private ExpandableMenuOverlay menuOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabserver);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            //透明状态栏目
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        menuOverlay = (ExpandableMenuOverlay) findViewById(R.id.button_menu);

        menuOverlay.setOnMenuButtonClickListener(new ExpandableButtonMenu.OnMenuButtonClick() {
            @Override
            public void onClick(ExpandableButtonMenu.MenuButton action) {
                switch (action) {
                    case MID:
                        Intent smartc = new Intent(ServerActivity.this,SmarcActivity.class);
                        startActivity(smartc);
                        menuOverlay.getButtonMenu().toggle();
                        break;
                    case LEFT:
                        Intent chizi= new Intent(ServerActivity.this,Chizi.class);
                        startActivity(chizi);
                        menuOverlay.getButtonMenu().toggle();
                        break;
                    case RIGHT:
                        Intent searchphone= new Intent(ServerActivity.this,SearchphoneActivity.class);
                        startActivity(searchphone);
                        menuOverlay.getButtonMenu().toggle();
                        break;
                }
            }
        });




        lostfound = (Button) findViewById(R.id.lostfound);
        waimai = (Button) findViewById(R.id.waimai);
        tour = (Button) findViewById(R.id.tour);
        course = (Button) findViewById(R.id.course);
        secondtrade = (Button) findViewById(R.id.secondtrade);
        union = (Button) findViewById(R.id.union);

        lovewall= (Button) findViewById(R.id.lovewall);
       zhihuribao= (Button) findViewById(R.id.zhihuribao);
        xiaoyuandongtai= (Button) findViewById(R.id.xiaoyuandongtai);
        lovewall.setOnClickListener(this);
        zhihuribao.setOnClickListener(this);
        xiaoyuandongtai.setOnClickListener(this);
        lostfound.setOnClickListener(this);
        waimai.setOnClickListener(this);
        tour.setOnClickListener(this);
        course.setOnClickListener(this);
        secondtrade.setOnClickListener(this);
        union.setOnClickListener(this);

        BannerLayout bannerLayout = (BannerLayout) findViewById(R.id.banner);

        final List<String> urls = new ArrayList<>();
        urls.add("http://119.29.121.145/123.jpeg");
        urls.add("http://bmob-cdn-1826.b0.upaiyun.com/2016/06/24/60440c0240e095bb806a2d51278f20c2.jpg");
        urls.add("http://img0.imgtn.bdimg.com/it/u=1095909580,3513610062&fm=23&gp=0.jpg");
        urls.add("http://img4.imgtn.bdimg.com/it/u=1030604573,1579640549&fm=23&gp=0.jpg");
        urls.add("http://img5.imgtn.bdimg.com/it/u=2583054979,2860372508&fm=23&gp=0.jpg");
        bannerLayout.setViewUrls(urls);

        marqueeView = (MarqueeView)findViewById(R.id.marqueeView);
        List<String> info = new ArrayList<>();
        info.add("     欢迎使用汕职之家!");
        info.add("     欢迎联系C橙团队!");
        info.add("     知行合一,德技双馨!");
        info.add("     够真橙,活青春!");
        info.add("     橙心橙意为你好!");
        info.add("     如有疑问,找汕职小C（＊＾＿＿＾＊）");
        marqueeView.startWithList(info);
        marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                Toast.makeText(ServerActivity.this, textView.getText()+"", Toast.LENGTH_SHORT).show();
            }
        });

        //添加监听事件
        bannerLayout.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {

                switch (position){
                    case 0:
                        Toast.makeText(getBaseContext(),"你点击了一啊",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(getBaseContext(),"caocao",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.union:
                CircularAnimUtil.startActivity((Activity) ServerActivity.this, UnionActivity.class, v ,R.color.bantouming);
                break;
            case R.id.tour:
                CircularAnimUtil.startActivity((Activity) ServerActivity.this, TourActivity.class, v ,R.color.gray_bg);

                break;
            case R.id.waimai:
                CircularAnimUtil.startActivity((Activity) ServerActivity.this, WaimaiActivity.class, v ,R.color.gray_bg);
                break;
            case R.id.secondtrade:
                CircularAnimUtil.startActivity((Activity) ServerActivity.this, SecondTradeActivity.class, v ,R.color.gray_bg);
                break;
            case R.id.lostfound:
                CircularAnimUtil.startActivity((Activity) ServerActivity.this, LostFoundActivity.class, v ,R.color.gray_bg);
                break;
            case R.id.course:
                CircularAnimUtil.startActivity((Activity) ServerActivity.this, CourseActivity.class, v ,R.color.gray_bg);
                break;
            case  R.id.lovewall:
                CircularAnimUtil.startActivity((Activity) ServerActivity.this, LoveWallActivity.class, v ,R.color.gray_bg);
                break;


        }

    }

    private void toast(String toast) {
        Toast.makeText(ServerActivity.this, toast, Toast.LENGTH_SHORT).show();
    }


}
