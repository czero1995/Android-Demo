package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJFragment.XSBDRXZN;
import com.example.czero.szzj.SZZJFragment.XSBDTop;
import com.example.czero.szzj.SZZJFragment.XSBDZXBB;

/**
 * Created by zake on 6/11/16.
 */
public class NewPeopleXSBD extends Activity implements View.OnClickListener{
    private Button bdxz;
    private Button rxzn;
    private Button zxbb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.npxsbd);
        bdxz= (Button) findViewById(R.id.np_bdxz);
        rxzn= (Button) findViewById(R.id.np_rxzn);
        zxbb= (Button) findViewById(R.id.np_zxbb);
        bdxz.setOnClickListener(this);
        rxzn.setOnClickListener(this);
        zxbb.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.np_bdxz:
                XSBDTop xsbdTop = new XSBDTop();
                FragmentManager xsbdtop = getFragmentManager();
                FragmentTransaction transactiona = xsbdtop.beginTransaction();
                transactiona.replace(R.id.xsbdtop_layout, xsbdTop);
                transactiona.commit();
                break;
            case R.id.np_rxzn:
                XSBDRXZN xsbdrxzn = new XSBDRXZN();
                FragmentManager xsrxzn = getFragmentManager();
                FragmentTransaction transactionb = xsrxzn.beginTransaction();
                transactionb.replace(R.id.xsbdtop_layout, xsbdrxzn);
                transactionb.commit();
                break;
            case R.id.np_zxbb:
                XSBDZXBB xsbdzxbb = new XSBDZXBB();
                FragmentManager xszxbb = getFragmentManager();
                FragmentTransaction transactionc = xszxbb.beginTransaction();
                transactionc.replace(R.id.xsbdtop_layout, xsbdzxbb);
                transactionc.commit();
                break;
        }
    }
}
