package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJModel.User;
import com.example.czero.szzj.SZZJUtil.Util;

import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.update.BmobUpdateAgent;


/**
 * Created by zake on 5/20/16.
 */
public class LoginActivity extends Activity implements View.OnClickListener {
    private Button btnLogin, btnReg, btnResetPsd,btnLook;
    private EditText etUsername, etPassword;
    private String username;
    private String password;
    private TextView backInfo, mUserInfo;
    private ImageView mUserLogo, mNewLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        BmobUpdateAgent.setUpdateOnlyWifi(false);
        BmobUpdateAgent.update(this);

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnReg = (Button) findViewById(R.id.btn_register);
        btnResetPsd = (Button) findViewById(R.id.btn_reset_psd);
        btnLook= (Button) findViewById(R.id.btn_look);
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);

        btnLogin.setOnClickListener(this);
        btnReg.setOnClickListener(this);
        btnResetPsd.setOnClickListener(this);
        btnLook.setOnClickListener(this);
        getUserInfo();
    }


    private void getUserInfo() {
        SharedPreferences sp = getSharedPreferences("UserInfo", 0);
        etUsername.setText(sp.getString("username", null));
        etPassword.setText(sp.getString("password", null));
    }

    private void saveUserInfo(String username,String password){
        SharedPreferences sp = getSharedPreferences("UserInfo",0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username",username);
        editor.putString("password",password);
        editor.commit();
    }
    @Override
    public void onClick(View v) {
        Context context = v.getContext();
        Animation shake = AnimationUtils.loadAnimation(context,
                R.anim.shake);
        switch (v.getId()) {
            case R.id.btn_login:
                v.startAnimation(shake);
                username=etUsername.getText().toString();
                password=etPassword.getText().toString();
                if(!Util.isNetworkConnected(this)){
                    toast("亲,木有网络");
                }else if (username.equals("")||password.equals("")){
                    toast("请输入账号和密码");
                    break;
                }else {
                    User bu2 = new User();
                    bu2.setUsername(username);
                    bu2.setPassword(password);
                    bu2.login(this, new SaveListener() {
                        @Override
                        public void onSuccess() {
                            toast("登陆成功");
                            saveUserInfo(username,password);
                            Intent tohome = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(tohome);
                            finish();
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            toast("用户名或密码错误");
                        }
                    });
                }
                break;
            case R.id.btn_reset_psd:
                Intent contact = new Intent(LoginActivity.this,ContactActivity.class);
                startActivity(contact);
                break;
            case R.id.btn_register:
                Intent toReg = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(toReg);
                break;
            case R.id.btn_look:
                Intent toHome = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(toHome);
        }
    }
    public void toast(String toast){
        Toast.makeText(this,toast,Toast.LENGTH_SHORT).show();
    }
}

