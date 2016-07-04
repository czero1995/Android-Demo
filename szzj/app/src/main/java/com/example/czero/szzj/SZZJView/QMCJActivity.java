package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJData.QMCJItemListAdapter;
import com.example.czero.szzj.SZZJModel.Course;
import com.example.czero.szzj.SZZJModel.QMCJ;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class QMCJActivity extends Activity {


    private QMCJ qmcj;
    private EditText et_qmcjnumber;
    private EditText et_qmcjpassword;
    private Button btn_qmcj;
    private ListView lvqmcj;
    private QMCJItemListAdapter qmcjItemListAdapter;
    private List<QMCJ> qmcjList = new ArrayList<QMCJ>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qmcj);
        et_qmcjnumber = (EditText) findViewById(R.id.et_qmcjnumber);
        et_qmcjpassword = (EditText) findViewById(R.id.et_qmcjpassword);
        lvqmcj = (ListView) findViewById(R.id.lv_qmcj);
        qmcjItemListAdapter = new QMCJItemListAdapter(QMCJActivity.this, (ArrayList<QMCJ>) qmcjList);
        lvqmcj.setAdapter(qmcjItemListAdapter);

        btn_qmcj= (Button) findViewById(R.id.btn_qmcj);
        btn_qmcj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getQMCJData();
            }
        });

    }

    private void getQMCJData() {
        String qmcjnumber = et_qmcjnumber.getText().toString();
        String qmcjpassword = et_qmcjpassword.getText().toString();
        BmobQuery<QMCJ> query = new BmobQuery<QMCJ>();
        query.order("-createdAt");
        query.addWhereEqualTo("number", qmcjnumber);
        query.addWhereEqualTo("password",qmcjpassword);
        query.findObjects(this, new FindListener<QMCJ>() {
            @Override
            public void onSuccess(List<QMCJ> list) {
                qmcjList = list;

                    qmcjItemListAdapter.refresh((ArrayList<QMCJ>) qmcjList);


            }

            @Override
            public void onError(int i, String s) {
               toast(s);
            }

        });
        query.addWhereNotEqualTo("number",qmcjnumber);
        query.findObjects(this, new FindListener<QMCJ>() {
            @Override
            public void onSuccess(List<QMCJ> list) {
                toast("暂查不到该同学的成绩");
            }

            @Override
            public void onError(int i, String s) {

            }
        });

    }


    private void toast(String toast){
        Toast.makeText(QMCJActivity.this,toast,Toast.LENGTH_SHORT).show();
    }

}