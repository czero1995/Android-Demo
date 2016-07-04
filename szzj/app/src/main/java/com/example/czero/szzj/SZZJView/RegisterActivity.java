package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJModel.User;
import com.example.czero.szzj.SZZJUtil.Util;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by zake on 5/20/16.
 */
public class RegisterActivity extends Activity implements View.OnClickListener {
    private Button btnReg;
    private EditText etUsername, etPassword, etComfirmPsd, etPhone;
    private String username = null;
    private String password = null;
    private String comfirmPsd = null;
    private String phone = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        etComfirmPsd = (EditText) findViewById(R.id.et_comfirm_psd);
        etPhone = (EditText) findViewById(R.id.et_phone);
        btnReg = (Button) findViewById(R.id.btn_reg_now);
        btnReg.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_reg_now:
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();
                comfirmPsd = etComfirmPsd.getText().toString();
                phone = etPhone.getText().toString();
                if (!Util.isNetworkConnected(this)) {
                    toast("亲,木有网络");
                } else if (username.equals("") || password.equals("")) {
                    toast("请输入完整的用户名和密码");
                } else if (!comfirmPsd.equals(password)) {
                    toast("两次输入密码不一致");
                } else {
                    User bu = new User();
                    bu.setUsername(username);
                    bu.setPassword(password);
                    bu.setPhone(phone);
                    bu.signUp(this, new SaveListener() {
                        @Override
                        public void onSuccess() {
                            toast("注册成功,请登陆");
                            Intent backLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(backLogin);
                            finish();
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            toast("用户已存在,请更换");
                        }
                    });
                }
                break;
        }
    }

    private void toast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }
}
