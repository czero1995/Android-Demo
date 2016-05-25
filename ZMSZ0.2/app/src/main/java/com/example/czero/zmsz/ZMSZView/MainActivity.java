package com.example.czero.zmsz.ZMSZView;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.czero.zmsz.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;

public class MainActivity extends TabActivity implements View.OnClickListener{
    private Button menumineinfo,menuschoolnet,menusharefriend,menucontact,menufeedback;
    private TabHost tabHost;
    private LayoutInflater layoutInflater;
    private String url = "http://www.stpt.edu.cn:8080/web/gxb/zsxx/";
    String[] mTitle = new String[]{"校园服务", "发现", "吃喝玩乐"};
    int[] mIcon = new int[]{R.drawable.ic_shop, R.drawable.ic_sale, R.drawable.ic_car, R.drawable.ic_mine};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initTabView();
        initView();

    }

    private void initView() {
        menumineinfo= (Button) findViewById(R.id.menu_mineinfo);
        menuschoolnet= (Button) findViewById(R.id.menu_schoolnet);
        menusharefriend= (Button) findViewById(R.id.menu_sharefriend);
        menucontact= (Button) findViewById(R.id.menu_contact);
        menufeedback= (Button) findViewById(R.id.menu_feedback);
        menumineinfo.setOnClickListener(this);
        menuschoolnet.setOnClickListener(this);
        menusharefriend.setOnClickListener(this);
        menucontact.setOnClickListener(this);
        menufeedback.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.menu_mineinfo:
               Intent intent1 = new Intent(MainActivity.this,MineInfoActivity.class);
                startActivity(intent1);
                break;
            case R.id.menu_schoolnet:
                Uri uri = Uri.parse(url);
                Intent intent2 = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent2);
                break;
            case R.id.menu_sharefriend:
                Intent intent3 = new Intent(Intent.ACTION_SEND);
                intent3.setType("text/plain");
                intent3.putExtra(Intent.EXTRA_SUBJECT,"分享");
                intent3.putExtra(Intent.EXTRA_TEXT,"纵梦汕职-C橙团队"+"\n"+"赶快下载体验吧!!!"+"http://czero.cn\"");
                startActivity(Intent.createChooser(intent3,"分享到"));
                break;
            case R.id.menu_contact:
                Intent intent4 = new Intent(MainActivity.this,ContactActivity.class);
                startActivity(intent4);
                break;
            case R.id.menu_feedback:
                Intent intent5 = new Intent(MainActivity.this,FeedbackActivity.class);
                startActivity(intent5);
                break;

        }
    }
    private void initTabView() {
        tabHost = getTabHost();
        layoutInflater = LayoutInflater.from(this);
        TabHost.TabSpec spec;
        Intent intent1 = new Intent(this, ServerActivity.class);
        spec = tabHost.newTabSpec(mTitle[0]).setIndicator(getTabItemView(0)).setContent(intent1);
        tabHost.addTab(spec);
        Intent intent2 = new Intent(this, DiscoverActivity.class);
        spec = tabHost.newTabSpec(mTitle[1]).setIndicator(getTabItemView(1)).setContent(intent2);
        tabHost.addTab(spec);
        Intent intent3 = new Intent(this, HappyActivity.class);
        spec = tabHost.newTabSpec(mTitle[2]).setIndicator(getTabItemView(2)).setContent(intent3);
        tabHost.addTab(spec);
        tabHost.setCurrentTab(0);
    }

    public View getTabItemView(int i) {
        // TODO Auto-generated method stub
        View view = layoutInflater.inflate(R.layout.tab_widget_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mIcon[i]);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTitle[i]);
        return view;
    }
private void toast(String toast){
    Toast.makeText(MainActivity.this,toast,Toast.LENGTH_SHORT).show();
}


}
