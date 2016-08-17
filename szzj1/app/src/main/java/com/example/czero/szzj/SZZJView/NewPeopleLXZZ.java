package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJFragment.LXZZLXXX;
import com.example.czero.szzj.SZZJFragment.LXZZTop;
import com.example.czero.szzj.SZZJFragment.LXZZXZZ;
import com.example.czero.szzj.SZZJFragment.XSBDTop;

/**
 * Created by zake on 6/11/16.
 */
public class NewPeopleLXZZ extends Activity implements View.OnClickListener {
    private Button qzx;
    private Button xzz;
    private Button lxxx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nplxzz);
        qzx= (Button) findViewById(R.id.np_qzx);
        xzz= (Button) findViewById(R.id.np_xzz);
        lxxx= (Button) findViewById(R.id.np_lxxx);
        qzx.setOnClickListener(this);
        xzz.setOnClickListener(this);
        lxxx.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.np_qzx:
                LXZZTop lxzzTop = new LXZZTop();
                FragmentManager lxzztop = getFragmentManager();
                FragmentTransaction transactiona = lxzztop.beginTransaction();
                transactiona.replace(R.id.lxzztop_layout, lxzzTop);
                transactiona.commit();
                break;

            case R.id.np_xzz:
                LXZZXZZ lxzzXzz = new LXZZXZZ();
                FragmentManager lxzzxzz = getFragmentManager();
                FragmentTransaction transactionb = lxzzxzz.beginTransaction();
                transactionb.replace(R.id.lxzztop_layout, lxzzXzz);
                transactionb.commit();
                break;
            case R.id.np_lxxx:
                LXZZLXXX lxzzLxxx = new LXZZLXXX();
                FragmentManager lxzzlxxx = getFragmentManager();
                FragmentTransaction transactionc = lxzzlxxx.beginTransaction();
                transactionc.replace(R.id.lxzztop_layout, lxzzLxxx);
                transactionc.commit();
                break;
        }
    }
}
