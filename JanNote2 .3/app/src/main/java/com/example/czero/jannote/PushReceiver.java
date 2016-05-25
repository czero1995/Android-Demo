package com.example.czero.jannote;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.util.JsonToken;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import cn.bmob.push.PushConstants;

public class PushReceiver extends BroadcastReceiver {
    String message = "";
    private String url = "http://czero.cn";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(PushConstants.ACTION_MESSAGE)) {
            String msg = intent.getStringExtra(PushConstants.EXTRA_PUSH_MESSAGE_STRING);
//            Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
            JSONTokener jsonTokener = new JSONTokener(msg);
            try {
                JSONObject object = (JSONObject) jsonTokener.nextValue();
                message = object.getString("alert");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Uri uri = Uri.parse(url);
            Intent pm = new Intent(Intent.ACTION_VIEW, uri);
            NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
//            Notification notification = new Notification(R.drawable.jannote,"简记",System.currentTimeMillis());
//            notification.setLatestEventInfo(context,"简记",message,null);
//            manager.notify(R.drawable.jannote,notification);
//            Intent pm =  new Intent(context,MainActivity.class);
            PendingIntent pintent = PendingIntent.getActivity(context, 0, pm, 0);
            Notification.Builder builder = new Notification.Builder(context);
            builder.setSmallIcon(R.drawable.jannote);//设置图标
            builder.setTicker("简记");//手机状态栏提示
            builder.setWhen(System.currentTimeMillis());//设置时间
            builder.setContentTitle("简记");//设置标题
            builder.setContentText(message);//设置通知内容
            builder.setContentIntent(pintent);//点击之后的意图
//        builder.setDefaults(Notification.DEFAULT_SOUND);//设置提示声音
//        builder.setDefaults(Notification.DEFAULT_LIGHTS);//设置提示声灯
//        builder.setDefaults(Notification.DEFAULT_VIBRATE);//设置提示震动
            builder.setDefaults(Notification.DEFAULT_ALL);
            Notification notification = builder.build();
            manager.notify(R.drawable.jannote, notification);
        }
    }
}



























