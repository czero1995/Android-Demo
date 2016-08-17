package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJModel.User;
import com.example.czero.szzj.View.backactivity.BaseActivity;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by zake on 5/21/16.
 */
public class MineInfoEditActivity extends BaseActivity {
    private EditText etUsername;
    private EditText etSchool;
    private EditText etCademy;
    private EditText etDorPart;
    private EditText etDorNum;
    private EditText etPhone;
    private EditText etQQ;
    private User curUser;
    public static final int MINE_INFO_FINISH_FIND_USER = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_info_edit);
        setCurUser();
    }
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MINE_INFO_FINISH_FIND_USER:
                    initView();
                    break;
            }
        }
    };
    private void initView(){
        etUsername = (EditText) findViewById(R.id.et_mineinfo_username);
        etSchool = (EditText) findViewById(R.id.et_mineinfo_school);
        etCademy = (EditText) findViewById(R.id.et_mineinfo_cademy);
        etDorPart = (EditText) findViewById(R.id.et_mineinfo_dorpart);
        etDorNum = (EditText) findViewById(R.id.et_mineinfo_dornum);
        etPhone = (EditText) findViewById(R.id.et_mineinfo_phone);
        etQQ = (EditText) findViewById(R.id.et_mineinfo_qq);
        etUsername.setText(curUser.getUsername());
        etSchool.setText(curUser.getSchool());
        etCademy.setText(curUser.getCademy());
        etDorPart.setText(curUser.getDorPart());
        etDorNum.setText(curUser.getDorNum());
        etPhone.setText(curUser.getPhone());
        etQQ.setText(curUser.getQQ());
    }
    private void setCurUser(){
        BmobUser bmobUser = BmobUser.getCurrentUser(this);
        BmobQuery<User> query = new BmobQuery<User>();
        query.addWhereEqualTo("objectId",bmobUser.getObjectId());
        query.findObjects(this, new FindListener<User>() {
            @Override
            public void onSuccess(List<User> list) {
                curUser=list.get(0);
                Message msg= new Message();
                msg.what = MINE_INFO_FINISH_FIND_USER;
                mHandler.sendMessage(msg);
            }

            @Override
            public void onError(int i, String s) {
                toast("亲， 获取当前用户失败");
            }
        });
    }
    private void saveUserInfo(){
        if(curUser ==null){
            toast("请先登陆");
            Intent toLogin = new Intent(MineInfoEditActivity.this,LoginActivity.class);
            startActivity(toLogin);
        }else{
            curUser.setUsername(etUsername.getText().toString());
            curUser.setSchool(etSchool.getText().toString());
            curUser.setCademy(etCademy.getText().toString());
            curUser.setDorPart(etDorPart.getText().toString());
            curUser.setDorNum(etDorNum.getText().toString());
            curUser.setPhone(etPhone.getText().toString());
            curUser.setQQ(etQQ.getText().toString());
            curUser.update(this, curUser.getObjectId(), new UpdateListener() {
                @Override
                public void onSuccess() {
                    Intent back = new Intent(MineInfoEditActivity.this,MineInfoActivity.class);
                    setResult(200,back);
                    finish();
                    toast("个人资料修改成功");
                }

                @Override
                public void onFailure(int i, String s) {
                    toast("更新失败");
                }
            });
        }
    }
    public void clickSave(View v) {
        saveUserInfo();
    }

    public void clickCancel(View v) {
        finish();
    }

    private void toast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }
}
