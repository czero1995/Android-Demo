package com.example.czero.grammarjob;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by zake on 5/10/16.
 */
public class Two extends Activity implements View.OnClickListener {
    private Button btn_twoa, btn_twoa1, btn_twob, btn_twoc, btn_twod, btn_twoe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.two);
        btn_twoa = (Button) findViewById(R.id.idtwoa_mczj);
        btn_twoa1 = (Button) findViewById(R.id.idtwoa_hj);
        btn_twob = (Button) findViewById(R.id.idtwob_fczj);
        btn_twoc = (Button) findViewById(R.id.idtwoc_gxzj);
        btn_twod = (Button) findViewById(R.id.idtwod_zcdcyzx);
        btn_twoe = (Button) findViewById(R.id.idtwoe_dzj);
        btn_twoa.setOnClickListener(this);
        btn_twoa1.setOnClickListener(this);
        btn_twob.setOnClickListener(this);
        btn_twoc.setOnClickListener(this);
        btn_twod.setOnClickListener(this);
        btn_twoe.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.idtwoa_mczj:
                TwoA_MCCJ twoAMCCJ = new TwoA_MCCJ();
                FragmentManager fragmentManagera = getFragmentManager();
                FragmentTransaction transactiona = fragmentManagera.beginTransaction();
                transactiona.replace(R.id.tworight_layout, twoAMCCJ);
                transactiona.commit();
                break;
            case R.id.idtwoa_hj:
                TwoA_HJ twoAHj = new TwoA_HJ();
                FragmentManager fragmentManagera1 = getFragmentManager();
                FragmentTransaction transactiona1 = fragmentManagera1.beginTransaction();
                transactiona1.replace(R.id.tworight_layout, twoAHj);
                transactiona1.commit();
                break;
            case R.id.idtwob_fczj:
                TwoB_FCZJ twoBFczj = new TwoB_FCZJ();
                FragmentManager fragmentManagerb = getFragmentManager();
                FragmentTransaction transactionb = fragmentManagerb.beginTransaction();
                transactionb.replace(R.id.tworight_layout, twoBFczj);
                transactionb.commit();
                break;
            case R.id.idtwoc_gxzj:
                TwoC_GXZJ twoCGxzj = new TwoC_GXZJ();
                FragmentManager fragmentManagerc = getFragmentManager();
                FragmentTransaction transactionc = fragmentManagerc.beginTransaction();
                transactionc.replace(R.id.tworight_layout, twoCGxzj);
                transactionc.commit();
                break;
            case R.id.idtwod_zcdcyzx:
                TwoD_ZCDCYZX twoDZcdcyzx = new TwoD_ZCDCYZX();
                FragmentManager fragmentManagerd = getFragmentManager();
                FragmentTransaction transactiond = fragmentManagerd.beginTransaction();
                transactiond.replace(R.id.tworight_layout, twoDZcdcyzx);
                transactiond.commit();
                break;
            case R.id.idtwoe_dzj:
                TwoE_DZJ twoEDzj = new TwoE_DZJ();
                FragmentManager fragmentManagere = getFragmentManager();
                FragmentTransaction transactione = fragmentManagere.beginTransaction();
                transactione.replace(R.id.tworight_layout, twoEDzj);
                transactione.commit();
                break;
        }

    }
}
