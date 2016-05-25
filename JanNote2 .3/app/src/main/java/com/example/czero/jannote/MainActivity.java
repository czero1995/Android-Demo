package com.example.czero.jannote;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.czero.jannote.ArcMenu;
import com.example.czero.jannote.ArcMenu.OnMenuItemClickListener;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import net.youmi.android.AdManager;
import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;
import net.youmi.android.banner.AdViewListener;
import net.youmi.android.spot.SpotManager;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobPushManager;


public class MainActivity extends Activity {
    private Slidemenu slidemenu;
    private ArcMenu arcMenu;
    private ImageView text;
    private NoteDB noteDB;
    private MyAdapter myAdapter;
    private Cursor cursor;
    private ListView listview;
    private SQLiteDatabase dbReader;
    private Button allnote, clonenote, todonote, sendtext, customfeedback;
    private CheckBox checkBox;
    private static final String APP_ID = "wx51ff5c7b5d989434";
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        AdManager.getInstance(getBaseContext()).init("65c725812a4c1330", "13be2515a169e93d", false);
        // 实例化广告条
        AdView adView = new AdView(MainActivity.this, AdSize.FIT_SCREEN);

// 获取要嵌入广告条的布局
        final LinearLayout adLayout = (LinearLayout) findViewById(R.id.adLayout);

// 将广告条加入到布局中
        adLayout.addView(adView);
//adView.setAdListener(new AdViewListener() {
//    @Override
//    public void onReceivedAd(AdView adView) {
//        adLayout.setVisibility(View.GONE);
//    }
//
//    @Override
//    public void onSwitchedAd(AdView adView) {
//
//    }
//
//    @Override
//    public void onFailedToReceivedAd(AdView adView) {
//
//    }
//});
        Bmob.initialize(this, "c0032d4421c7be56b134def4778022d5");
        BmobInstallation.getCurrentInstallation(this).save();
        BmobPush.startWork(this);
        BmobPushManager push = new BmobPushManager(MainActivity.this);
        api = WXAPIFactory.createWXAPI(getBaseContext(), APP_ID);
        api.registerApp(APP_ID);
        arcMenu = (ArcMenu) findViewById(R.id.id_menuright
        );
        listview = (ListView) findViewById(R.id.listview);
        slidemenu = (Slidemenu) findViewById(R.id.id_menu);
        noteDB = new NoteDB(this);
        dbReader = noteDB.getReadableDatabase();
        allnote = (Button) findViewById(R.id.allnote_btn);
        clonenote = (Button) findViewById(R.id.clonenote_btn);
        todonote = (Button) findViewById(R.id.todonote_btn);
        customfeedback = (Button) findViewById(R.id.feedback_btn);
        sendtext = (Button) findViewById(R.id.sendtext);
        checkBox = (CheckBox) findViewById(R.id.checkbox);
        clonenote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent clone = new Intent(MainActivity.this, CloneNote.class);
                startActivity(clone);
                Toast.makeText(MainActivity.this, "云端笔记", Toast.LENGTH_SHORT).show();

            }
        });
        todonote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent todo = new Intent(MainActivity.this, TODO.class);
                startActivity(todo);
                Toast.makeText(MainActivity.this, "待办事件", Toast.LENGTH_SHORT).show();
            }
        });
        allnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, MainActivity.class);
                startActivity(i);
                Toast.makeText(MainActivity.this, "所有笔记", Toast.LENGTH_SHORT).show();

            }
        });
        customfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent feedback = new Intent(MainActivity.this, CustomFeedback.class);
                startActivity(feedback);
                Toast.makeText(MainActivity.this, "用户反馈", Toast.LENGTH_SHORT).show();
            }
        });
        sendtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editor = new EditText(MainActivity.this);
                editor.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT));
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setTitle("简记");
                builder.setIcon(R.drawable.jannote);
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
                        Toast.makeText(MainActivity.this, String.valueOf(api.sendReq(req)), Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("取消", null);
                final AlertDialog alert = builder.create();
                alert.show();


            }
        });

        Button switch_menu = (Button) findViewById(R.id.switch_menu);
        switch_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slidemenu.toggle();
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                Intent intent = new Intent(MainActivity.this, SelectContent.class);
                intent.putExtra(NoteDB.ID, cursor.getInt(cursor.getColumnIndex(NoteDB.ID)));
                intent.putExtra(NoteDB.TEXT, cursor.getString(cursor.getColumnIndex(NoteDB.TEXT)));
                intent.putExtra(NoteDB.IMAGE, cursor.getString(cursor.getColumnIndex(NoteDB.IMAGE)));
                intent.putExtra(NoteDB.VIDEO, cursor.getString(cursor.getColumnIndex(NoteDB.VIDEO)));
                startActivity(intent);
            }
        });
        init();


    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    private void init() {
        arcMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public void onClick(View view, int pos) {
                Intent i = new Intent(MainActivity.this, Addcontent.class);
                switch (view.getId()) {
                    case R.id.text:
                        i.putExtra("flag", "1");
                        startActivity(i);
                        break;
                    case R.id.camera:
                        i.putExtra("flag", "2");
                        startActivity(i);
                        break;
                    case R.id.video:
                        Toast.makeText(MainActivity.this, "video", Toast.LENGTH_SHORT).show();
                        i.putExtra("flag", "3");
                        startActivity(i);
                        break;
                    case R.id.draw:
                        i.putExtra("flag", "4");
                        startActivity(i);
                        break;
                    case R.id.record:
                        i.putExtra("flag", "5");
                        startActivity(i);
                        break;
                }
            }
        });
    }

    public void selectDB() {
        cursor = dbReader.query(NoteDB.TABLE_NAME, null, null, null, null, null, null, null);
        myAdapter = new MyAdapter(this, cursor);
        listview.setAdapter(myAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        selectDB();
    }


}
