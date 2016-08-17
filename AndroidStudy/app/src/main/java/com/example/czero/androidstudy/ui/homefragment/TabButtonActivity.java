package com.example.czero.androidstudy.ui.homefragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.czero.androidstudy.R;
import com.example.czero.androidstudy.main.MainActivity;
import com.example.czero.androidstudy.view.s.bannar.BannerLayout;
import com.example.czero.androidstudy.view.s.suppertitle.SupperTitle;
import com.lzp.floatingactionbuttonplus.FabTagLayout;
import com.lzp.floatingactionbuttonplus.FloatingActionButtonPlus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by czero on 7/24/16.
 */

public class TabButtonActivity extends Activity {
    private FloatingActionButtonPlus mActionButtonPlus;
    private SupperTitle supperTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.tab_button);
        supperTitle = (SupperTitle) findViewById(R.id.suppertitle);
        supperTitle.setTitle("TabButton界面");
        supperTitle.setTitleBackground(getResources().getColor(R.color.mainblue));
        supperTitle.setLeftImageResource(R.drawable.ic_back);
        supperTitle.setLeftImagePadding(0, 15, 0, 15);
        supperTitle.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mActionButtonPlus= (FloatingActionButtonPlus) findViewById(R.id.FabPlus);
        BannerLayout bannerLayout = (BannerLayout) findViewById(R.id.banner);

        final List<String> urls = new ArrayList<>();
        urls.add("http://bmob-cdn-1826.b0.upaiyun.com/2016/06/24/60440c0240e095bb806a2d51278f20c2.jpg");
        urls.add("http://bmob-cdn-1826.b0.upaiyun.com/2016/06/24/60440c0240e095bb806a2d51278f20c2.jpg");
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
                        Toast.makeText(getBaseContext(),"你点击了一啊",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(getBaseContext(),"caocao",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        mActionButtonPlus.setOnItemClickListener(new FloatingActionButtonPlus.OnItemClickListener() {
            @Override
            public void onItemClick(FabTagLayout tagView, int position) {
                if(position==0) {
                    Toast.makeText(TabButtonActivity.this, "00000",Toast.LENGTH_SHORT).show();
                }
                if(position==1) {
                    Toast.makeText(TabButtonActivity.this, "1111111111111", Toast.LENGTH_SHORT).show();
                }
                if(position==2) {
                    Toast.makeText(TabButtonActivity.this, "222", Toast.LENGTH_SHORT).show();
                }
                if(position==3) {
                    Toast.makeText(TabButtonActivity.this, "3333.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(TabButtonActivity.this, "else", Toast.LENGTH_SHORT).show();
                }
            }


        });
    }


}
