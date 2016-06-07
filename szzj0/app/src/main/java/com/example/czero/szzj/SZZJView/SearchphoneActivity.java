package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.czero.szzj.R;


/**
 * Created by zake on 5/27/16.
 */
public class SearchphoneActivity extends Activity {
    private Button searchshortphone,searchlongphone;
    private EditText etsearchlongphone;
    private TextView resultshortphone,resultlongphone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchphone);
       etsearchlongphone= (EditText) findViewById(R.id.etsearchlongphone);
        searchlongphone= (Button) findViewById(R.id.searchlongphone);
        searchshortphone= (Button) findViewById(R.id.searchshortphone);
        resultshortphone= (TextView) findViewById(R.id.resultshortphone);
        resultlongphone= (TextView) findViewById(R.id.resultlongphone);
        searchlongphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etsearchlongphone.equals("")) {

                    String shortphonenumber = etsearchlongphone.getText().toString();
                    SmsManager.getDefault().sendTextMessage("10657000",
                            null, shortphonenumber, null, null);
                    resultlongphone.setText("短号查询长号,请留意短信通知.");
                }else{
                    Toast.makeText(SearchphoneActivity.this,"请输入正确的号码",Toast.LENGTH_SHORT).show();
                }
            }
        });
        searchshortphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    SmsManager.getDefault().sendTextMessage("10086",
                            null, "cxdh", null, null);
                    resultshortphone.setText("长号查询短号,请留意短信通知.");

            }
        });
    }

}
