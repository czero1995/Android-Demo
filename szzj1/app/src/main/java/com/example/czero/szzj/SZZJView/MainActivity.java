package com.example.czero.szzj.SZZJView;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.czero.szzj.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class MainActivity extends TabActivity{

    private TabHost tabHost;
    private LayoutInflater layoutInflater;
    String[] mTitle = new String[]{"校园服务", "发现", "我"};
    int[] mIcon = new int[]{R.drawable.ic_server, R.drawable.ic_discover, R.drawable.ic_me};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTabView();

        String DB_PATH = "/data/data/com.example.czero.szzj/databases/";
        String DB_NAME = "question.db";
        if (new File(DB_PATH + DB_NAME).exists() == false) {
            File dir = new File(DB_PATH);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            try {
                InputStream is = getBaseContext().getAssets().open(DB_NAME);
                OutputStream os = new FileOutputStream(DB_PATH + DB_NAME);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
                os.flush();
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        Intent intent3 = new Intent(this, MeActivity.class);
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
