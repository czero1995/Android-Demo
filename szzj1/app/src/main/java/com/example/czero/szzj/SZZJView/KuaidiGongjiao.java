package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.czero.szzj.R;

/**
 * Created by zake on 6/11/16.
 */
public class KuaidiGongjiao extends Activity implements View.OnClickListener{
    private Button kdsc;
    private Button xygj;
    private Button gjsc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuaidigongjiao);
        kdsc= (Button) findViewById(R.id.kdsc);
        xygj= (Button) findViewById(R.id.xygj);
        gjsc= (Button) findViewById(R.id.gjsc);
        kdsc.setOnClickListener(this);
        xygj.setOnClickListener(this);
        gjsc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.kdsc:
                Intent intent = new Intent(KuaidiGongjiao.this,KuaidiActivity.class);
                startActivity(intent);
                break;
            case R.id.xygj:
                Intent intent1 = new Intent(KuaidiGongjiao.this,NewPeopleCXJT.class);
                startActivity(intent1);
                break;
            case R.id.gjsc:
                Intent intent2 = new Intent(KuaidiGongjiao.this,NewPeopleGJSC.class);
                startActivity(intent2);
        }
    }
    private void toast(String toast){
        Toast.makeText(KuaidiGongjiao.this,toast,Toast.LENGTH_SHORT).show();
    }
}
