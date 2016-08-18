package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJModel.LiuYanBan;
import com.example.czero.szzj.SZZJModel.ShanZhiQuan;
import com.example.czero.szzj.SZZJModel.Shop;
import com.example.czero.szzj.SZZJModel.User;
import com.example.czero.szzj.SZZJUtil.DeviceUtils;
import com.example.czero.szzj.SZZJUtil.Util;
import com.example.czero.szzj.View.backactivity.BaseActivity;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.bean.BmobSmsState;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.QuerySMSStateListener;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by zake on 5/20/16.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private Button btnReg, btnyanzheng;
    private EditText etUsername, etPassword, etComfirmPsd, etPhone, etYanzhengma;
    private String username = null;
    private String password = null;
    private String comfirmPsd = null;
    private String phone;
    private TextView kuaisudenglu;
    private int mCount = 60;
    private RelativeLayout common_loading;
    private boolean isClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏目
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        etComfirmPsd = (EditText) findViewById(R.id.et_comfirm_psd);
        etPhone = (EditText) findViewById(R.id.et_phone);
        btnReg = (Button) findViewById(R.id.btn_reg_now);
        kuaisudenglu = (TextView) findViewById(R.id.kuaisudenglu);
        etYanzhengma = (EditText) findViewById(R.id.et_yanzhengma);
        common_loading= (RelativeLayout) findViewById(R.id.common_loading);
        phone = etPhone.getText().toString();
        btnReg.setOnClickListener(this);
        btnyanzheng = (Button) findViewById(R.id.btn_yanzheng);
        btnyanzheng.setOnClickListener(this);
        kuaisudenglu.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_reg_now:
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();
                comfirmPsd = etComfirmPsd.getText().toString();
                phone = etPhone.getText().toString();
                String yanzhengma = etYanzhengma.getText().toString();

                if (!Util.isNetworkConnected(this)) {
                    toast("亲,木有网络");
                } else if (username.equals("") || password.equals("")) {
                    toast("请输入完整的用户名和密码");
                } else if (!comfirmPsd.equals(password)) {
                    toast("两次输入密码不一致");
                }
//                else if (phone.length() < 11) {
//                    toast(("请输入正确的11位手机号码"));
//                }
        else if (password.length() < 6 || password.length() > 22) {
                    toast("请输入6-22位的密码");
                } else {
                    common_loading.setVisibility(View.VISIBLE);
                    BmobSMS.verifySmsCode(RegisterActivity.this, phone, yanzhengma, new VerifySMSCodeListener() {

                        @Override
                        public void done(BmobException ex) {
                            User bu = new User();
                            // TODO Auto-generated method stub
                            if (ex == null) {//短信验证码已验证成功
                                String device = DeviceUtils.getModel() + DeviceUtils.getManufacturer().toString();

                                bu.setDevice(device);
                                bu.setUsername(username);
                                bu.setPassword(password);
                                bu.setPhone(phone);
                                bu.setPasswd(password);
                                bu.signUp(RegisterActivity.this, new SaveListener() {
                                    @Override
                                    public void onSuccess() {
                                        toast("注册成功,请登陆");
                                        common_loading.setVisibility(View.GONE);
                                        Intent backLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(backLogin);
                                        finish();
                                    }

                                    @Override
                                    public void onFailure(int i, String s) {
                                        toast("用户已存在，请更换");
                                    }
                                });
                            } else {
                                toast("验证失败"+ex.toString());
                            }
                        }
                    });

                }

                break;
            case R.id.btn_yanzheng:
                String phones = etPhone.getText().toString();
                if(phones.equals("") || phones.length()<11){
                    toast("请输入11位的手机号码");
                }else {
                    btnyanzheng.setEnabled(false);
                    btnyanzheng.setBackgroundColor(getResources().getColor(R.color.characters));
                    new CountDownTimer(60000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            --mCount;
                            btnyanzheng.setText("还剩" + mCount + "秒");
                        }

                        @Override
                        public void onFinish() {
                            isClicked = false;
                            mCount = 60;
                            btnyanzheng.setText("获取");
                            btnyanzheng.setEnabled(true);
                            btnyanzheng.setBackgroundColor(getResources().getColor(R.color.mainblue));
                        }
                    }.start();
                    BmobSMS.requestSMSCode(RegisterActivity.this, phones, "汕职之家", new RequestSMSCodeListener() {

                        @Override
                        public void done(Integer smsId, BmobException ex) {
                            // TODO Auto-generated method stub
                            if (ex == null) {//验证码发送成功
                                toast("发送成功，留意短信通知");
                            }else{
                                toast(ex.toString());
                            }

                        }
                    });
                }
//               if (phones.length() < 11) {
//                   toast(("请输入正确的11位手机号码"));
//               }else {
//                   BmobQuery<User> query = new BmobQuery<User>();
//                   query.addWhereEqualTo("phone", phone);
//                   query.findObjects(getBaseContext(), new FindListener<User>() {
//                       @Override
//                       public void onSuccess(List<User> list) {
//                           toast("该手机号已被注册过");
//                       }
//
//                       @Override
//                       public void onError(int i, String s) {
//                           btnyanzheng.setEnabled(false);
//                           btnyanzheng.setBackgroundColor(getResources().getColor(R.color.characters));
//                           new CountDownTimer(60000, 1000) {
//                               @Override
//                               public void onTick(long millisUntilFinished) {
//                                   --mCount;
//                                   btnyanzheng.setText("还剩" + mCount + "秒");
//                               }
//
//                               @Override
//                               public void onFinish() {
//                                   isClicked = false;
//                                   mCount = 60;
//                                   btnyanzheng.setText("获取");
//                                   btnyanzheng.setEnabled(true);
//                                   btnyanzheng.setBackgroundColor(getResources().getColor(R.color.mainblue));
//                               }
//                           }.start();
//                           BmobSMS.requestSMSCode(RegisterActivity.this, phone, "汕职之家", new RequestSMSCodeListener() {
//
//                               @Override
//                               public void done(Integer smsId, BmobException ex) {
//                                   // TODO Auto-generated method stub
//                                   if (ex == null) {//验证码发送成功
//                                       toast("发送成功，留意短信通知");
//                                   }
//
//                               }
//                           });
//                       }
//
//                   });
//               }
                break;
            case R.id.kuaisudenglu:
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
        }

    }


    private void toast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }
}
