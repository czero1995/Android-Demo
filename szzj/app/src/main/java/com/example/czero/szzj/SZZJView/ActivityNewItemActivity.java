package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJModel.ActivityNew;
import com.example.czero.szzj.SZZJModel.Discover;

/**
 * Created by zake on 6/12/16.
 */
public class ActivityNewItemActivity extends Activity{

    private ActivityNew activityNew;
    private String activitynewID;
    private TextView activitynewname,activitynewcontent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitynewitem);
        activityNew = (ActivityNew) getIntent().getSerializableExtra("activitynew");
        activitynewID = getIntent().getStringExtra("activitynewID");
        activitynewname = (TextView) findViewById(R.id.activitynewname);
        activitynewcontent = (TextView) findViewById(R.id.activitynewcontent);
        activitynewname.setText(activityNew.getName());
        activitynewcontent.setText(activityNew.getItemcontent());

    }
}
