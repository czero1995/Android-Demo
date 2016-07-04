package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJFragment.CXJTBaoche;
import com.example.czero.szzj.SZZJFragment.CXJTEight;
import com.example.czero.szzj.SZZJFragment.CXJTThreeseven;

/**
 * Created by zake on 6/11/16.
 */
public class NewPeopleCXJT extends Activity implements View.OnClickListener{
    private Button threeseven;
    private Button eight;
    private Button baoche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.npcxjt);
        threeseven= (Button) findViewById(R.id.np_37);
        eight= (Button) findViewById(R.id.np_8);
        baoche= (Button) findViewById(R.id.np_baoche);

        threeseven.setOnClickListener(this);
        eight.setOnClickListener(this);
        baoche.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.np_37:
                CXJTThreeseven cxjtThreeseven = new CXJTThreeseven();
                FragmentManager cxjtts = getFragmentManager();
                FragmentTransaction transactiona = cxjtts.beginTransaction();
                transactiona.replace(R.id.cxjttop_layout, cxjtThreeseven);
                transactiona.commit();
                break;
            case R.id.np_8:
                CXJTEight cxjtEight = new CXJTEight();
                FragmentManager cxjteight = getFragmentManager();
                FragmentTransaction transactionb = cxjteight.beginTransaction();
                transactionb.replace(R.id.cxjttop_layout, cxjtEight);
                transactionb.commit();
                break;
            case R.id.np_baoche:
                Intent intent = new Intent(NewPeopleCXJT.this,TourActivity.class);
                startActivity(intent);
                break;


        }

    }
}
