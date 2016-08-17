package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.czero.szzj.R;

/**
 * Created by zake on 6/2/16.
 */
public class NewPeople extends Activity implements View.OnClickListener {
    private Button szyl;
    private Button xsbd;
    private Button lxzz;
    private Button jtcx;
    private Button xytb;
    private Button gzh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpeople);
        szyl= (Button) findViewById(R.id.newpeople_szyl);
        xsbd= (Button) findViewById(R.id.newpeople_xsbd);
        lxzz= (Button) findViewById(R.id.newpeople_lxzz);
        jtcx= (Button) findViewById(R.id.newpeople_jtcx);
        xytb= (Button) findViewById(R.id.newpeople_xytb);
        gzh= (Button) findViewById(R.id.newpeople_gzh);
        szyl.setOnClickListener(this);
        xsbd.setOnClickListener(this);
        lxzz.setOnClickListener(this);
        jtcx.setOnClickListener(this);
        xytb.setOnClickListener(this);
        gzh.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.newpeople_szyl:
                Intent szyl = new Intent(NewPeople.this,NewPeopleSZYL.class);
                startActivity(szyl);
                break;
            case R.id.newpeople_xsbd:
                Intent xsbd = new Intent(NewPeople.this,NewPeopleXSBD.class);
                startActivity(xsbd);
                break;
            case R.id.newpeople_lxzz:
                Intent lxzz = new Intent(NewPeople.this,NewPeopleLXZZ.class);
                startActivity(lxzz);
                break;
            case R.id.newpeople_jtcx:
                Intent jtcx = new Intent(NewPeople.this,NewPeopleCXJT.class);
                startActivity(jtcx);
                break;
            case R.id.newpeople_xytb:
                Intent xytb = new Intent(NewPeople.this,NewPeopleXYTB.class);
                startActivity(xytb);
                break;
            case R.id.newpeople_gzh:
                Intent gzh = new Intent(NewPeople.this,NewPeopleGZH.class);
                startActivity(gzh);
                break;
        }
    }
}
