package com.example.czero.grammarjob;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by zake on 5/10/16.
 */
public class One extends Activity implements View.OnClickListener {
    private Button btn_onea, btn_oneb, btn_onec, btn_oned, btn_onee, btn_onef, btn_oneg, btn_oneh,
            btn_onei, btn_onej, btn_onek, btn_onel,btn_onem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        /
        setContentView(R.layout.one);
        btn_onea = (Button) findViewById(R.id.idonea_jbjxyby);
        btn_oneb = (Button) findViewById(R.id.idoneb_mcczygc);
        btn_onec = (Button) findViewById(R.id.idonec_xrc);
        btn_oned = (Button) findViewById(R.id.idoned_fc);
        btn_onee = (Button) findViewById(R.id.idonee_jxc);
        btn_onef = (Button) findViewById(R.id.idonef_dcyt);
        btn_oneg = (Button) findViewById(R.id.idonegyqzdc);
        btn_oneh = (Button) findViewById(R.id.idoneh_yq);
        btn_onei = (Button) findViewById(R.id.idonei_bdytygqfc);
        btn_onej = (Button) findViewById(R.id.idonej_xzfc);
        btn_onek = (Button) findViewById(R.id.idonek_dmc);
        btn_onel = (Button) findViewById(R.id.idonel_bdccz);
        btn_onem = (Button) findViewById(R.id.idonem_lxt);
        btn_onea.setOnClickListener(this);
        btn_oneb.setOnClickListener(this);
        btn_onec.setOnClickListener(this);
        btn_oned.setOnClickListener(this);
        btn_onee.setOnClickListener(this);
        btn_onef.setOnClickListener(this);
        btn_oneg.setOnClickListener(this);
        btn_oneh.setOnClickListener(this);
        btn_onei.setOnClickListener(this);
        btn_onej.setOnClickListener(this);
        btn_onek.setOnClickListener(this);
        btn_onel.setOnClickListener(this);
        btn_onem.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.idonea_jbjxyby:
                Toast.makeText(One.this, "基本句型和单句", Toast.LENGTH_SHORT).show();
                OneA_JBJXYBY oneAJBJXYBJ = new OneA_JBJXYBY();
                FragmentManager fragmentManagera = getFragmentManager();
                FragmentTransaction transactiona = fragmentManagera.beginTransaction();
                transactiona.replace(R.id.oneright_layout, oneAJBJXYBJ);
                transactiona.commit();
                break;
            case R.id.idoneb_mcczygc:
                OneB_MCCZYGC oneBMcczygc = new OneB_MCCZYGC();
                FragmentManager fragmentManagerb = getFragmentManager();
                FragmentTransaction transactionb = fragmentManagerb.beginTransaction();
                transactionb.replace(R.id.oneright_layout, oneBMcczygc);
                transactionb.commit();
                break;
            case R.id.idonec_xrc:
                OneC_XRC oneCXrc = new OneC_XRC();
                FragmentManager fragmentManagerc = getFragmentManager();
                FragmentTransaction transactionc = fragmentManagerc.beginTransaction();
                transactionc.replace(R.id.oneright_layout, oneCXrc);
                transactionc.commit();
                break;
            case R.id.idoned_fc:
                OneD_FC oneDFc = new OneD_FC();
                FragmentManager fragmentManagerd = getFragmentManager();
                FragmentTransaction transactiond = fragmentManagerd.beginTransaction();
                transactiond.replace(R.id.oneright_layout, oneDFc);
                transactiond.commit();
                break;
            case R.id.idonee_jxc:
                OneE_XJC oneEXjc = new OneE_XJC();
                FragmentManager fragmentManagere = getFragmentManager();
                FragmentTransaction transactione = fragmentManagere.beginTransaction();
                transactione.replace(R.id.oneright_layout, oneEXjc);
                transactione.commit();
                break;
            case R.id.idonef_dcyt:
                OneF_DCST oneFDcst = new OneF_DCST();
                FragmentManager fragmentManagerf = getFragmentManager();
                FragmentTransaction transactionf = fragmentManagerf.beginTransaction();
                transactionf.replace(R.id.oneright_layout, oneFDcst);
                transactionf.commit();
                break;
            case R.id.idonegyqzdc:
                OneG_YQZDC oneGYqzdc = new OneG_YQZDC();
                FragmentManager fragmentManagerg = getFragmentManager();
                FragmentTransaction transactiong = fragmentManagerg.beginTransaction();
                transactiong.replace(R.id.oneright_layout, oneGYqzdc);
                transactiong.commit();
                break;
            case R.id.idoneh_yq:
                OneH_YQ oneHYq = new OneH_YQ();
                FragmentManager fragmentManagerh = getFragmentManager();
                FragmentTransaction transactionh = fragmentManagerh.beginTransaction();
                transactionh.replace(R.id.oneright_layout, oneHYq);
                transactionh.commit();
                break;
            case R.id.idonei_bdytygqfc:
                OneI_BDYTYGQFC oneIBdytygqfc = new OneI_BDYTYGQFC();
                FragmentManager fragmentManageri = getFragmentManager();
                FragmentTransaction transactioni = fragmentManageri.beginTransaction();
                transactioni.replace(R.id.oneright_layout, oneIBdytygqfc);
                transactioni.commit();
                break;
            case R.id.idonej_xzfc:
                OneJ_XZFC oneJXzfc = new OneJ_XZFC();
                FragmentManager fragmentManagerj = getFragmentManager();
                FragmentTransaction transactionj = fragmentManagerj.beginTransaction();
                transactionj.replace(R.id.oneright_layout, oneJXzfc);
                transactionj.commit();
                break;
            case R.id.idonek_dmc:
                OneK_DMC oneKDmc = new OneK_DMC();
                FragmentManager fragmentManagerk = getFragmentManager();
                FragmentTransaction transactionk = fragmentManagerk.beginTransaction();
                transactionk.replace(R.id.oneright_layout, oneKDmc);
                transactionk.commit();
                break;
            case R.id.idonel_bdccz:
                OneL_BDCCZ oneLBdccz = new OneL_BDCCZ();
                FragmentManager fragmentManagerl = getFragmentManager();
                FragmentTransaction transactionl = fragmentManagerl.beginTransaction();
                transactionl.replace(R.id.oneright_layout, oneLBdccz);
                transactionl.commit();
                break;
            case R.id.idonem_lxt:
                Intent intent = new Intent(One.this,lxt.class);
                startActivity(intent);

        }

    }
}
