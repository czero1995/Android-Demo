package com.example.czero.jannote;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.VideoView;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zake on 4/16/16.
 */
public class Addcontent extends Activity implements View.OnClickListener {
    private String val;
    private ImageView c_img, c_draw;
    private VideoView c_video;
    private EditText c_text;
    private Button c_sava, c_cancel;
    private File imgFile, videoFile, drawFile, recordFile;
    private android.os.Environment Enviroment;
    private NoteDB noteDB;
    private SQLiteDatabase dbwriter;
    private LinearLayout draw;
    private Draw draws;
    private Record records;
    private Button cleardraw, savedraw;
    private LinearLayout drawactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcontent);
        val = getIntent().getStringExtra("flag");
        initview();
        draws = (Draw) findViewById(R.id.jannotedraw);
        noteDB = new NoteDB(this);
        dbwriter = noteDB.getWritableDatabase();
        c_sava.setOnClickListener(this);
        c_cancel.setOnClickListener(this);


    }

    private void initview() {
        cleardraw = (Button) findViewById(R.id.cleardraw);
        savedraw = (Button) findViewById(R.id.savedraw);
        cleardraw.setOnClickListener(this);
        savedraw.setOnClickListener(this);
        drawactivity = (LinearLayout) findViewById(R.id.drawactivity);
        c_img = (ImageView) findViewById(R.id.c_img);
        c_draw = (ImageView) findViewById(R.id.c_draw);
        c_video = (VideoView) findViewById(R.id.c_video);
        c_text = (EditText) findViewById(R.id.c_text);
        c_sava = (Button) findViewById(R.id.c_save);
        c_cancel = (Button) findViewById(R.id.c_cancel);
        if (val.equals("1")) {
            c_img.setVisibility(View.GONE);
            c_video.setVisibility(View.GONE);
            c_draw.setVisibility(View.GONE);
        }
        if (val.equals("2")) {
            c_img.setVisibility(View.VISIBLE);
            Intent iimg = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            imgFile = new File(Enviroment.getExternalStorageDirectory().getAbsolutePath() + "/" + getTime() + ".jpg");
            iimg.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imgFile));
            startActivityForResult(iimg, 1);
        }
        if (val.equals("3")) {
            c_video.setVisibility(View.VISIBLE);
            Intent ivideo = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            videoFile = new File(Enviroment.getExternalStorageDirectory().getAbsolutePath() + "/" + getTime() + ".mp4");
            ivideo.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(videoFile));
            startActivityForResult(ivideo, 2);
        }
        if (val.equals("4")) {
            drawactivity.setVisibility(View.VISIBLE);
            Intent draw = new Intent(Addcontent.this, Draw.class);
            startService(draw);


        }
        if (val.equals("5")) {
            Intent record = new Intent(Addcontent.this, Record.class);
            startActivity(record);
        }
    }

    private String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date curdate = new Date();
        String str = format.format(curdate);
        return str;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.c_save:
                addDB();
                finish();
                break;
            case R.id.c_cancel:
                finish();
                break;
            case R.id.cleardraw:
                draws.cleardraws();
                Toast.makeText(Addcontent.this, "画布清除,请继续", Toast.LENGTH_SHORT).show();
                break;
            case R.id.savedraw:
                File folder = new File(Enviroment.getExternalStorageDirectory().toString());
                boolean success = false;
                if (!folder.exists()) {
                    success = folder.mkdirs();
                }
                drawFile = new File(Enviroment.getExternalStorageDirectory().getAbsolutePath() + "/" + getTime() + ".jpg");
//                imgFile = new File(Enviroment.getExternalStorageDirectory().getAbsolutePath() + "/" + getTime() + ".jpg");

                if (!drawFile.exists()) {
                    try {
                        success = drawFile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                FileOutputStream ostream = null;
                try {
                    ostream = new FileOutputStream(drawFile);
                    View targetView = draws;
                    Bitmap well = draws.getBitmap();
                    Bitmap save = Bitmap.createBitmap(320, 480, Bitmap.Config.ARGB_8888);
                    Paint paint = new Paint();
                    paint.setColor(Color.WHITE);
                    Canvas now = new Canvas(save);
                    now.drawRect(new Rect(0, 0, 320, 480), paint);
                    now.drawBitmap(well, new Rect(0, 0, well.getWidth(), well.getHeight()), new Rect(0, 0, 320, 480), null);
                    if (save == null) {
                        System.out.println("NULL bitmap save\n");
                    }
                    save.compress(Bitmap.CompressFormat.PNG, 100, ostream);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Null error", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "File error", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "IO error", Toast.LENGTH_SHORT).show();
                }

                break;

        }

    }


    private void addDB() {
        ContentValues cv = new ContentValues();
        cv.put(NoteDB.TEXT, c_text.getText().toString());
        cv.put(NoteDB.TIME, getTime());
        cv.put(NoteDB.IMAGE, imgFile + "");
        cv.put(NoteDB.VIDEO, videoFile + "");
//        cv.put(NoteDB.IMAGE, drawFile + "");
//        cv.put(NoteDB.RECORD,recordFile+"");
//        cv.put(NoteDB.DRAW, drawFile+" ");
        //TOFO,有待研究,数据库cv.put少一个,adapter就没能显示在listview上
        dbwriter.insert(NoteDB.TABLE_NAME, null, cv);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            c_img.setImageBitmap(bitmap);
        }
        if (requestCode == 2) {
            c_video.setVideoURI(Uri.fromFile(videoFile));
            c_video.start();
        }

    }
}
