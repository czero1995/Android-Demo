package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJModel.Course;
import com.example.czero.szzj.SZZJModel.Shop;
import com.example.czero.szzj.SZZJModel.Union;
import com.example.czero.szzj.View.backactivity.BaseActivity;

public class UnionItemActivity extends BaseActivity {


    private Union union;
    private String unionID;
    private TextView unionname,uniontime,unionslogan,unionactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unionitem);
        union = (Union) getIntent().getSerializableExtra("union");
        unionID = getIntent().getStringExtra("unionID");
        unionname= (TextView) findViewById(R.id.unionname);
        uniontime= (TextView) findViewById(R.id.uniontime);
        unionslogan= (TextView) findViewById(R.id.unionslogan);
        unionactivity= (TextView) findViewById(R.id.unionactivity);
        unionname.setText(union.getName());
        uniontime.setText(union.getTime());
        unionslogan.setText(union.getSlogan());
        unionactivity.setText(union.getActivity());

    }

    private void toast(String toast){
        Toast.makeText(UnionItemActivity.this,toast,Toast.LENGTH_SHORT).show();
    }
}
