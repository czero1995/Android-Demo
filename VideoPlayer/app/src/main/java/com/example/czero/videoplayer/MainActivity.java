package com.example.czero.videoplayer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {
    private VideoView vv;
    private Button start, stop;
    int[] raws = {R.raw.a, R.raw.b, R.raw.c, R.raw.d, R.raw.e};
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vv = (VideoView) findViewById(R.id.videoview);
        start = (Button) findViewById(R.id.startplay);
        stop = (Button) findViewById(R.id.stopplay);
        stop.setVisibility(View.GONE);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop.setVisibility(View.VISIBLE);
                start.setVisibility(View.GONE);
                for (int j = 0; j < raws.length; j++) {
                    vv.setVideoPath("android.resource://com.example.czero.videoplayer/" + raws[i]);
                    vv.setMediaController(new MediaController(MainActivity.this));
                    vv.requestFocus();

                    vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            vv.start();
                        }
                    });
                    vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            vv.setVideoPath("android.resource://com.example.czero.videoplayer/" + raws[i++]);
                            vv.start();
//                            mp.setLooping(true);

                        }
                    });
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vv.pause();
                stop.setVisibility(View.GONE);
                start.setVisibility(View.VISIBLE);
            }
        });
    }

}

