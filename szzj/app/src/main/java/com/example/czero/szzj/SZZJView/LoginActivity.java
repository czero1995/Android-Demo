package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJModel.User;
import com.example.czero.szzj.SZZJUtil.Util;
import com.example.czero.szzj.View.CircularAnimUtil;

import cn.bmob.v3.listener.BmobUpdateListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.update.BmobUpdateAgent;
import cn.bmob.v3.update.UpdateResponse;


/**
 * Created by zake on 5/20/16.
 */
public class LoginActivity extends Activity implements View.OnClickListener {
    private Button btnLogin;
    private TextView forgetpasswd,register;
    private EditText etUsername, etPassword;
    private String username;
    private String password;
    private ImageView img_welcome;
    ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            //透明状态栏目
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_login);
        BmobUpdateAgent.setUpdateOnlyWifi(false);
        BmobUpdateAgent.update(this);BmobUpdateAgent.setUpdateListener(new BmobUpdateListener() {

            @Override
            public void onUpdateReturned(int updateStatus, UpdateResponse updateInfo) {
                // TODO Auto-generated method stub
                //根据updateStatus来判断更新是否成功

            }
        });
        btnLogin = (Button) findViewById(R.id.btn_login);
        forgetpasswd= (TextView) findViewById(R.id.forgetpasswd);
        register= (TextView) findViewById(R.id.register);

        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        img_welcome= (ImageView) findViewById(R.id.img_welcome);

        btnLogin.setOnClickListener(this);
        forgetpasswd.setOnClickListener(this);
        register.setOnClickListener(this);
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
    public void onClick(final View v) {
        Context context = v.getContext();
        Animation shake = AnimationUtils.loadAnimation(context,
                R.anim.shake);
        switch (v.getId()) {
            case R.id.btn_login:
                username=etUsername.getText().toString();
                password=etPassword.getText().toString();
                if(!Util.isNetworkConnected(this)){
                    toast("亲,木有网络");
                    v.startAnimation(shake);
                }else if (username.equals("")||password.equals("")){
                    toast("请输入账号和密码");
                    v.startAnimation(shake);
                    break;
                }else {
                    User bu2 = new User();
                    bu2.setUsername(username);
                    bu2.setPassword(password);
                    bu2.login(this, new SaveListener() {
                        @Override
                        public void onSuccess() {
                            CircularAnimUtil.startActivity(LoginActivity.this, MeActivity.class, v, R.drawable.boder_gray_white);
                            saveUserInfo(username,password);
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            toast(s);
                        }
                    });
                }
                break;
            case R.id.forgetpasswd:
                Intent contact = new Intent(LoginActivity.this,ContactActivity.class);
                startActivity(contact);
                break;
            case R.id.register:
                Intent toReg = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(toReg);
                break;

        }
    }
    public void toast(String toast){
        Toast.makeText(this,toast,Toast.LENGTH_SHORT).show();
    }
}

