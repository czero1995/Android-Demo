package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJModel.Tour;
import com.example.czero.szzj.SZZJModel.Union;
import com.example.czero.szzj.View.backactivity.BaseActivity;

public class TourItemActivity extends BaseActivity {


    private Tour tour;
    private String tourID;
    private TextView name,tourname, tourcontact, tourcontent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touritem);
       tour = (Tour) getIntent().getSerializableExtra("tour");
        tourID = getIntent().getStringExtra("tourID");
        name= (TextView) findViewById(R.id.name);
        tourname= (TextView) findViewById(R.id.tourname);
        tourcontact= (TextView) findViewById(R.id.tourcontact);
        tourcontent= (TextView) findViewById(R.id.tourcontent);
        name.setText(tour.getName());
        tourname.setText(tour.getTourname());
        tourcontact.setText(tour.getTourcontact());
        tourcontent.setText(tour.getTourcontent());
    }

    private void toast(String toast) {
        Toast.makeText(TourItemActivity.this, toast, Toast.LENGTH_SHORT).show();
    }
}
