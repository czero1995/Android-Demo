package com.example.czero.smarttime;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zake on 4/6/16.
 */
public class Time extends LinearLayout {
    private TextView showtime,nettime;
    private Button btn_nettime;
    private String time;


    public Time(Context context) {
        super(context);
    }

    public Time(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Time(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
        handler.sendEmptyMessage(0);
    }
    private void init(){
        showtime= (TextView) findViewById(R.id.showtime);
        nettime= (TextView) findViewById(R.id.nettime);
        btn_nettime= (Button)findViewById(R.id.btn_nettime);
        btn_nettime.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getNetTime("http://www.baidu.com");
                if (isNetConnected(getContext()) == false) {
                    Toast.makeText(getContext(), "无法获取网络", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getContext(), "正在获取网络时间", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 获取系统时间
     */
    private void refreshTime(){
      Calendar c = Calendar.getInstance();
      showtime.setText(String.format("%d:%d:%d",c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),c.get(Calendar.SECOND)));;
    }

    /**
     * 当切换到其他的Activity时,调用该方法,可以节省内存
     * @param changedView
     * @param visibility
     */
    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if(visibility==View.VISIBLE){
            handler.sendEmptyMessage(0);
        }else {
            handler.removeMessages(0);
        }
    }

    /**
     * 使用Handle来创建子线程,来动态更新UI界面的显示
     */
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(android.os.Message msg) {
            refreshTime();
            if(getVisibility()== View.VISIBLE){
                handler.sendEmptyMessageDelayed(0,1000);
            }
        };
    };

    /**
     * 获取网络时间
     * 使用异步线程动态更新UI
     * @param url
     */
    private void getNetTime(String url){
        new AsyncTask<String,Void,String>(){

            @Override
            protected String doInBackground(String... params) {
                try {
                    URL url= new URL(params[0]);
                    URLConnection urlConnection = url.openConnection();
                    urlConnection.connect();
                    long ld = urlConnection.getDate();
                    Date date = new Date(ld);
                    time = date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            /**
             * 实现一步线程AsyncTask的方法
             */
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
               nettime.setText("当前的网络时间为:"+time);
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

    /**
     * 判断是否能够获取网络服务
     * @param context
     * @return
     */
    public boolean isNetConnected(Context context){
        if(context!=null){
            ConnectivityManager mConnectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = mConnectivityManager.getActiveNetworkInfo();
            if(info!=null){
                return info.isAvailable();
            }
        }
        return false;
    }
}
