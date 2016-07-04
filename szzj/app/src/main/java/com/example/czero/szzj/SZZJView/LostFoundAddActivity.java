package com.example.czero.szzj.SZZJView;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJModel.Found;
import com.example.czero.szzj.SZZJModel.Lost;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by zake on 5/23/16.
 */
public class LostFoundAddActivity extends LostFoundBaseActivity implements View.OnClickListener{
    EditText edit_title, edit_photo, edit_describe;
    Button btn_back, btn_true;

    TextView tv_add;
    String from = "";

    String old_title = "";
    String old_describe = "";
    String old_phone = "";
    @Override
    public void setContentView() {
        setContentView(R.layout.activity_lostfoundadd);
    }

    @Override
    public void initViews() {
        tv_add = (TextView) findViewById(R.id.tv_add);
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_true = (Button) findViewById(R.id.btn_true);
        edit_photo = (EditText) findViewById(R.id.edit_photo);
        edit_describe = (EditText) findViewById(R.id.edit_describe);
        edit_title = (EditText) findViewById(R.id.edit_title);
    }

    @Override
    public void initListeners() {
        btn_back.setOnClickListener(this);
        btn_true.setOnClickListener(this);
    }

    @Override
    public void initData() {
        from = getIntent().getStringExtra("from");
        old_title = getIntent().getStringExtra("title");
        old_phone = getIntent().getStringExtra("phone");
        old_describe = getIntent().getStringExtra("describe");

        edit_title.setText(old_title);
        edit_describe.setText(old_describe);
        edit_photo.setText(old_phone);

        if (from.equals("丢失物件")) {
            tv_add.setText("丢失物件");
        } else {
            tv_add.setText("发现物件");
        }
    }

    public void onClick(View v) {
        if (v == btn_true) {
            addByType();
        } else if (v == btn_back) {
            finish();
        }
    }
    String title = "";
    String describe = "";
    String photo="";

    private void addByType(){
        title = edit_title.getText().toString();
        describe = edit_describe.getText().toString();
        photo = edit_photo.getText().toString();

        if(TextUtils.isEmpty(title)){
            ShowToast("请输入标题");
            return;
        }
        if(TextUtils.isEmpty(describe)){
            ShowToast("请输入描述的内容");
            return;
        }
        if(TextUtils.isEmpty(photo)){
            ShowToast("请输入您的联系方式");
            return;
        }
        if(from.equals("丢失物件")){
            addLost();
        }else{
            addFound();
        }
    }

    private void addLost(){
        Lost lost = new Lost();
        lost.setDescribe(describe);
        lost.setPhone(photo);
        lost.setTitle(title);
        lost.save(this, new SaveListener() {

            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
                ShowToast("发布成功");
                setResult(RESULT_OK);
                finish();
            }

            @Override
            public void onFailure(int code, String arg0) {
                // TODO Auto-generated method stub
                ShowToast("发布失败"+arg0);
            }
        });
    }

    private void addFound(){
        Found found = new Found();
        found.setDescribe(describe);
        found.setPhone(photo);
        found.setTitle(title);
        found.save(this, new SaveListener() {

            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
                ShowToast("发布成功");
                setResult(RESULT_OK);
                finish();
            }

            @Override
            public void onFailure(int code, String arg0) {
                // TODO Auto-generated method stub
                ShowToast("发布失败:"+arg0);
            }
        });
    }
}
