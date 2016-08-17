package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJModel.ActivityNew;
import com.example.czero.szzj.SZZJModel.Discover;
import com.example.czero.szzj.View.SupperTitle;

/**
 * Created by zake on 6/12/16.
 */
public class ActivityNewItemActivity extends Activity{

    private ActivityNew activityNew;
    private String activitynewID;
    private TextView activitynewname,activitynewcontent;
    private SupperTitle supperTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitynewitem);
        activityNew = (ActivityNew) getIntent().getSerializableExtra("activitynew");
        activitynewID = getIntent().getStringExtra("activitynewID");
       supperTitle= (SupperTitle) findViewById(R.id.supper_title);
        activitynewcontent = (TextView) findViewById(R.id.activitynewcontent);
        activitynewname.setText(activityNew.getName());
        activitynewcontent.setText(activityNew.getItemcontent());

    }
}
