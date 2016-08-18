package com.example.czero.szzj.SZZJView;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJModel.User;
import com.example.czero.szzj.SZZJUtil.DeviceUtils;
import com.example.czero.szzj.SZZJUtil.Util;
import com.example.czero.szzj.View.backactivity.BaseActivity;

import java.util.List;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by czero on 8/10/16.
 */
public class ForgetActivity extends BaseActivity {
    private TextView kuaisudenglu;
    private EditText forphone, foryanzhengma, fornewpasswd, forinsurepasswd;
    private Button forbtnyanzheng, forbtninsure;
    private int mCount = 60;
    private boolean isClicked = false;
    private User curUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        forphone = (EditText) findViewById(R.id.for_phone);
        foryanzhengma = (EditText) findViewById(R.id.for_yanzhengma);
        fornewpasswd = (EditText) findViewById(R.id.for_newpasswd);
        forinsurepasswd = (EditText) findViewById(R.id.for_insure_passwd);
        forbtnyanzheng = (Button) findViewById(R.id.for_btn_yanzheng);
        forbtninsure = (Button) findViewById(R.id.for_btn_insure);
        kuaisudenglu = (TextView) findViewById(R.id.kuaisudenglu);
        final String newpasswd = fornewpasswd.getText().toString();
        curUser= BmobUser.getCurrentUser(getBaseContext(), User.class);
        final String insurepasswd = forinsurepasswd.getText().toString();
        kuaisudenglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(I);
            }
        });
        forbtninsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Util.isNetworkConnected(getBaseContext())) {
                    toast("亲,木有网络");
                } else if (forphone.equals("") || forphone.length() < 11) {
                    toast("请输入正确的11位手机号码");
                } else if (!newpasswd.equals(insurepasswd)) {
                    toast("两次输入密码不一致");
                } else if (foryanzhengma.equals("")) {
                    toast("验码不能为空");
                } else if (fornewpasswd.length() < 6 || fornewpasswd.length() > 22) {
                    toast("请输入6-22位的密码");
                } else if(foryanzhengma.equals("")){
                    toast("请输入验证码");
                }
                else {
                    final String phones = forphone.getText().toString();
                    String yanzhengmas = foryanzhengma.getText().toString();
                    BmobSMS.verifySmsCode(getBaseContext(), phones, yanzhengmas, new VerifySMSCodeListener() {
                        @Override
                        public void done(BmobException e) {
//                            User bu = new User();
//                            bu= BmobUser.getCurrentUser(getBaseContext(), User.class);
//                            String id = bu.getObjectId().toString();
                            BmobQuery<User> query = new BmobQuery<User>();
//                            query.addWhereEqualTo("phone", phones);
                            curUser.setPassword(fornewpasswd.getText().toString());
                            curUser.setPasswd(fornewpasswd.getText().toString());
                            query.getObject(getBaseContext(), curUser.getObjectId(), new GetListener<User>() {
                                @Override
                                public void onSuccess(User user) {
                                    toast("更新成功");
                                }

                                @Override
                                public void onFailure(int i, String s) {
toast(s);
                                }
                            });

                            // TODO Auto-generated method stub
                            if (e == null)

                            {//短信验证码已验证成功
//                                String passwd = fornewpasswd.getText().toString();
//                                bu.setPassword(passwd);
//                                bu.update(getBaseContext(), id,new UpdateListener() {
//                                    @Override
//                                    public void onSuccess() {
//                                        toast("密码修改成功，请登陆");
//                                        Intent i = new Intent(getBaseContext(), LoginActivity.class);
//                                        startActivity(i);
//                                        finish();
//                                    }
//
//                                    @Override
//                                    public void onFailure(int i, String s) {
//                                        toast(s);
//                                    }
//                                });
                            } else {
                                toast("验证失败");
                            }
                        }
                    });
                }
            }
        });

        forbtnyanzheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String phones = forphone.getText().toString();
                if (forphone.equals("") || forphone.length() < 11) {
                    toast("请输入正确的11位手机号码");}
                else {
                    BmobQuery<User> query = new BmobQuery<User>();
                    query.addWhereEqualTo("phone", phones);
                    query.findObjects(getBaseContext(), new FindListener<User>() {
                        @Override
                        public void onSuccess(List<User> list) {
                            forbtnyanzheng.setEnabled(false);
                            forbtnyanzheng.setBackgroundColor(getResources().getColor(R.color.characters));
                            new CountDownTimer(60000, 1000) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    --mCount;
                                    forbtnyanzheng.setText("还剩" + mCount + "秒");
                                }

                                @Override
                                public void onFinish() {
                                    isClicked = false;
                                    mCount = 60;
                                    forbtnyanzheng.setText("获取");
                                    forbtnyanzheng.setEnabled(true);
                                    forbtnyanzheng.setBackgroundColor(getResources().getColor(R.color.mainblue));
                                }
                            }.start();
                            BmobSMS.requestSMSCode(getBaseContext(), phones, "汕职之家", new RequestSMSCodeListener() {

                                @Override
                                public void done(Integer smsId, BmobException ex) {
                                    // TODO Auto-generated method stub
                                    if (ex == null) {//验证码发送成功
                                        toast("发送成功，留意短信通知");
                                    } else {
                                        toast("发送失败");
                                    }

                                }
                            });

                        }

                        @Override
                        public void onError(int i, String s) {
                            toast("该手机号未注册过");
                        }
                    });
                }
            }
        });

    }

                private void toast (String toast){
                    Toast.makeText(getBaseContext(), toast, Toast.LENGTH_SHORT).show();
                }
            }
