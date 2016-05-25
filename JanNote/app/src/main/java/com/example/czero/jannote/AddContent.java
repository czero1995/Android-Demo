package com.example.czero.jannote;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.os.EnvironmentCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zake on 3/24/16.
 */
public class AddContent extends Activity implements View.OnClickListener {
    private String val;
    private Button savebtn, deletebtn;
    private EditText ettext;
    private ImageView c_img,c_draw;
    private VideoView c_video;
    private VideoView c_record;
    private MediaRecorder mediaRecorder;
    private NoteDB notesDB;
    private SQLiteDatabase dbWriter;
    private File phoneFile,videoFile,recordFile,drawFile;
    private android.os.Environment Environment;
    private LinearLayout draws;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcontent);
        val = getIntent().getStringExtra("flag");
        savebtn = (Button) findViewById(R.id.save);
        deletebtn = (Button) findViewById(R.id.delete);
        ettext = (EditText) findViewById(R.id.ettext);
        c_img = (ImageView) findViewById(R.id.c_img);
        c_draw = (ImageView) findViewById(R.id.c_draw);
        c_video = (VideoView) findViewById(R.id.c_video);
        draws= (LinearLayout) findViewById(R.id.draws);
        mediaRecorder=new MediaRecorder();
        savebtn.setOnClickListener(this);
        deletebtn.setOnClickListener(this);
        notesDB = new NoteDB(this);
        dbWriter = notesDB.getWritableDatabase();
        initview();
    }

    private void initview() {
        if (val.equals("1")) {
            c_img.setVisibility(View.GONE);
            c_video.setVisibility(View.GONE);
        }
        if (val.equals("2")) {
            c_img.setVisibility(View.VISIBLE);
            c_video.setVisibility(View.GONE);
            Intent iimg = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            phoneFile = new File(Environment.getExternalStorageDirectory()
                    .getAbsoluteFile() + "/" + getTime() + ".jpg");
            iimg.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(phoneFile));
            startActivityForResult(iimg, 1);
        }
        if (val.equals("3")) {
            c_img.setVisibility(View.GONE);
            c_video.setVisibility(View.VISIBLE);
            Intent video = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            videoFile = new File(Environment.getExternalStorageDirectory()
                    .getAbsoluteFile() + "/" + getTime() + ".mp4");
            video.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(videoFile));
            startActivityForResult(video, 2);

        }
//        if(val.equals("4")){
//            c_img.setVisibility(View.GONE);
//            c_video.setVisibility(View.GONE);
//            Intent record = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
//            recordFile=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+getTime()+".mp3");
//            record.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(recordFile));
//            startActivityForResult(record,3);
//
//        }
        if(val.equals("5")){
            c_img.setVisibility(View.GONE);
            c_video.setVisibility(View.GONE);
            ettext.setVisibility(View.GONE);
            draws.setVisibility(View.VISIBLE);
            Intent draw = new Intent(AddContent.this,Draw.class);
            draw.setClass(AddContent.this,Draw.class);
            drawFile=new File(Environment.getExternalStorageDirectory()
            .getAbsolutePath()+"/"+getTime()+".jpg");
            startService(draw);
            Bitmap bitmap = BitmapFactory.decodeFile(drawFile
                    .getAbsolutePath());
            c_draw.setImageBitmap(bitmap);
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Bitmap bitmap = BitmapFactory.decodeFile(phoneFile
                    .getAbsolutePath());
            c_img.setImageBitmap(bitmap);
        }
        if (requestCode == 2) {
            c_video.setVideoURI(Uri.fromFile(videoFile));
            c_video.start();
        }
        if(requestCode==3){
            Bitmap bitmap = BitmapFactory.decodeFile(drawFile
                    .getAbsolutePath());
            c_draw.setImageBitmap(bitmap);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                addDB();
                finish();
                break;

            case R.id.delete:
                finish();
                break;
        }

    }

    public void addDB() {
        ContentValues cv = new ContentValues();
        cv.put(NoteDB.CONTENT, ettext.getText().toString());
        cv.put(NoteDB.TIME, getTime());
        cv.put(NoteDB.PATH, phoneFile + "");
        cv.put(NoteDB.VIDEO, videoFile + "");
        cv.put(NoteDB.DRAW, drawFile+"");
        //TOFO,有待研究,数据库cv.put少一个,adapter就没能显示在listview上
        dbWriter.insert(NoteDB.TABLE_NAME, null, cv);
    }
    private String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date curDate = new Date();
        String str = format.format(curDate);
        return str;
    }
}
