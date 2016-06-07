package com.example.czero.grammarjob;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by zake on 5/10/16.
 */
public class Three extends Activity implements View.OnClickListener {
    private Button btn_threea, btn_threeb, btn_threec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.three);
        btn_threea = (Button) findViewById(R.id.idthreea_xrczjjh);
        btn_threeb = (Button) findViewById(R.id.idthreeb_mczjjh);
        btn_threec = (Button) findViewById(R.id.idthreec_fczjjhc);
        btn_threea.setOnClickListener(this);
        btn_threeb.setOnClickListener(this);
        btn_threec.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.idthreea_xrczjjh:
                ThreeA_XRCZJJH threeAXRCCJJH = new ThreeA_XRCZJJH();
                FragmentManager fragmentManagera = getFragmentManager();
                FragmentTransaction transactiona = fragmentManagera.beginTransaction();
                transactiona.replace(R.id.threeright_layout, threeAXRCCJJH);
                transactiona.commit();
                break;
            case R.id.idthreeb_mczjjh:
                ThreeB_MCZJJH threeBMczjjh = new ThreeB_MCZJJH();
                FragmentManager fragmentManagerb = getFragmentManager();
                FragmentTransaction transactionb = fragmentManagerb.beginTransaction();
                transactionb.replace(R.id.threeright_layout, threeBMczjjh);
                transactionb.commit();
                break;
            case R.id.idthreec_fczjjhc:
                ThreeC_FCZJJH threeCFczjjh = new ThreeC_FCZJJH();
                FragmentManager fragmentManagerc = getFragmentManager();
                FragmentTransaction transactionc = fragmentManagerc.beginTransaction();
                transactionc.replace(R.id.threeright_layout, threeCFczjjh);
                transactionc.commit();
                break;
        }

    }
}
