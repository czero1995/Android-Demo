package com.example.czero.androidstudy.ui.homefragment;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.czero.androidstudy.R;
import com.example.czero.androidstudy.base.BaseFragment;
import com.example.czero.androidstudy.main.MainActivity;
import com.example.czero.androidstudy.view.s.bannar.BannerLayout;
import com.example.czero.androidstudy.view.s.marqeeview.MarqueeView;


import java.util.ArrayList;
import java.util.List;



/**
 * Created by filip on 8/21/2015.
 */
public class HuLianWangFragment extends BaseFragment {
    private MarqueeView marqueeView;
    private Context context;
    BannerLayout bannerLayout;
    @Override
    protected int obtainLayoutID() {
        return R.layout.home_hulianwang;
    }

    @Override
    protected void onViewCreated(View view) {
        bannerLayout = (BannerLayout)view.findViewById(R.id.banner);
        final List<String> urls = new ArrayList<>();
        urls.add("http://img3.imgtn.bdimg.com/it/u=2674591031,2960331950&fm=23&gp=0.jpg");
        urls.add("http://img5.imgtn.bdimg.com/it/u=3639664762,1380171059&fm=23&gp=0.jpg");
        urls.add("http://img0.imgtn.bdimg.com/it/u=1095909580,3513610062&fm=23&gp=0.jpg");
        urls.add("http://img4.imgtn.bdimg.com/it/u=1030604573,1579640549&fm=23&gp=0.jpg");
        urls.add("http://img5.imgtn.bdimg.com/it/u=2583054979,2860372508&fm=23&gp=0.jpg");
        bannerLayout.setViewUrls(urls);

        //添加监听事件
        bannerLayout.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                Toast.makeText(MainActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
                switch (position){
                    case 0:
                        Toast.makeText(getContext(),"你点击了一啊",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(getContext(),"caocao",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        marqueeView = (MarqueeView)view.findViewById(R.id.marqueeView);
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
                Toast.makeText(mContext, textView.getText()+"", Toast.LENGTH_SHORT).show();
            }
        });

    }



    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initDatas(Bundle savedInstanceState) {

    }
}
