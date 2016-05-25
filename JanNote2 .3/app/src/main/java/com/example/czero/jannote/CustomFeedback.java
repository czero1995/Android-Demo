package com.example.czero.jannote;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by zake on 4/30/16.
 */
public class CustomFeedback extends Activity {
    private EditText customer_name,customer_phone,customer_content;
    private Button btn_custom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customfeedback);
        Bmob.initialize(this, "c0032d4421c7be56b134def4778022d5");
        customer_content= (EditText) findViewById(R.id.et_customerfeedback);
        customer_name= (EditText) findViewById(R.id.customer_name);
        customer_phone= (EditText) findViewById(R.id.customer_phone);
        btn_custom= (Button) findViewById(R.id.btn_customerfeedback);
        btn_custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cname = customer_name.getText().toString();
                String cphone = customer_phone.getText().toString();
                String ccontent = customer_content.getText().toString();
                if(cname.equals("")||cphone.equals("")||ccontent.equals("")){
                    return;
                }
                Custom customer = new Custom();
                customer.setCustomr_name(cname);
                customer.setCustomr_phone(cphone);
                customer.setCustomr_content(ccontent);
                customer.save(CustomFeedback.this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(CustomFeedback.this,"提交成功,谢谢您的配合!",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(CustomFeedback.this,"Failure",Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}
class Custom extends BmobObject{
    private String customr_name;
    private String customr_phone;
    private String customr_content;

    public String getCustomr_phone() {
        return customr_phone;
    }

    public void setCustomr_phone(String customr_phone) {
        this.customr_phone = customr_phone;
    }

    public String getCustomr_name() {
        return customr_name;
    }

    public void setCustomr_name(String customr_name) {
        this.customr_name = customr_name;
    }

    public String getCustomr_content() {
        return customr_content;
    }

    public void setCustomr_content(String customr_content) {
        this.customr_content = customr_content;
    }


}