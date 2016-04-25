package com.example.zake.smarttime;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.AsyncTask;
import android.widget.Toast;


import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.LogRecord;






/**
 * Created by zake on 3/14/16.
 */
public class TimeView extends LinearLayout {
    private String timer;
    private TextView tvtime,nettime;
    private Button netbutton,sendtext;
    private CheckBox checkbox;
    private static final String APP_ID="wxd2d07257c4b4dc18";
    private IWXAPI api;


    public TimeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public TimeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public TimeView(Context context) {
        super(context);
    }

//    @Override初始化完成之后执行操作
    protected void onFinishInflate() {
        super.onFinishInflate();
        tvtime = (TextView) findViewById(R.id.tvtime);
        nettime = (TextView) findViewById(R.id.nettime);
        netbutton = (Button) findViewById(R.id.netbutton);
        api = WXAPIFactory.createWXAPI(getContext(), APP_ID);
        api.registerApp(APP_ID);
//        share = (Button) findViewById(R.id.share);
//        launchwx = (Button) findViewById(R.id.launchwx);
        sendtext = (Button) findViewById(R.id.sendtext);
        checkbox = (CheckBox) findViewById(R.id.checkbox);
        sendtext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editor = new EditText(getContext());
                editor.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
                editor.setText("更好的使用和利用时间工具");
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setTitle("Your Time");
                builder.setIcon(R.drawable.time);
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
                         Toast.makeText(getContext(),String.valueOf(api.sendReq(req)),Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("取消",null);
                final  AlertDialog alert = builder.create();
                alert.show();
            }
        });

        //为请求生成一个唯一标识


//        launchwx.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                api.openWXApp();
//            }
//        });
        netbutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadURL("http://open.baidu.com/special/time/");
                if(isNetWorkConnected(getContext())==false){
                    Toast.makeText(getContext(),"无法获取网络",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(),"正在获取网络时间",Toast.LENGTH_SHORT).show();
                }
            }
        });

//        share.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showShare();
//            }
//        });
        timeHandler.sendEmptyMessage(0);
    }
    public boolean isNetWorkConnected(Context context){
        if(context!=null){
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
           NetworkInfo mNetworkInfo= mConnectivityManager.getActiveNetworkInfo();
            if(mNetworkInfo!=null){
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
    private String buildTransaction(final String type){
        return  (type == null)?String.valueOf(System.currentTimeMillis()):type+System.currentTimeMillis();
    }
    private void refreshTime(){
        Calendar c = Calendar.getInstance();
        tvtime.setText(String.format("%d:%d:%d",c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),c.get(Calendar.SECOND)));
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if(visibility==View.VISIBLE){
            timeHandler.sendEmptyMessage(0);
        }else{
            timeHandler.removeMessages(0);
        }
    }

    private Handler timeHandler = new Handler() {

        public void handleMessage(android.os.Message msg){
                refreshTime();
            if(getVisibility()== View.VISIBLE){
                timeHandler.sendEmptyMessageDelayed(0,1000);
            }

            };

    };
    public void ReadURL(String url){
        new AsyncTask<String,Void,String>(){

            @Override
            protected String doInBackground(String... params) {
                try {
                    URL url = new URL(params[0]);
                    URLConnection connection = url.openConnection();
                    connection.connect();
                    long ld = connection.getDate();
                    Date date = new Date(ld);
                    timer=date.getHours()+"时"+date.getMinutes()+"分"+date.getSeconds()+"秒";

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                nettime.setText("当前的网络时间为:"+timer);
                super.onPostExecute(s);
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onCancelled(String s) {
                super.onCancelled(s);
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
            }
        }.execute(url);
    }
     private void showShare() {
//        ShareSDK.initSDK(getContext());
//       OnekeyShare oks = new OnekeyShare();
////            //关闭sso授权
//           oks.disableSSOWhenAuthorize();
//
//        // title标题：微信、QQ（新浪微博不需要标题）
//        oks.setTitle("我是分享标题");  //最多30个字符
//
//        // text是分享文本：所有平台都需要这个字段
//        oks.setText("我是分享文本，啦啦啦~http://uestcbmi.com/");  //最多40个字符
//
//        // imagePath是图片的本地路径：除Linked-In以外的平台都支持此参数
//        //oks.setImagePath(Environment.getExternalStorageDirectory() + "/meinv.jpg");//确保SDcard下面存在此张图片
//
//        //网络图片的url：所有平台
//        oks.setImageUrl("http://7sby7r.com1.z0.glb.clouddn.com/CYSJ_02.jpg");//网络图片rul
//
//        // url：仅在微信（包括好友和朋友圈）中使用
//        oks.setUrl("http://sharesdk.cn");   //网友点进链接后，可以看到分享的详情
//
//        // Url：仅在QQ空间使用
//        oks.setTitleUrl("http://www.baidu.com");  //网友点进链接后，可以看到分享的详情
//
//        // 启动分享GUI
//        oks.show(getContext());
             }
     }

