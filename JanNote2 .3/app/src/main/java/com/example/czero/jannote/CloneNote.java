package com.example.czero.jannote;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobPushManager;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by zake on 4/28/16.
 */
public class CloneNote extends Activity {
    private EditText name,phone;
    private Button submit, querynote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clonenote);
        Bmob.initialize(this, "c0032d4421c7be56b134def4778022d5");
        BmobInstallation.getCurrentInstallation(this).save();
        BmobPush.startWork(this);
        BmobPushManager push = new BmobPushManager(CloneNote.this);
        name= (EditText) findViewById(R.id.name);
        phone= (EditText) findViewById(R.id.phone);
        submit= (Button) findViewById(R.id.submit);
        querynote = (Button) findViewById(R.id.querynote);
        querynote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobQuery<Clone> query = new BmobQuery<Clone>();
                query.findObjects(CloneNote.this, new FindListener<Clone>() {
                    @Override
                    public void onSuccess(List<Clone> list) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(CloneNote.this);
                        builder.setTitle("查询结果");
                        String str = "";
                        for(Clone clone : list){
                            str += clone.getName()+":"+clone.getPhone()+"\n";
                        }
                        builder.setMessage(str);
                        builder.create().show();
                    }

                    @Override
                    public void onError(int i, String s) {

                    }
                });
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mname = name.getText().toString();
                String mphone=phone.getText().toString();
                if(mname.equals("")||mphone.equals("")){
                    return;
                }
                Clone cloneback = new Clone();
                cloneback.setName(mname);
                cloneback.setPhone(mphone);
                cloneback.save(CloneNote.this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(CloneNote.this,"success",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(CloneNote.this,"fail",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}

class  Clone extends BmobObject {
    private String name;
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
