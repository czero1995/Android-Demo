package com.example.czero.szzj.SZZJView;

import android.app.TabActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.czero.szzj.R;


public class MainActivity extends TabActivity{

    private TabHost tabHost;
    private LayoutInflater layoutInflater;
    String[] mTitle = new String[]{"校园服务", "发现", "我"};
    int[] mIcon = new int[]{R.drawable.ic_shop, R.drawable.ic_sale, R.drawable.ic_car, R.drawable.ic_mine};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initTabView();

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
