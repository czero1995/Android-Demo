package com.example.zake.smarttime;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.os.Vibrator;
import android.widget.Toast;

/**
 * Created by zake on 3/16/16.
 */
public class playalarm extends Activity {
    private MediaPlayer mp;
    private Button stopalarm;
    private Vibrator vibrator;
    private float y1,y2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm);

        mp = MediaPlayer.create(this,R.raw.music);
        mp.start();
        stopalarm= (Button) findViewById(R.id.stopalarm);
        vibrator();
        stopalarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPause();
                Intent intent = new Intent(playalarm.this,AlarmView.class);

            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP:
               y2 = event.getY();
                if (y2-y1>50){
                    Toast.makeText(playalarm.this,"uuuuuuuuuuu",Toast.LENGTH_SHORT).show();
                }
                    onPause();
                break;

        }


        return true;
    }

    public void vibrator(){
        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);

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
