package com.example.czero.smarttime;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;import android.os.Vibrator;
import android.widget.CheckBox;
import android.widget.Toast;

public class playAlarm extends Activity {
    private MediaPlayer mp;
    private Button stopalarm;
    private Vibrator vibrator;
    private float y1,y2,y3;
    private Context context;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playalarm);

        mp = MediaPlayer.create(this,R.raw.music);
        mp.start();

        stopalarm= (Button) findViewById(R.id.stopalarm);
        vibrator();
        stopalarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(playAlarm.this,Alarm.class);
                onPause();
                vibrator.cancel();


            }
        });
    }
    public void vibrator(){
        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        vibrator.vibrate(new long[]{1000,3000,1000,3000,1000,3000,1000,3000,1000,3000,1000,3000,1000,3000,1000,3000,1000,3000,1000,3000},-1 );

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                y1=event.getY();
                break;
            case MotionEvent.ACTION_UP:
                y2=event.getY();
                if(y2-y1>10){
                    Toast.makeText(playAlarm.this,"uuuuuuu",Toast.LENGTH_SHORT).show();

                }
                break;
            case MotionEvent.ACTION_MOVE:
                y3=event.getY();
                if(y3-y1>10){
                    Toast.makeText(playAlarm.this,"uuuuuuu",Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.stop();;
        mp.release();
    }
}
