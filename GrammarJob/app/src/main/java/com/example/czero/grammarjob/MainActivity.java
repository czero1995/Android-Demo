package com.example.czero.grammarjob;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class MainActivity extends Activity implements View.OnClickListener{
    private Button one,two,three,sendtext;
    private Slidemenu slidemenu;
    private static final String APP_ID="wx03420e7bca4a768f";
    private IWXAPI api;
    private CheckBox checkbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        api = WXAPIFactory.createWXAPI(MainActivity.this, APP_ID);
        api.registerApp(APP_ID);
        one= (Button) findViewById(R.id.one);
        two= (Button) findViewById(R.id.two);
        three= (Button) findViewById(R.id.three);
        sendtext= (Button) findViewById(R.id.sendtext);
        checkbox= (CheckBox) findViewById(R.id.checkbox);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        sendtext.setOnClickListener(this);
//        pac= (Button) findViewById(R.id.id_pac);
//        pac.setOnClickListener(this);
        checkbox = (CheckBox) findViewById(R.id.checkbox);
        sendtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editor = new EditText(MainActivity.this);
                editor.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT));
                editor.setText("简记");
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setTitle("简约不简单");
                builder.setIcon(R.drawable.grammarjob);
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
                        req.scene = checkbox.isChecked()?SendMessageToWX.Req.WXSceneTimeline:SendMessageToWX.Req.WXSceneSession;
                        Toast.makeText(MainActivity.this,String.valueOf(api.sendReq(req)),Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("取消",null);
                final  AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.one:
                Toast.makeText(MainActivity.this, "基本句型和单句", Toast.LENGTH_SHORT).show();
                Intent oneintent = new Intent(MainActivity.this,One.class);
                startActivity(oneintent);
                break;
            case R.id.two:
                Toast.makeText(MainActivity.this, "中级句型", Toast.LENGTH_SHORT).show();
                Intent twointent = new Intent(MainActivity.this,Two.class);
                startActivity(twointent);
                break;
            case R.id.three:
                Toast.makeText(MainActivity.this, "高级句型", Toast.LENGTH_SHORT).show();
                Intent threeintent = new Intent(MainActivity.this,Three.class);
                startActivity(threeintent);
                break;

        }
    }
}
