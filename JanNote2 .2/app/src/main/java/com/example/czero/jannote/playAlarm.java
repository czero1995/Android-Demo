package com.example.czero.jannote;

import android.app.Activity;
import android.app.Service;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by zake on 4/26/16.
 */
public class playAlarm extends Activity {
    private Button vibrator_stop;
    private Vibrator vibrator;
    private TextView todotext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm);
        vibrator_stop= (Button) findViewById(R.id.vibrator_stop);
        todotext= (TextView) findViewById(R.id.todotext);

        vibrator_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.cancel();
            }
        });
        vibrator();
    }
    public void vibrator(){
        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        vibrator.vibrate(new long[]{1000,3000,1000,3000,1000,3000,1000,3000,1000,3000,1000,3000,1000,3000,1000,3000,1000,3000,1000,3000},-1 );

    }

}
