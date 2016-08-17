package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJModel.Discover;

/**
 * Created by zake on 6/12/16.
 */
public class DiscoverItemActivity extends Activity {

    private Discover discover;
    private String discoverID;
    private TextView discovername, discovercontent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discoveritem);
        discover = (Discover) getIntent().getSerializableExtra("discover");
        discoverID = getIntent().getStringExtra("discoverID");
        discovername = (TextView) findViewById(R.id.discovername);
        discovercontent = (TextView) findViewById(R.id.discoveritemcontent);
        discovername.setText(discover.getDiscovername());
        discovercontent.setText(discover.getDiscoveritemcontent());

    }


}