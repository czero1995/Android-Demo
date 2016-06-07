package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.czero.szzj.R;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import cn.bmob.v3.BmobUser;

/**
 * Created by zake on 5/20/16.
 */
public class MeActivity extends Activity implements View.OnClickListener {

    private Button qqlogin,menumineinfo,menucontact,menufeedback,loginout;
    private Button menu_sendwuchat;
    private CheckBox checkBox;
    private IWXAPI api;
    private static final String APP_ID = "wx9d0c2adb4dad845a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabme);
        api = WXAPIFactory.createWXAPI(getBaseContext(), APP_ID);
        api.registerApp(APP_ID);
        menumineinfo= (Button) findViewById(R.id.menu_mineinfo);
        menucontact= (Button) findViewById(R.id.menu_contact);
        menufeedback= (Button) findViewById(R.id.menu_feedback);
        loginout= (Button) findViewById(R.id.menu_logout);
        menu_sendwuchat= (Button) findViewById(R.id.menu_sendwechat);
        checkBox= (CheckBox) findViewById(R.id.checkbox);
        menumineinfo.setOnClickListener(this);
        menucontact.setOnClickListener(this);
        menufeedback.setOnClickListener(this);
        loginout.setOnClickListener(this);
        menu_sendwuchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editor = new EditText(MeActivity.this);
                editor.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT));
                final AlertDialog.Builder builder = new AlertDialog.Builder(MeActivity.this);
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setTitle("汕职之家");
                builder.setIcon(R.drawable.ic_app);
                builder.setView(editor);
                builder.setMessage("请输入要分享的文本");
                builder.setPositiveButton("分享", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        获取要分享的文本
                        String text = editor.getText().toString();
                        if (text == null || text.length() == 0) {
                            return;
                        }
                        //第一步:创建一个用于封装文本的WXTextObject对象
                        WXTextObject textObj = new WXTextObject();
                        textObj.text = text;
                        //第三部创建WXMediaMessage对象,用于向微信传送数据
                        WXMediaMessage msg = new WXMediaMessage();
                        msg.mediaObject = textObj;
                        msg.description = text;
                        //第三步:创建一个用于请求微信客户端的SendMessageTowx对象
                        SendMessageToWX.Req req = new SendMessageToWX.Req();
                        req.message = msg;
                        //设置请求的唯一标识
                        req.transaction = buildTransaction("text");
                        //表示发送给朋友还是朋友圈
                        req.scene = checkBox.isChecked() ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
                        Toast.makeText(MeActivity.this, String.valueOf(api.sendReq(req)), Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("取消", null);
                final AlertDialog alert = builder.create();
                alert.show();


            }
        });

    }
    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    private void toast(String toast) {
        Toast.makeText(MeActivity.this, toast, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menu_mineinfo:
                Intent intent1 = new Intent(MeActivity.this,MineInfoActivity.class);
                startActivity(intent1);
                break;

            case R.id.menu_contact:
                Intent intent4 = new Intent(MeActivity.this,ContactActivity.class);
                startActivity(intent4);
                break;
            case R.id.menu_feedback:
                Intent intent5 = new Intent(MeActivity.this,FeedbackActivity.class);
                startActivity(intent5);
                break;
            case R.id.menu_logout:
                BmobUser.logOut(this);
                Intent toLogin = new Intent(MeActivity.this, LoginActivity.class);
                startActivity(toLogin);
                finish();
                break;

        }
    }
}

